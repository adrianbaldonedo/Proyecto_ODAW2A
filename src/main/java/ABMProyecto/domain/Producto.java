package ABMProyecto.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
public class Producto {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty  
    private String nombre;
    private double precio;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Proveedor proveedor;
    @ManyToOne
    private Tipo tipo;
}