package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteReqDTO {

    private String genero;
    private String documento;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String nombres;
    private String apellidos;

}
