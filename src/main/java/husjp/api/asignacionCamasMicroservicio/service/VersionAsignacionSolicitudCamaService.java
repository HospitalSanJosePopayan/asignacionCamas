package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionSolicitudCamaResDTO;

import java.util.List;

public interface VersionAsignacionSolicitudCamaService {

    VersionAsignacionCamaResDTO guardarVersionAsignacionCama(VersionAsignacionCamaReqDTO versionAsignacionCamaReqDTO, String username);
    List<VersionAsignacionSolicitudCamaResDTO> getVersionAsignacionSolicitudCamaByBloque(Integer bloqueServicioId);
    VersionAsignacionSolicitudCamaResDTO editarAsignacion(String id, VersionAsignacionCamaEditDTO versionAsignacionCamaEditDTO, String username);

}
