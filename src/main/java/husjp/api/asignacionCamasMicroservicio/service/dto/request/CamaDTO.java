package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CamaDTO {

    private Integer id;
    @NotEmpty
    private String codigo;

}
