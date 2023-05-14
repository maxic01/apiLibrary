package com.spring.apilibrary.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ComentarioDTO {
    private long id;
    @NotEmpty(message = "el nombre no debe estar vacio")

    private String nombre;
    @NotEmpty(message = "el email no debe estar vacio")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "el cuerpo del comentario debe tener al menos diez caracteres")
    private String cuerpo;

    public ComentarioDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
