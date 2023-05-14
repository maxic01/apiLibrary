package com.spring.apilibrary.Services;


import com.spring.apilibrary.Dto.ComentarioDTO;
import com.spring.apilibrary.Dto.PublicacionDTO;
import com.spring.apilibrary.Dto.PublicacionRespuesta;

public interface IPublicacionService {
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public PublicacionRespuesta obtenerPublicaciones(int numPagina, int cantPaginas, String ordenar, String sortDir);
    public PublicacionDTO obtenerPublicacion(long id);
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
    public PublicacionDTO actualizarParcial(PublicacionDTO publicacionDTO, Long id);
    public void eliminarPublicacion(long id);
}
