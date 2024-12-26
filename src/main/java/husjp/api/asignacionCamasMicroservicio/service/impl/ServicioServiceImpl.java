package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Servicio;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.ServicioRepository;
import husjp.api.asignacionCamasMicroservicio.service.ServicioService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.ServicioResDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ServicioServiceImpl implements ServicioService {

    private ModelMapper mapper;
    private ServicioRepository servicioRepository;

    @Override
    public Servicio findById(Integer idServicio) {
        return servicioRepository.findById(idServicio).orElseThrow(() -> new EntidadNoExisteException("No existe el servicio con el identificador "+idServicio));
    }

    @Override
    public List<ServicioResDTO> findAllByBloqueServicioId(Integer idBloqueServicio) {
        return servicioRepository.findByBloqueServicio_Id(idBloqueServicio).stream()
                .map(entity -> mapper.map(entity, ServicioResDTO.class))
                .collect(Collectors.toList());
    }

}
