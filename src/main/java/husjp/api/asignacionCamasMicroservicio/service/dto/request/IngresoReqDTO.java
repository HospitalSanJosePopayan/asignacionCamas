package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngresoReqDTO {

    @NotEmpty
    private String id;
    @NotEmpty
    private LocalDateTime fechaIngreso;
    @NotEmpty
    private PacienteReqDTO paciente;
}
