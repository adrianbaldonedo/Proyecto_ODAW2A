package ABMProyecto.domain;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FormInfo {
    @NotEmpty
    @NonNull
    private String nombre;
    @Email
    private String email;
    private String valores;
    private Boolean aceptaCondiciones;
}
