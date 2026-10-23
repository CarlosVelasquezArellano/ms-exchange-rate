package com.nttdata.proxy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.LocalDate;

@RegisterRestClient(configKey = "tipo-cambio-proxy")
@Path("/tipo-cambio/today.json")
public interface ApiProxy {

    @GET
    TipoCambioProxy obtenerTipoCambio();
}
