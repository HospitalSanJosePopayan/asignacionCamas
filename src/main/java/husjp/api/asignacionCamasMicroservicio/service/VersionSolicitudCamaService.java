package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionSolicitudCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResDTO;

import java.util.List;

public interface VersionSolicitudCamaService {

    VersionSolicitudResDTO guardarVersionSolicitudCama(VersionSolicitudCamaReqDTO versionSolicitudCamaReqDTO, String username);
    List<VersionSolicitudResDTO> getVersionSolicitudCamaActivasEnEsperaByIdBloque(Integer idBloqueServicio);
    VersionSolicitudResDTO editarVersionSolicitudCama(String id, VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO, String username);
    VersionSolicitudResDTO EstadoSolicitud(String id);
    VersionSolicitudResDTO findEndVersionByIdSolicitudCama(String id);
    VersionSolicitudResDTO CambiarEstadoCanceladaSolicitud(String id, String motivoCancelar);
    VersionSolicitudResDTO obtenerVerionSolicitudCamaActivaPorIngreso(String ingreso);
}
