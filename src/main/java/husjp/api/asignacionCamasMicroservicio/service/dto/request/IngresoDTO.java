package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngresoDTO {

    private String id;
    private LocalDateTime fechaIngreso;
    private PacienteDTO paciente;
}
