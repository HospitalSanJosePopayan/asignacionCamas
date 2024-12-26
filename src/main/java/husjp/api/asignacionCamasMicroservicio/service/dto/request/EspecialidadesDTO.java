package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EspecialidadesDTO {

    @NotEmpty
    private Integer id;

}
