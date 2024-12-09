package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResponseDTO;

import java.util.List;

public interface VersionAsignacionSolicitudCamaService {

    VersionAsignacionCamaResponseDTO guardarVersionAsignacionCama(VersionAsignacionCamaDTO versionAsignacionCamaDTO, String username);
    List<VersionAsignacionCamaResponseDTO> getAllVersionAsignacionCamaActivasEnEsperaByIdBloque(Integer idBloqueServicio);
    VersionAsignacionCamaResponseDTO editarAsignacion(String id, VersionAsignacionCamaEditDTO versionAsignacionCamaEditDTO, String username);
}
