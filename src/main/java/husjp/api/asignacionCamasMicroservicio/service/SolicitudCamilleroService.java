package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.request.SolicitudCamilleroDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.SolicitudCamilleroResponseDTO;

public interface SolicitudCamilleroService {

    SolicitudCamilleroResponseDTO save (SolicitudCamilleroDTO solicitudCamilleroDTO, String username);
}
