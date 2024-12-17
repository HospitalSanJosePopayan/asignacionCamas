package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResDTO {

    private Integer idPersona;
    private String documento;
    private String nombreCompleto;
    private String genero;
    private LocalDate fechaNacimiento;

}
