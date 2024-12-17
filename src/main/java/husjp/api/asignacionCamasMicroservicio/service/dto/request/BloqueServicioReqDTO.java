package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BloqueServicioReqDTO {

    @NotEmpty(message = "El campo id no puede estar vacio")
    @NotNull(message = "El campo id no puede ser nulo")
    private Integer id;
}
