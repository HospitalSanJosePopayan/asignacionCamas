package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.MedidasAislamientoResponseDTO;

import java.util.List;

public interface MedidasAislamientoService {

    List<MedidasAislamientoResponseDTO> findAll ();
}
