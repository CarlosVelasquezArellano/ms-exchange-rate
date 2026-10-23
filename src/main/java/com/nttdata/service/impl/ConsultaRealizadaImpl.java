package com.nttdata.service.impl;

import com.nttdata.dto.ConsultaRealizadaResponse;
import com.nttdata.entity.ConsultaRealizada;
import com.nttdata.proxy.ApiProxy;
import com.nttdata.proxy.TipoCambioProxy;
import com.nttdata.repository.ConsultaRealizadaRepository;
import com.nttdata.service.IConsultaRealizada;
import com.nttdata.utils.Parametros;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ConsultaRealizadaImpl implements IConsultaRealizada {

    private final ApiProxy apiProxy;
    private final ConsultaRealizadaRepository repository;

    public ConsultaRealizadaImpl(@RestClient ApiProxy apiProxy, ConsultaRealizadaRepository repository) {
        this.apiProxy = apiProxy;
        this.repository = repository;
    }

    @Override
    public ConsultaRealizadaResponse obtenerTipoCambio(String documento) {

        if (documento == null || documento.strip().length() < 8){
            throw new WebApplicationException(
                    Response.status(400)
                            .type(MediaType.APPLICATION_JSON)
                            .entity(Map.of(
                                    "mensaje",
                                    "El documento es obligatorio y debe contener como mínimo 8 caracteres."
                            )).build()
            );
        }

        long consultas = repository.cantidadRegistros(documento);

        if (consultas >= Parametros.MAX_SOLICITUDES) {
            throw new WebApplicationException(
                    Response.status(429)
                            .type(MediaType.APPLICATION_JSON)
                            .entity(Map.of(
                                    "mensaje",
                                    "Se alcanzó el máximo de solicitudes permitidas para hoy"
                            )).build()
            );
        }

        TipoCambioProxy tipoCambio = apiProxy.obtenerTipoCambio();
        registrarConsulta(tipoCambio, documento);

        return new ConsultaRealizadaResponse(
                tipoCambio.getFecha(),
                tipoCambio.getSunat(),
                tipoCambio.getCompra(),
                tipoCambio.getVenta()
        );

    }

    @Override
    public List<ConsultaRealizadaResponse> obtenerConsultas(String documento) {
        if (documento == null || documento.strip().length() < 8){
            throw new WebApplicationException(
                    Response.status(400)
                            .type(MediaType.APPLICATION_JSON)
                            .entity(Map.of(
                                    "mensaje",
                                    "El documento es obligatorio y debe contener como mínimo 8 caracteres."
                            )).build()
            );
        }

        var response = repository.listarRegistros(documento).stream()
                .map(r -> new ConsultaRealizadaResponse(
                        r.getFeCreaRegistro().toLocalDate(),
                        r.getSunat(),
                        r.getCompra(),
                        r.getVenta()
                ))
                .toList();

        if (response.isEmpty()){
            throw new WebApplicationException(
                    Response.status(404)
                            .type(MediaType.APPLICATION_JSON)
                            .entity(Map.of(
                                    "mensaje",
                                    "No se encontraron registros para el documento: " + documento
                            )).build()
            );
        }

        return response;
    }

    private void registrarConsulta(TipoCambioProxy tipoCambio, String documento) {
        repository.registrar(new ConsultaRealizada(
                null,
                documento,
                tipoCambio.getSunat(),
                tipoCambio.getCompra(),
                tipoCambio.getVenta(),
                LocalDateTime.now()
        ));
    }
}
