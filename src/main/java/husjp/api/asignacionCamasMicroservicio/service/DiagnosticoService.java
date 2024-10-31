package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.DiagnosticoResponseDTO;

import java.util.List;

public interface DiagnosticoService {

    List<DiagnosticoResponseDTO> findByIdOrNombre(String idOrName);
}
