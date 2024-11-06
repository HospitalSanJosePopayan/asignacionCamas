package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.BloqueServicio;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
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
        // Se omite el mapeo de la lista de servicios
        var typeMap = mapper.typeMap(BloqueServicio.class, BloqueServicioResponseDTO.class);
        typeMap.addMappings(mapper -> mapper.skip(BloqueServicioResponseDTO::setServicios));
        List<BloqueServicioResponseDTO> result =  bloqueServicioRepository.findAll().stream()
                .map(entity -> mapper.map(entity, BloqueServicioResponseDTO.class))
                .collect(Collectors.toList());
        mapper.getTypeMap(BloqueServicio.class, BloqueServicioResponseDTO.class).addMappings(m -> m.map(BloqueServicio::getServicios, BloqueServicioResponseDTO::setServicios));
        return result;
    }

    @Override
    public BloqueServicioResponseDTO findAllServiciosByBloque(Integer idBloque) {
        return mapper.map(bloqueServicioRepository.findById(idBloque).orElseThrow(() -> new EntidadNoExisteException("no se encontro el bloque con id "+idBloque)), BloqueServicioResponseDTO.class);
    }

}
