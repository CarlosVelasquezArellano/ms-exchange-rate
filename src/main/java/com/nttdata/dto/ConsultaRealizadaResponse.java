package com.nttdata.dto;

import java.time.LocalDate;

public record ConsultaRealizadaResponse(

        LocalDate fecha,
        double sunat,
        double compra,
        double venta
) {
}
