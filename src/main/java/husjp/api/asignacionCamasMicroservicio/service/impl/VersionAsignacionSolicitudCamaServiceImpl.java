package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.Usuario;
import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.VersionSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionAsignacionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.AsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionAsignacionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.VersionSolicitudCamaService;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaReqDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.VersionAsignacionCamaEditDTO;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VersionAsignacionSolicitudCamaServiceImpl implements VersionAsignacionSolicitudCamaService {

    private final UsuarioRepository usuarioRepository;
    private ModelMapper mapper;
    private VersionAsignacionSolicitudCamaRepository versionAsignacionSolicitudCamaRepository;
    private VersionSolicitudCamaRepository versionSolicitudCamaRepository;
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

    @Override
    public List<VersionAsignacionSolicitudCamaResDTO> getVersionAsignacionSolicitudCamaByBloque(Integer bloqueServicioId) {
        List<VersionAsignacionSolicitudCama> response = versionAsignacionSolicitudCamaRepository.findVersionAsignacionSolicitudCamaByEstadoAceptadaAndBloqueNative(EstadoSolicitudCama.ACEPTADA.getId(),bloqueServicioId);
        assert response != null;
        List<VersionAsignacionSolicitudCamaResDTO> listResponse = new ArrayList<>();
        for (VersionAsignacionSolicitudCama versionAsigSolCamaEntity : response) {
            VersionAsignacionSolicitudCamaResDTO versionAsigSolCamaResDTO = new VersionAsignacionSolicitudCamaResDTO();
            versionAsigSolCamaResDTO.setId(versionAsigSolCamaEntity.getId());
            versionAsigSolCamaResDTO.setAsignacionCama(mapper.map(versionAsigSolCamaEntity.getAsignacionSolicitudCama(), AsignacionCamaResDTOSinVersionesSolDTO.class));
            versionAsigSolCamaResDTO.setCama(mapper.map(versionAsigSolCamaEntity.getCama(), CamaResDTO.class));
            versionAsigSolCamaResDTO.setUsuario(mapper.map(versionAsigSolCamaEntity.getUsuario(), UsuarioResDTO.class));
            versionAsigSolCamaResDTO.setObservacion(versionAsigSolCamaEntity.getObservacion());
            versionAsigSolCamaResDTO.setEnfermeroOrigen(versionAsigSolCamaEntity.getEnfermero_origen());
            versionAsigSolCamaResDTO.setEnfermeroDestino(versionAsigSolCamaEntity.getEnfermero_destino());
            versionAsigSolCamaResDTO.setExtension(versionAsigSolCamaEntity.getExtension());
            versionAsigSolCamaResDTO.setMotivoCancelacion(versionAsigSolCamaEntity.getMotivo_cancelacion());
            versionAsigSolCamaResDTO.setFechaCreacion(versionAsigSolCamaEntity.getFechaCreacion());
            versionAsigSolCamaResDTO.setServicio(mapper.map(versionAsigSolCamaEntity.getServicio(), ServicioResDTO.class));
            VersionSolicitudCama versionSolicitudfinal = versionSolicitudCamaRepository.findLastVersionByAsignacionCamaId(versionAsigSolCamaEntity.getAsignacionSolicitudCama().getId()).orElseThrow(null);
            if (versionSolicitudfinal != null) {
                VersionSolicitudSinAsignacionDTO versionSolicitufinaldto = new VersionSolicitudSinAsignacionDTO();
                versionSolicitufinaldto.setId(versionSolicitudfinal.getId());
                versionSolicitufinaldto.setRequiereAislamiento(versionSolicitudfinal.getRequiereAislamiento());
                versionSolicitufinaldto.setEstado(versionSolicitudfinal.getEstado());
                versionSolicitufinaldto.setMotivo(versionSolicitudfinal.getMotivo());
                versionSolicitufinaldto.setAutorizacionFacturacion(versionSolicitudfinal.getAutorizacionFacturacion());
                versionSolicitufinaldto.setRequerimientosEspeciales(versionSolicitudfinal.getRequerimientosEspeciales());
                versionSolicitufinaldto.setFecha(versionSolicitudfinal.getFecha());
                versionSolicitufinaldto.setUsuario(mapper.map(versionSolicitudfinal.getUsuario(), UsuarioResDTO.class));
                versionSolicitufinaldto.setSolicitudCama(mapper.map(versionSolicitudfinal.getSolicitudCama(), SolicitudCamaResSinVersionesDTO.class));
                versionSolicitufinaldto.setMedidasAislamiento(versionSolicitudfinal.getMedidasAislamiento().stream().map(medidasAislamiento -> mapper.map(medidasAislamiento, MedidasAislamientoResDTO.class)).collect(Collectors.toList()));
                versionSolicitufinaldto.setTitulosFormacionAcademica(versionSolicitudfinal.getTitulosFormacionAcademica().stream().map(titulosFormacionAcacemica -> mapper.map(titulosFormacionAcacemica, TitulosFormacionAcacemicaResDTO.class)).collect(Collectors.toList()));
                versionSolicitufinaldto.setDiagnosticos(versionSolicitudfinal.getDiagnosticos().stream().map(diagnostico -> mapper.map(diagnostico, DiagnosticoResDTO.class)).collect(Collectors.toList()));
                versionSolicitufinaldto.setServicio(mapper.map(versionSolicitudfinal.getServicio(), ServicioResDTO.class));
                versionSolicitufinaldto.setCama(mapper.map(versionSolicitudfinal.getCama(), CamaResDTO.class));
                versionSolicitufinaldto.setBloqueServicio(mapper.map(versionSolicitudfinal.getBloqueServicio(), BloqueServicioResDTO.class));
                versionAsigSolCamaResDTO.setVersionSolicitud(versionSolicitufinaldto);
            }
            listResponse.add(versionAsigSolCamaResDTO);
        }

        return listResponse;
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
