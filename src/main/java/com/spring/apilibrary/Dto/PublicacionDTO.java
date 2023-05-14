package com.spring.apilibrary.Dto;

import com.spring.apilibrary.Model.Comentario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PublicacionDTO {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "El titulo de la publicacion debe tener al menos dos caracteres")
    private String titulo;
    @NotEmpty
    @Size(min = 10, message = "La descripcion de la publicacion debe tener al menos diez caracteres")
    private String descripcion;
    @NotEmpty
    private String contenido;
    private Set<Comentario> comentario;

    public PublicacionDTO() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Set<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(Set<Comentario> comentario) {
        this.comentario = comentario;
    }
}
