package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;

import java.util.List;

public interface VersionSolicitudCamaService {

    VersionSolicitudResponseDTO guardarVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO);
    List<VersionSolicitudResponseDTO> getVersionSolicitudCamaActivasEnEspera();

}
