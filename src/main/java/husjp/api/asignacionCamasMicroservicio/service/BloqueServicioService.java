package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.BloqueServicioResponseDTO;

import java.util.List;

public interface BloqueServicioService {

    List<BloqueServicioResponseDTO> findAll();

}
