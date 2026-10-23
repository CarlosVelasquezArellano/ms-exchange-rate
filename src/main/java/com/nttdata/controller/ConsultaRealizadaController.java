package com.nttdata.controller;

import com.nttdata.dto.ConsultaRealizadaResponse;
import com.nttdata.service.IConsultaRealizada;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("api/v1")
public class ConsultaRealizadaController {

    private final IConsultaRealizada consultaRealizada;

    public ConsultaRealizadaController(IConsultaRealizada consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    @GET
    @Path("/tipo-cambio/{documento}")
    @Produces(MediaType.APPLICATION_JSON)
    public ConsultaRealizadaResponse obtenerTipoCambio(@PathParam("documento") String documento){
        return consultaRealizada.obtenerTipoCambio(documento);
    }

    @GET
    @Path("/tipo-cambio/{documento}/registros")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsultaRealizadaResponse> obtenerConsultas(@PathParam("documento") String documento){
        return consultaRealizada.obtenerConsultas(documento);

    }

}
