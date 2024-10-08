package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Integer idPersona;
    private String documento;
    private String nombreCompleto;
}
