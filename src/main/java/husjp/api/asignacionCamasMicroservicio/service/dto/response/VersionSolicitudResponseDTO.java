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
    private String otraEspecialidad;
    private String autorizacionFacturacion;
    private LocalDateTime fecha;
    private UsuarioResponseDTO usuario;
    private SolicitudCamaResponseDTO solicitudCama;
    private List<MedidasAislamientoResponseDTO> medidasAislamiento;
    private List<TitulosFormacionAcacemicaResponseDTO> titulosFormacionAcademica;
    private List<DiagnosticoResponseDTO> diagnosticos;
    private SubServicioResponseDTO subservicio;
    private CamaResponseDTO cama;
}
