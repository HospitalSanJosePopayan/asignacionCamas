package husjp.api.asignacionCamasMicroservicio.service;

import husjp.api.asignacionCamasMicroservicio.service.dto.response.TitulosFormacionAcacemicaResponseDTO;

import java.util.List;

public interface TitulosFormacionAcademicaService {

    List<TitulosFormacionAcacemicaResponseDTO> findAllByEspecialidad();
}
