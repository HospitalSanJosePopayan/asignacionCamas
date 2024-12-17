package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Diagnostico;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.DiagnosticoRepository;
import husjp.api.asignacionCamasMicroservicio.service.DiagnosticoService;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.DiagnosticoResDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    private ModelMapper mapper;
    private DiagnosticoRepository diagnosticoRepository;

    @Override
    public List<DiagnosticoResDTO> findByIdOrNombre(String idOrName) {
        List<Diagnostico> response = diagnosticoRepository.findByIdOrNombre(idOrName).orElse(null);
        if(response == null) {
            throw new EntidadNoExisteException("No se encontraron diagnósticos con el nombre o id: " + idOrName);
        }

        return response.stream()
                .map(entity -> mapper.map(entity, DiagnosticoResDTO.class))
                .collect(Collectors.toList());
    }
}
