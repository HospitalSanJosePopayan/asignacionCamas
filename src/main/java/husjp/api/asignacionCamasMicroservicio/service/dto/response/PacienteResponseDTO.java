package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

@Data
public class PacienteResponseDTO {

    private Integer idPersona;
    private String documento;
    private String nombreCompleto;
    private String genero;

}
