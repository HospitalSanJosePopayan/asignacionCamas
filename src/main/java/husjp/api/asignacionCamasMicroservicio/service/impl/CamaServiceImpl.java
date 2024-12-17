package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.CamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.CamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.CamaSimpleResDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CamaServiceImpl implements CamaService {

    private ModelMapper mapper;
    private CamaRepository camaRepository;

    @Override
    public Cama findByCodigo(String codigo) {
        return camaRepository.findByCodigo(codigo).orElseThrow(() -> new EntidadNoExisteException("No se encuentra cama con codigo: " + codigo));
    }

    @Override
    public List<CamaSimpleResDTO> findAllByServicioId(Integer idServicio) {
        List<Cama> res = camaRepository.findByServicioId(idServicio).orElseThrow(() -> new EntidadNoExisteException("No se encuentran camas para el servicio con id: " + idServicio));
        return res.stream()
                .map(entity -> mapper.map(entity, CamaSimpleResDTO.class))
                .collect(Collectors.toList());
    }

}
