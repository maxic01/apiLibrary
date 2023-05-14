package com.spring.apilibrary.Controller;


import com.spring.apilibrary.Dto.ComentarioDTO;
import com.spring.apilibrary.Dto.PublicacionDTO;
import com.spring.apilibrary.Dto.PublicacionRespuesta;
import com.spring.apilibrary.Services.IPublicacionService;
import com.spring.apilibrary.Utilities.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private IPublicacionService publicacionService;
    @Operation(summary = "Obtener todas las publicaciones")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public PublicacionRespuesta listarPublicaciones(
            @RequestParam(value = "pageNo", defaultValue = Constantes.NUMERO_PAGINA_POR_DEFECTO, required = false)int numPagina,
            @RequestParam(value = "pageSize", defaultValue = Constantes.CANTIDAD_PAGINAS_POR_DEFECTO, required = false)int cantPaginas,
            @RequestParam(value = "sortBy", defaultValue = Constantes.ORDENAR_POR_DEFECTO, required = false)String ordenar,
            @RequestParam(value = "sortDir", defaultValue = Constantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false)String sortDir){
        return publicacionService.obtenerPublicaciones(numPagina, cantPaginas, ordenar, sortDir);
    }
    @Operation(summary = "Obtener publicacion por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacion(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(publicacionService.obtenerPublicacion(id));
    }
    @Operation(summary = "Guardar publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "Actualizar publicacion por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id")long id){
        PublicacionDTO publicacionRespuesta = publicacionService.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarParcial(@RequestBody PublicacionDTO publicacionDTO, @PathVariable(value = "id") Long id) {
        PublicacionDTO publicacionParcial = publicacionService.actualizarParcial(publicacionDTO, id);
        return ResponseEntity.ok(publicacionParcial);
    }

    @Operation(summary = "Eliminar publicacion por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id")long id){
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }
}
