package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionSolicitudResponseDTO {

    private String id;
    private Boolean requiereAislamiento;
    private String motivo;
    private String autorizacionFacturacion;
    private String requerimientosEspeciales;
    private LocalDateTime fecha;
    private UsuarioResponseDTO usuario;
    private SolicitudCamaResponseDTO solicitudCama;
    private List<MedidasAislamientoResponseDTO> medidasAislamiento;
    private List<TitulosFormacionAcacemicaResponseDTO> titulosFormacionAcademica;
    private List<DiagnosticoResponseDTO> diagnosticos;
    private ServicioResponseDTO servicio;
    private CamaResponseDTO cama;
    private BloqueServicioDTO bloqueServicio;
    private String versionAsignacionId;  // ID de la versión de asignación
    private String motivoCancelacion;  // De version_asignacion_solicitud_cama
    private LocalDateTime fechaAsignacion;  // De version_asignacion_solicitud_cama
    private String estadoAsignacion;  // De version_asignacion_solicitud_cama
    private LocalDateTime fechaCancelacion;  // De version_asignacion_solicitud_cama
}
