package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VersionAsignacionSolicitudCamaResDTO {
    private String id;
    private AsignacionCamaResVersionDTO asignacionCama;
    private CamaResDTO cama;
    private UsuarioResDTO usuario;
    private String observacion;
    private String enfermeroOrigen;
    private String enfermeroDestino;
    private String extension;
    private String motivoCancelacion;
    private LocalDateTime fechaCreacion;
    private ServicioResDTO servicio;
}
