package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoResponseDTO {

    private String id;
    private LocalDateTime fechaIngreso;
    private PacienteResponseDTO paciente;
}
