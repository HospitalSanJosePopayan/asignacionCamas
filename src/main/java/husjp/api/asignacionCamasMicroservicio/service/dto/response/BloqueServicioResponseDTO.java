package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BloqueServicioResponseDTO {

    private Integer id;
    private String nombre;
    private List<ServicioResDTO> servicios;
}
