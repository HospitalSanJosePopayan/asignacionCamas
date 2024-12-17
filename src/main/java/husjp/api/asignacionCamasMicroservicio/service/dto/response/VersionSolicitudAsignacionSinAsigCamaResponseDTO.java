package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionSolicitudAsignacionSinAsigCamaResponseDTO {

    private String id;
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
