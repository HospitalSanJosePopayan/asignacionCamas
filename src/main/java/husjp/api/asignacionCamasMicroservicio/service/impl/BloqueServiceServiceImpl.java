package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.repository.BloqueServicioRepository;
import husjp.api.asignacionCamasMicroservicio.service.BloqueServicioService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.BloqueServicioResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BloqueServiceServiceImpl implements BloqueServicioService {

    private ModelMapper mapper;
    private BloqueServicioRepository bloqueServicioRepository;

    @Override
    public List<BloqueServicioResponseDTO> findAll() {
        return bloqueServicioRepository.findAll().stream()
                .map(entity -> mapper.map(entity, BloqueServicioResponseDTO.class))
                .collect(Collectors.toList());
    }

}
