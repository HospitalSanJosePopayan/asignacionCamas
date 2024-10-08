package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private String genero;
    private String documento;
    private String nombreCompleto;
}
