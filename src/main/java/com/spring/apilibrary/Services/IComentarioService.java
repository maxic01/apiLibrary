package com.spring.apilibrary.Services;



import com.spring.apilibrary.Dto.ComentarioDTO;

import java.util.List;

public interface IComentarioService {
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentarioPorPublicacion(long publicacionId);
    public ComentarioDTO obtenerComentario(Long publicacionId, Long comentarioId);
    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitud);
    public void eliminarComentario(Long publicacionId, Long comentarioId);
    public ComentarioDTO actualizarParcial(Long publicacionId, Long comentarioId, ComentarioDTO solicitud);
}
