package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VersionSolicitudSinSolAsigResDTO {
    private String id;
    private Boolean requiereAislamiento;
    private Boolean estado;
    private String motivo;
    private String autorizacionFacturacion;
    private String requerimientosEspeciales;
    private LocalDateTime fecha;
    private UsuarioResDTO usuario;
    private List<MedidasAislamientoResDTO> medidasAislamiento;
    private List<TitulosFormacionAcacemicaResDTO> titulosFormacionAcademica;
    private List<DiagnosticoResDTO> diagnosticos;
    private ServicioResDTO servicio;
    private CamaResDTO cama;
    private BloqueServicioResDTO bloqueServicio;
}
