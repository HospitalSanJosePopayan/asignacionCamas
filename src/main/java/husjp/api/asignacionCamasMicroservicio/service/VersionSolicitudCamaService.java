package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;

import java.util.List;

public interface VersionSolicitudCamaService {

    VersionSolicitudResponseDTO guardarVersionSolicitudCama(VersionSolicitudCamaDTO versionSolicitudCamaDTO, String username);
    List<VersionSolicitudResponseDTO> getVersionSolicitudCamaActivasEnEspera();
    VersionSolicitudResponseDTO editarVersionSolicitudCama(String id, VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO,String username);
    VersionSolicitudResponseDTO EstadoSolicitud(String id);

}
