package com.spring.apilibrary.Controller;


import com.spring.apilibrary.Dto.ComentarioDTO;
import com.spring.apilibrary.Services.IComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;

    @Operation(summary = "Obtener comentarios por ID publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> listarComentariosPorPublicacion(@PathVariable(value = "publicacionId") Long publicacionId) {
        return comentarioService.obtenerComentarioPorPublicacion(publicacionId);
    }

    @Operation(summary = "Obtener comentario por ID de publicacion y ID de comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion o comentario encontrada")
    })
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> obtenerComentario(@PathVariable(value = "publicacionId") Long publicacionId,
                                                           @PathVariable(value = "id") long comentarioId) {
        ComentarioDTO comentarioDTO = comentarioService.obtenerComentario(publicacionId, comentarioId);
        return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Guardar comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value = "publicacionId") long publicacionId, @Valid  @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO), HttpStatus.CREATED);

    }

    @Operation(summary = "Actualizar comentario por ID publicacion y ID comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion o comentario no encontrada")
    })
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(@PathVariable(value = "publicacionId") Long publicacionId,
                                                              @PathVariable(value = "id") Long comentarioId, @Valid @RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO comentarioActualizado = comentarioService.actualizarComentario(publicacionId, comentarioId, comentarioDTO);
        return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
    }
    @Operation(summary = "Actualizar parcial por ID publicacion y ID comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion o comentario no encontrada")
    })
    @PatchMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarParcial(@PathVariable(value = "publicacionId") Long publicacionId,
                                                              @PathVariable(value = "comentarioId") Long comentarioId, @Valid
                                                               @RequestBody ComentarioDTO solicitud) {
        ComentarioDTO comentarioParcial = comentarioService.actualizarParcial(publicacionId, comentarioId, solicitud);
        return ResponseEntity.ok(comentarioParcial);
    }

    @Operation(summary = "Eliminar comentario por ID publicacion y ID comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion o comentario no encontrada")
    })
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") Long publicacionId,
                                                     @PathVariable(value = "id") long comentarioId){
        comentarioService.eliminarComentario(publicacionId, comentarioId);
        return new ResponseEntity<>("comentario eliminado", HttpStatus.OK);
    }

}