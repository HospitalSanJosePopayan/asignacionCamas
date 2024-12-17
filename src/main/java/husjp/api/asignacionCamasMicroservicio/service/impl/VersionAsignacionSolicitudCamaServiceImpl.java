package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Usuario;
import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionAsignacionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionAsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.VersionAsignacionCamaResDTO;
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
    private VersionAsignacionSolicitudCamaRepository versionAsignacionSolicitudCamaRepository;
    private AsignacionSolicitudCamaService asignacionSolicitudCamaService;
    private VersionSolicitudCamaService versionSolicitudCamaService;

    @Override
    public VersionAsignacionCamaResDTO guardarVersionAsignacionCama(VersionAsignacionCamaReqDTO versionAsignacionCamaReqDTO, String username) {
        //Validar que la solicitud este vigente y exista si no existe lanzar excepcion
        //crear version de solicitud
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCama= crearRespuestaSolicitudCama(versionAsignacionCamaReqDTO, username);
        VersionAsignacionSolicitudCama r = versionAsignacionSolicitudCamaRepository.save(versionAsignacionSolicitudCama);

        return mapper.map(r, VersionAsignacionCamaResDTO.class);
    }

    private VersionAsignacionSolicitudCama crearRespuestaSolicitudCama(VersionAsignacionCamaReqDTO versionAsignacionCamaReqDTO, String username){
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCama = mapper.map(versionAsignacionCamaReqDTO, VersionAsignacionSolicitudCama.class);
        versionAsignacionSolicitudCama.setFechaCreacion(LocalDateTime.now());
        //agregar usuario
        Usuario usuario = usuarioRepository.findByDocumento(username).orElse(null);
        versionAsignacionSolicitudCama.setUsuario(usuario);
        versionAsignacionSolicitudCama.setFechaCreacion(LocalDateTime.now());
        //asignacion de cama
        versionAsignacionSolicitudCama.setAsignacionSolicitudCama(asignacionSolicitudCamaService.crearAsignacionCamas(versionAsignacionCamaReqDTO.getAsignacionCama().getIdSolicitudCama(), versionAsignacionCamaReqDTO.getServicio().getId()));
        //asignamos id
        versionAsignacionSolicitudCama.setId(versionAsignacionSolicitudCama.getAsignacionSolicitudCama().getId()+"-V1");
        return versionAsignacionSolicitudCama;
    }

    @Override
    public List<VersionAsignacionCamaResDTO> getAllVersionAsignacionCamaActivasEnEsperaByIdBloque(Integer idBloqueServicio) {
        List<VersionAsignacionSolicitudCama> respuesta = versionAsignacionSolicitudCamaRepository.findByRespuestaSolicitudCamaEstadoActivoPorBloque(idBloqueServicio, EstadoSolicitudCama.ACEPTADA.getId()).orElse(null);
        assert respuesta != null;
        //return List.of(mapper.map(respuesta,VersionRespuestaSolicitudCamaResponseDTO.class));
        List<VersionAsignacionCamaResDTO> res = respuesta.stream()
                .map(entity -> mapper.map(entity, VersionAsignacionCamaResDTO.class))
                .collect(Collectors.toList());

        //obtenemos la información de la ultima versión que tiene la solicitud de cama para tener la información completa
        for (VersionAsignacionCamaResDTO versionAsignacionCamaResDTO : res) {
            versionAsignacionCamaResDTO.getAsignacionCama().getSolicitudCama().setVersionSolicitud(null);
            versionAsignacionCamaResDTO.getAsignacionCama().getSolicitudCama().setVersionSolicitud(List.of(versionSolicitudCamaService.findEndVersionByIdSolicitudCama(versionAsignacionCamaResDTO.getAsignacionCama().getSolicitudCama().getId())));
        }

        return res;
    }

    @Override
    public VersionAsignacionCamaResDTO editarAsignacion(String id, VersionAsignacionCamaEditDTO versionAsignacionCamaEditDTO, String username) {
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCamaActual = versionAsignacionSolicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("No Existe esta Asignacion"));
        VersionAsignacionSolicitudCama editarAsignacion = new VersionAsignacionSolicitudCama();
        BeanUtils.copyProperties(versionAsignacionSolicitudCamaActual, editarAsignacion);
        editarAsignacion = mapper.map(versionAsignacionCamaEditDTO, VersionAsignacionSolicitudCama.class);
        editarAsignacion.setId(incrementarVersionId(versionAsignacionSolicitudCamaActual.getId()));
        editarAsignacion.setFechaCreacion(LocalDateTime.now());
        editarAsignacion.setAsignacionSolicitudCama(versionAsignacionSolicitudCamaActual.getAsignacionSolicitudCama());
        editarAsignacion.setUsuario(usuarioRepository.findByDocumento(username).orElse(null));
        versionAsignacionSolicitudCamaRepository.save(editarAsignacion);
        return mapper.map(editarAsignacion, VersionAsignacionCamaResDTO.class);
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
