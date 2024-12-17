package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.MedidasAislamientoResDTO;

import java.util.List;

public interface MedidasAislamientoService {

    List<MedidasAislamientoResDTO> findAll ();
}
