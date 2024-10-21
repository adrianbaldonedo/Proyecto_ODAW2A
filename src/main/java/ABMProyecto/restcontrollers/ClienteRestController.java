package ABMProyecto.restcontrollers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ABMProyecto.domain.Cliente;
import ABMProyecto.services.ClienteService;
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
@Tag(name="ClienteController",description="clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
 @Autowired
    private ClienteService clienteService;
    @Operation (summary = "Get todos los clientes",
    description="Devuelve lista de todos los clientes.",
    tags={"etiquetas clasificadoras","get"})
    @ApiResponses({
    @ApiResponse(responseCode = "200",
    content = {@Content(schema = @Schema(implementation = Cliente.class),
    mediaType = "application/json") }),
    @ApiResponse(responseCode = "404",
    content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500",
    content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    public ResponseEntity<?>getAll(){
        List<Cliente>lista=clienteService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?>showOne(@PathVariable Long id){
       Cliente cliente=clienteService.obtenerporId(id);
       return ResponseEntity.ok(cliente);
    }
    @PostMapping("/cliente")
    public ResponseEntity<?>showPost(@Valid @RequestBody Cliente nuevoCliente){
    Cliente cliente=clienteService.añadir(nuevoCliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
    @PostMapping("/cliente/{id}")
    public ResponseEntity<?>showEditSubmit(@Valid @RequestBody Cliente editCliente,@PathVariable Long id){
        if(clienteService.obtenerporId(id)==null){
            return ResponseEntity.notFound().build();
        }
        if(id!=editCliente.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            clienteService.editar(editCliente);
        return ResponseEntity.ok(editCliente);
    }
    @DeleteMapping("/cliente/{id}")
        public ResponseEntity<?> showDelete(@PathVariable Long id){
            if(clienteService.obtenerporId(id)==null)
                return ResponseEntity.notFound().build();
            clienteService.borrar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}
