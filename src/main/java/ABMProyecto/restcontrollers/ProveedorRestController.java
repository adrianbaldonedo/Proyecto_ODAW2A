package ABMProyecto.restcontrollers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.services.ProveedorService;
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
@Tag(name="ProveedorController",description="proveedores")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorRestController {
 @Autowired
    private ProveedorService proveedorService;
    @Operation (summary = "Get todos los Proveedoress",
    description="Devuelve lista de todos los Proveedors.",
    tags={"etiquetas clasificadoras","get"})
    @ApiResponses({
    @ApiResponse(responseCode = "200",
    content = {@Content(schema = @Schema(implementation = Proveedor.class),
    mediaType = "application/json") }),
    @ApiResponse(responseCode = "404",
    content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500",
    content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    public ResponseEntity<?>getAll(){
        List<Proveedor>lista=proveedorService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/Proveedor/{id}")
    public ResponseEntity<?>showOne(@PathVariable Long id){
       Proveedor proveedor=proveedorService.obtenerporId(id);
       return ResponseEntity.ok(proveedor);
    }
    @PostMapping("/Proveedor")
    public ResponseEntity<?>showPost(@Valid @RequestBody Proveedor nuevoProveedor){
    Proveedor proveedor=proveedorService.añadir(nuevoProveedor);
    return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
    }
    @PostMapping("/Proveedor/{id}")
    public ResponseEntity<?>showEditSubmit(@Valid @RequestBody Proveedor editProveedor,@PathVariable Long id){
        if(proveedorService.obtenerporId(id)==null){
            return ResponseEntity.notFound().build();
        }
        if(id!=editProveedor.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            proveedorService.editar(editProveedor);
        return ResponseEntity.ok(editProveedor);
    }
    @DeleteMapping("/Proveedor/{id}")
        public ResponseEntity<?> showDelete(@PathVariable Long id){
            if(proveedorService.obtenerporId(id)==null)
                return ResponseEntity.notFound().build();
            proveedorService.borrar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}
