package ABMProyecto.restcontrollers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ABMProyecto.domain.Tipo;
import ABMProyecto.services.TipoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name="TipoController",description="Tipos")
@RestController
@RequestMapping("/api/tipos")
public class TipoRestController {
 @Autowired
    private TipoService tipoService;
    @Operation (summary = "Get todos los tipos",
    description="Devuelve lista de todos los tipos.",
    tags={"etiquetas clasificadoras","get"})
    @ApiResponses({
    @ApiResponse(responseCode = "200",
    content = {@Content(schema = @Schema(implementation = Tipo.class),
    mediaType = "application/json") }),
    @ApiResponse(responseCode = "404",
    content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500",
    content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    public ResponseEntity<?>getAll(){
        List<Tipo>lista=tipoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/Tipo/{id}")
    public ResponseEntity<?>showOne(@PathVariable Long id){
       Tipo tipo=tipoService.obtenerporId(id);
       return ResponseEntity.ok(tipo);
    }
    @PostMapping("/Tipo")
    public ResponseEntity<?>showPost(@Valid @RequestBody Tipo nuevoTipo){
    Tipo tipo=tipoService.añadir(nuevoTipo);
    return ResponseEntity.status(HttpStatus.CREATED).body(tipo);
    }
    @PostMapping("/Tipo/{id}")
    public ResponseEntity<?>showEditSubmit(@Valid @RequestBody Tipo editTipo,@PathVariable Long id){
        if(tipoService.obtenerporId(id)==null){
            return ResponseEntity.notFound().build();
        }
        if(id!=editTipo.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            tipoService.editar(editTipo);
        return ResponseEntity.ok(editTipo);
    }
    @DeleteMapping("/Tipo/{id}")
        public ResponseEntity<?> showDelete(@PathVariable Long id){
            if(tipoService.obtenerporId(id)==null)
                return ResponseEntity.notFound().build();
            tipoService.borrar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}
