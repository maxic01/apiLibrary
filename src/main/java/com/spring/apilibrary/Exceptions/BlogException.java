package com.spring.apilibrary.Exceptions;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public BlogException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public BlogException(HttpStatus estado, String mensaje, String mensaje1) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
