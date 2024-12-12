package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Usuario;
import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionRespuestaSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionAsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResponseDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionSolicitudResponseDTO;
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
    private VersionSolicitudCamaService versionSolicitudCamaService;

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
        versionAsignacionSolicitudCama.setFechaCreacion(LocalDateTime.now());
        //agregar usuario
        Usuario usuario = usuarioRepository.findByDocumento(username).orElse(null);
        versionAsignacionSolicitudCama.setUsuario(usuario);
        versionAsignacionSolicitudCama.setFechaCreacion(LocalDateTime.now());
        //asignacion de cama
        versionAsignacionSolicitudCama.setAsignacionCama(asignacionSolicitudCamaService.crearAsignacionCamas(versionAsignacionCamaDTO.getAsignacionCama().getIdSolicitudCama(), versionAsignacionCamaDTO.getServicio().getId()));
        //asignamos id
        versionAsignacionSolicitudCama.setId(versionAsignacionSolicitudCama.getAsignacionCama().getId()+"-V1");
        return versionAsignacionSolicitudCama;
    }

    @Override
    public List<VersionAsignacionCamaResponseDTO> getAllVersionAsignacionCamaActivasEnEsperaByIdBloque(Integer idBloqueServicio) {
        List<VersionAsignacionSolicitudCama> respuesta = versionRespuestaSolicitudCamaRepository.findByRespuestaSolicitudCamaEstadoActivoPorBloque(idBloqueServicio, EstadoSolicitudCama.ACEPTADA.getId()).orElse(null);
        assert respuesta != null;
        //return List.of(mapper.map(respuesta,VersionRespuestaSolicitudCamaResponseDTO.class));
        List<VersionAsignacionCamaResponseDTO> res = respuesta.stream()
                .map(entity -> mapper.map(entity, VersionAsignacionCamaResponseDTO.class))
                .collect(Collectors.toList());

        //obtenemos la información de la ultima versión que tiene la solicitud de cama para tener la información completa
        for (VersionAsignacionCamaResponseDTO versionAsignacionCamaResponseDTO : res) {
            versionAsignacionCamaResponseDTO.getAsignacionCama().getSolicitudCama().setVersionSolicitud(null);
            versionAsignacionCamaResponseDTO.getAsignacionCama().getSolicitudCama().setVersionSolicitud(List.of(versionSolicitudCamaService.findEndVersionByIdSolicitudCama(versionAsignacionCamaResponseDTO.getAsignacionCama().getSolicitudCama().getId())));
        }

        return res;
    }

    @Override
    public VersionAsignacionCamaResponseDTO editarAsignacion(String id, VersionAsignacionCamaEditDTO versionAsignacionCamaEditDTO, String username) {
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCamaActual = versionRespuestaSolicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("No Existe esta Asignacion"));
        VersionAsignacionSolicitudCama editarAsignacion = new VersionAsignacionSolicitudCama();
        BeanUtils.copyProperties(versionAsignacionSolicitudCamaActual, editarAsignacion);
        editarAsignacion = mapper.map(versionAsignacionCamaEditDTO, VersionAsignacionSolicitudCama.class);
        editarAsignacion.setId(incrementarVersionId(versionAsignacionSolicitudCamaActual.getId()));
        editarAsignacion.setFechaCreacion(LocalDateTime.now());
        editarAsignacion.setAsignacionCama(versionAsignacionSolicitudCamaActual.getAsignacionCama());
        editarAsignacion.setUsuario(usuarioRepository.findByDocumento(username).orElse(null));
        versionRespuestaSolicitudCamaRepository.save(editarAsignacion);
        return mapper.map(editarAsignacion, VersionAsignacionCamaResponseDTO.class);
    }
    @Override
    public VersionAsignacionCamaResponseDTO cambiarEstado(String id) {
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCama = versionRespuestaSolicitudCamaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoExisteException("No Existe esta Asignación"));
        versionAsignacionSolicitudCama.getAsignacionCama().setEstado(EstadoSolicitudCama.FINALIZADA.toEntity());
        versionAsignacionSolicitudCama.getAsignacionCama().getSolicitudCama().setEstado(EstadoSolicitudCama.FINALIZADA.toEntity());
        versionRespuestaSolicitudCamaRepository.save(versionAsignacionSolicitudCama);
        return mapper.map(versionAsignacionSolicitudCama, VersionAsignacionCamaResponseDTO.class);
    }


    @Override
    public VersionAsignacionCamaResponseDTO cambiarEstadoCancelada(String id, String motivoCancelar) {
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCama = versionRespuestaSolicitudCamaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoExisteException("No Existe esta Asignación"));
        versionAsignacionSolicitudCama.getAsignacionCama().setEstado(EstadoSolicitudCama.CANCELADA.toEntity());
        versionAsignacionSolicitudCama.setMotivo_cancelacion(motivoCancelar);
        versionAsignacionSolicitudCama.getAsignacionCama().getSolicitudCama().setEstado(EstadoSolicitudCama.EN_ESPERA.toEntity());
        versionRespuestaSolicitudCamaRepository.save(versionAsignacionSolicitudCama);
        return mapper.map(versionAsignacionSolicitudCama, VersionAsignacionCamaResponseDTO.class);
    }

    private String incrementarVersionId(String currentId) {
//        if (!currentId.matches("^[A-Z]+(?: [A-Z]+)?-\\d+-V\\d+$")) {
//            throw new IllegalArgumentException("Formato inválido del ID: " + currentId);
//        }
        String[] partesId = currentId.split("-V");
        String parteFija = partesId[0];
        int numeroVersion = Integer.parseInt(partesId[1]);
        return parteFija + "-V" + (numeroVersion + 1);
    }

}
