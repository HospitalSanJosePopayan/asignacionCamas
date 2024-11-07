package husjp.api.asignacionCamasMicroservicio.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCamilleroResponseDTO {

    private Integer id;
    private String recomendaciones;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaTraslado;
    private LocalDateTime fechaFinal;
    private String nombreCamillero;
    private IngresoResponseDTO ingreso;
    private ServicioResponseDTO servicioOrigen;
    private SeccionServicioResponseDTO seccionOrigen;
    private SubSeccionServicioResponseDTO subSeccionOrigen;
    private List<MedidasAislamientoResponseDTO> medidasAislamiento;
    private EstadoSolicitudCamilleroResponseDTO estado;
    private UsuarioResponseDTO usuarioJefeServicio;

}
