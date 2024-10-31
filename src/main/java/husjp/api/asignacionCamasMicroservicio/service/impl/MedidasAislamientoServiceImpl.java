package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.repository.MedidasAislamientoRepository;
import husjp.api.asignacionCamasMicroservicio.service.MedidasAislamientoService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.MedidasAislamientoResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MedidasAislamientoServiceImpl implements MedidasAislamientoService {

    private ModelMapper mapper;
    private MedidasAislamientoRepository medidasAislamientoRepository;

    @Override
    public List<MedidasAislamientoResponseDTO> findAll() {
        return medidasAislamientoRepository.findAll().stream()
                .map(entity -> mapper.map(entity, MedidasAislamientoResponseDTO.class))
                .collect(Collectors.toList());
    }
}
