package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.repository.TitulosFormacionAcademicaRepository;
import husjp.api.asignacionCamasMicroservicio.service.TitulosFormacionAcademicaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.TitulosFormacionAcacemicaResDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TitulosFormacionAcademicaServiceImpl implements TitulosFormacionAcademicaService {

    private ModelMapper mapper;
    private TitulosFormacionAcademicaRepository titulosFormacionAcademicaRepository;

    @Override
    public List<TitulosFormacionAcacemicaResDTO> findAllByEspecialidad() {

        return titulosFormacionAcademicaRepository.findAll().stream()
                .map(entity -> mapper.map(entity, TitulosFormacionAcacemicaResDTO.class ))
                .collect(Collectors.toList());
    }

}
