package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import husjp.api.asignacionCamasMicroservicio.entity.Servicio;
import husjp.api.asignacionCamasMicroservicio.entity.Usuario;
import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionRespuestaSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionAsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VersionAsignacionSolicitudCamaServiceImpl implements VersionAsignacionSolicitudCamaService {

    private final UsuarioRepository usuarioRepository;
    private ModelMapper mapper;
    private VersionRespuestaSolicitudCamaRepository versionRespuestaSolicitudCamaRepository;
    private AsignacionSolicitudCamaService asignacionSolicitudCamaService;

    @Override
    public VersionAsignacionCamaResponseDTO guardarVersionAsignacionCama(VersionAsignacionCamaDTO versionAsignacionCamaDTO, String username) {
        //Validar que la solicitud este vigente y exista si no existe lanzar excepcion
        //crear version de solicitud
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCama= crearRespuestaSolicitudCama(versionAsignacionCamaDTO, username);
        VersionAsignacionSolicitudCama r = versionRespuestaSolicitudCamaRepository.save(versionAsignacionSolicitudCama);

        return mapper.map(r, VersionAsignacionCamaResponseDTO.class);
    }

    private VersionAsignacionSolicitudCama crearRespuestaSolicitudCama(VersionAsignacionCamaDTO versionAsignacionCamaDTO,String username){
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCama = mapper.map(versionAsignacionCamaDTO, VersionAsignacionSolicitudCama.class);
        versionAsignacionSolicitudCama.setFechaModificacion(LocalDateTime.now());
        //agregar usuario
        Usuario usuario = usuarioRepository.findByDocumento(username).orElse(null);
        versionAsignacionSolicitudCama.setUsuario(usuario);
        versionAsignacionSolicitudCama.setFechaModificacion(LocalDateTime.now());
        //asignacion de cama
        versionAsignacionSolicitudCama.setAsignacionCama(asignacionSolicitudCamaService.crearAsignacionCamas(versionAsignacionCamaDTO.getAsignacionCama().getIdSolicitudCama(), versionAsignacionCamaDTO.getServicio().getId()));
        //asignamos id
        versionAsignacionSolicitudCama.setId(versionAsignacionSolicitudCama.getAsignacionCama().getId()+"-V1");
        return versionAsignacionSolicitudCama;
    }

    @Override
    public List<VersionAsignacionCamaResponseDTO> getAllVersionAsignacionCamaActivasEnEspera() {
        List<VersionAsignacionSolicitudCama> respuesta = versionRespuestaSolicitudCamaRepository.findAllByRespuestaSolicitudCamaEstadoActivo().orElse(null);
        assert respuesta != null;
        //return List.of(mapper.map(respuesta,VersionRespuestaSolicitudCamaResponseDTO.class));
        return respuesta.stream()
                .map(entity -> mapper.map(entity, VersionAsignacionCamaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VersionAsignacionCamaResponseDTO editarAsignacion(String id, VersionAsignacionCamaDTO versionAsignacionCamaDTO) {
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCamaActual = versionRespuestaSolicitudCamaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoExisteException("No Existe esta Asignacion"));
        System.out.println("ID actual: " + versionAsignacionSolicitudCamaActual.getId());
        VersionAsignacionSolicitudCama editarAsignacion = new VersionAsignacionSolicitudCama();
        BeanUtils.copyProperties(versionAsignacionSolicitudCamaActual, editarAsignacion);
        editarAsignacion.setObservacion(versionAsignacionCamaDTO.getObservacion());
        editarAsignacion.setEnfermero_origen(versionAsignacionCamaDTO.getEnfermero_origen());
        editarAsignacion.setEnfermero_destino(versionAsignacionCamaDTO.getEnfermero_destino());
        Servicio servicio = mapper.map(versionAsignacionCamaDTO.getServicio(), Servicio.class);
        editarAsignacion.setServicio(servicio);
        Cama cama = mapper.map(versionAsignacionCamaDTO.getCama(), Cama.class);
        editarAsignacion.setCama(cama);
        editarAsignacion.setFechaModificacion(LocalDateTime.now());
        String nuevoId = incrementarVersionId(versionAsignacionSolicitudCamaActual.getId());
        System.out.println("Versión nueva: " + nuevoId);
        System.out.println("este es mi nuevo objeto"+editarAsignacion);
        editarAsignacion.setId(nuevoId);
        versionRespuestaSolicitudCamaRepository.save(editarAsignacion);
        return mapper.map(editarAsignacion, VersionAsignacionCamaResponseDTO.class);
    }
    private String incrementarVersionId(String currentId) {
        if (!currentId.matches("^[A-Z]+(?: [A-Z]+)?-\\d+-V\\d+$")) {
            throw new IllegalArgumentException("Formato inválido del ID: " + currentId);
        }
        String[] partesId = currentId.split("-V");
        String parteFija = partesId[0];
        int numeroVersion = Integer.parseInt(partesId[1]);
        return parteFija + "-V" + (numeroVersion + 1);
    }

}
