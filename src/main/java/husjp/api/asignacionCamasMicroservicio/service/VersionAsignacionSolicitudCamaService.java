package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResDTO;

import java.util.List;

public interface VersionAsignacionSolicitudCamaService {

    VersionAsignacionCamaResDTO guardarVersionAsignacionCama(VersionAsignacionCamaReqDTO versionAsignacionCamaReqDTO, String username);
    List<VersionAsignacionCamaResDTO> getAllVersionAsignacionCamaActivasEnEsperaByIdBloque(Integer idBloqueServicio);
    VersionAsignacionCamaResDTO editarAsignacion(String id, VersionAsignacionCamaEditDTO versionAsignacionCamaEditDTO, String username);

}
