package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamaResponseDTO {

    private Integer id;
    private String nombre;
    private String codigo;
    private ServicioResponseDTO servicio;
    private SubSeccionesServicioResponseDTO subseccion;
    private CamaEstadoResponseDTO camaEstado;
}
