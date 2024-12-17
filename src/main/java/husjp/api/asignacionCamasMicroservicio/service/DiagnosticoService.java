package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.DiagnosticoResDTO;

import java.util.List;

public interface DiagnosticoService {

    List<DiagnosticoResDTO> findByIdOrNombre(String idOrName);
}
