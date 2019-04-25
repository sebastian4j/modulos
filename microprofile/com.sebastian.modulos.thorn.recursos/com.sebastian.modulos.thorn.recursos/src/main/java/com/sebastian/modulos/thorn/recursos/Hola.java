package com.sebastian.modulos.thorn.recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hola {

    @GET
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok("Hola desde el jar").build();
    }
}
