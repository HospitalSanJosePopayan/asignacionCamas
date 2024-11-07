package husjp.api.asignacionCamasMicroservicio.service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SolicitudCamilleroDTO {

    private String recomendaciones;
    private IngresoDTO ingreso;
    private ServicioDTO servicioOrigen;
    private SeccionServicioDTO seccionOrigen;
    private SubSeccionServicioDTO subSeccionOrigen;
    private List<MedidasAislamientoDTO> medidasAislamiento;
    private String nombreCamillero;

}
