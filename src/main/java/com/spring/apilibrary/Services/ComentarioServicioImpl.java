package com.spring.apilibrary.Services;


import com.spring.apilibrary.Dto.ComentarioDTO;
import com.spring.apilibrary.Exceptions.BlogException;
import com.spring.apilibrary.Exceptions.ResourceNotFoundException;
import com.spring.apilibrary.Model.Comentario;
import com.spring.apilibrary.Model.Publicacion;
import com.spring.apilibrary.Repository.IComentarioRepository;
import com.spring.apilibrary.Repository.IPublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements IComentarioService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IComentarioRepository comentarioRepository;

    @Autowired
    private IPublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapModel(comentarioDTO);

        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return mapDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentarioPorPublicacion(long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentario(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(!comentario.getPublicacion().equals(publicacion)) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion");
        }
        return mapDTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitud) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(!comentario.getPublicacion().equals(publicacion)) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion");
        }
        comentario.setNombre(solicitud.getNombre());
        comentario.setEmail(solicitud.getEmail());
        comentario.setCuerpo(solicitud.getCuerpo());

        Comentario comentarioActualizado = comentarioRepository.save(comentario);
        return mapDTO(comentarioActualizado);

    }

    @Override
    public void eliminarComentario(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(!comentario.getPublicacion().equals(publicacion)) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion");
        }
        comentarioRepository.delete(comentario);
    }

    @Override
    public ComentarioDTO actualizarParcial(Long publicacionId, Long comentarioId, ComentarioDTO solicitud) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(!comentario.getPublicacion().equals(publicacion)) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion");
        }

        if (solicitud.getNombre() != null) {
            comentario.setNombre(solicitud.getNombre());
        }
        if (solicitud.getEmail() != null) {
            comentario.setEmail(solicitud.getEmail());
        }
        if (solicitud.getCuerpo() != null) {
            comentario.setCuerpo(solicitud.getCuerpo());
        }

        Comentario comentarioActualizado = comentarioRepository.save(comentario);
        return mapDTO(comentarioActualizado);
    }


    private ComentarioDTO mapDTO(Comentario comentario){

        return modelMapper.map(comentario, ComentarioDTO.class);
    }
    private Comentario mapModel(ComentarioDTO comentarioDTO){
        /*Comentario comentario = new Comentario();

        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());*/
        return modelMapper.map(comentarioDTO, Comentario.class);
    }
}
