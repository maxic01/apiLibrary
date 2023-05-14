package com.spring.apilibrary.Services;

import com.spring.apilibrary.Dto.ComentarioDTO;
import com.spring.apilibrary.Dto.PublicacionDTO;
import com.spring.apilibrary.Dto.PublicacionRespuesta;
import com.spring.apilibrary.Exceptions.BlogException;
import com.spring.apilibrary.Exceptions.ResourceNotFoundException;
import com.spring.apilibrary.Model.Comentario;
import com.spring.apilibrary.Model.Publicacion;
import com.spring.apilibrary.Repository.IPublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements IPublicacionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IPublicacionRepository publicacionRepository;
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = mapModel(publicacionDTO);
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        return mapDTO(nuevaPublicacion);
    }

    @Override
    public PublicacionRespuesta obtenerPublicaciones(int numPagina, int cantPaginas, String ordenar, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenar).ascending():Sort.by(ordenar).descending();
        PageRequest pageable = PageRequest.of(numPagina, cantPaginas, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        List<Publicacion> listaPublicaciones = publicaciones.getContent();
        List<PublicacionDTO> contenido = listaPublicaciones.stream().map(publicacion -> mapDTO(publicacion)).collect(Collectors.toList());

        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumPagina(publicaciones.getNumber());
        publicacionRespuesta.setCantPaginas(publicaciones.getSize());
        publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setUltima(publicaciones.isLast());

        return publicacionRespuesta;
    }

    @Override
    public PublicacionDTO obtenerPublicacion(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapDTO(publicacionActualizada);
    }
    @Override
    public PublicacionDTO actualizarParcial(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        if (publicacion.getTitulo() != null) {
            publicacion.setTitulo(publicacionDTO.getTitulo());
        }
        if (publicacion.getContenido() != null) {
            publicacion.setContenido(publicacionDTO.getContenido());
        }

        Publicacion publiacionActualizado = publicacionRepository.save(publicacion);
        return mapDTO(publiacionActualizado);
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacionRepository.delete(publicacion);
    }

    private PublicacionDTO mapDTO(Publicacion publicacion){

        return modelMapper.map(publicacion, PublicacionDTO.class);
    }
    private Publicacion mapModel(PublicacionDTO publicacionDTO){

        return modelMapper.map(publicacionDTO, Publicacion.class);
    }
}
