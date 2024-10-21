package ABMProyecto.restcontrollers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ABMProyecto.domain.Producto;
import ABMProyecto.services.ProductoService;
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
@Tag(name="ProductoController",description="productos")
@RestController
@RequestMapping("/api/Productos")
public class ProductoRestController {
 @Autowired
    private ProductoService productoService;
    @Operation (summary = "Get todos los Productos",
    description="Devuelve lista de todos los Productos.",
    tags={"etiquetas clasificadoras","get"})
    @ApiResponses({
    @ApiResponse(responseCode = "200",
    content = {@Content(schema = @Schema(implementation = Producto.class),
    mediaType = "application/json") }),
    @ApiResponse(responseCode = "404",
    content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500",
    content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    public ResponseEntity<?>getAll(){
        List<Producto>lista=productoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/Producto/{id}")
    public ResponseEntity<?>showOne(@PathVariable Long id){
       Producto producto=productoService.obtenerporId(id);
       return ResponseEntity.ok(producto);
    }
    @PostMapping("/Producto")
    public ResponseEntity<?>showPost(@Valid @RequestBody Producto nuevoProducto){
    Producto Producto=productoService.añadir(nuevoProducto);
    return ResponseEntity.status(HttpStatus.CREATED).body(Producto);
    }
    @PostMapping("/Producto/{id}")
    public ResponseEntity<?>showEditSubmit(@Valid @RequestBody Producto editProducto,@PathVariable Long id){
        if(productoService.obtenerporId(id)==null){
            return ResponseEntity.notFound().build();
        }
        if(id!=editProducto.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            productoService.editar(editProducto);
        return ResponseEntity.ok(editProducto);
    }
    @DeleteMapping("/Producto/{id}")
        public ResponseEntity<?> showDelete(@PathVariable Long id){
            if(productoService.obtenerporId(id)==null)
                return ResponseEntity.notFound().build();
            productoService.borrar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}
