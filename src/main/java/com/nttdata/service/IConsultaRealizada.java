package com.nttdata.service;

import com.nttdata.dto.ConsultaRealizadaResponse;
import com.nttdata.entity.ConsultaRealizada;

import java.util.List;


public interface IConsultaRealizada {

    ConsultaRealizadaResponse obtenerTipoCambio(String documento);

    List<ConsultaRealizadaResponse> obtenerConsultas(String documento);

}
