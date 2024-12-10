package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VersionAsignacionCamaResponseDTO {

    private String id;
    private AsignacionCamaResponseDTO asignacionCama;
    private CamaResponseDTO cama;
    private UsuarioResponseDTO usuario;
    private String observacion;
    private String enfermero_origen;
    private String enfermero_destino;
    private String extension;
    private String motivo_cancelacion;
    private LocalDateTime fechaCreacion;
    private ServicioResponseDTO servicio;

}
