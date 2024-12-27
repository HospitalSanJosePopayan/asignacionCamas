package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.*;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.repository.*;
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
            versionAsigSolCamaResDTO.setAsignacionCama(mapper.map(versionAsigSolCamaEntity.getAsignacionSolicitudCama(), AsignacionCamaResVersionDTO.class));
            versionAsigSolCamaResDTO.setCama(mapper.map(versionAsigSolCamaEntity.getCama(), CamaResDTO.class));
            versionAsigSolCamaResDTO.setUsuario(mapper.map(versionAsigSolCamaEntity.getUsuario(), UsuarioResDTO.class));
            versionAsigSolCamaResDTO.setObservacion(versionAsigSolCamaEntity.getObservacion());
            versionAsigSolCamaResDTO.setEnfermeroOrigen(versionAsigSolCamaEntity.getEnfermeroOrigen());
            versionAsigSolCamaResDTO.setEnfermeroDestino(versionAsigSolCamaEntity.getEnfermeroDestino());
            versionAsigSolCamaResDTO.setExtension(versionAsigSolCamaEntity.getExtension());
            versionAsigSolCamaResDTO.setMotivoCancelacion(versionAsigSolCamaEntity.getMotivo_cancelacion());
            versionAsigSolCamaResDTO.setFechaCreacion(versionAsigSolCamaEntity.getFechaCreacion());
            versionAsigSolCamaResDTO.setServicio(mapper.map(versionAsigSolCamaEntity.getServicio(), ServicioResDTO.class));
            VersionSolicitudCama versionSolicitudfinal = versionSolicitudCamaRepository.findLastVersionByAsignacionCamaId(versionAsigSolCamaEntity.getAsignacionSolicitudCama().getId()).orElseThrow(null);
            if (versionSolicitudfinal != null) {
                VersionSolicitudSinSolAsigResDTO versionSolicitufinaldto = new VersionSolicitudSinSolAsigResDTO();
                versionSolicitufinaldto.setId(versionSolicitudfinal.getId());
                versionSolicitufinaldto.setRequiereAislamiento(versionSolicitudfinal.getRequiereAislamiento());
                versionSolicitufinaldto.setEstado(versionSolicitudfinal.getEstado());
                versionSolicitufinaldto.setMotivo(versionSolicitudfinal.getMotivo());
                versionSolicitufinaldto.setAutorizacionFacturacion(versionSolicitudfinal.getAutorizacionFacturacion());
                versionSolicitufinaldto.setRequerimientosEspeciales(versionSolicitudfinal.getRequerimientosEspeciales());
                versionSolicitufinaldto.setFecha(versionSolicitudfinal.getFecha());
                versionSolicitufinaldto.setUsuario(mapper.map(versionSolicitudfinal.getUsuario(), UsuarioResDTO.class));
                versionSolicitufinaldto.setMedidasAislamiento(versionSolicitudfinal.getMedidasAislamiento().stream().map(medidasAislamiento -> mapper.map(medidasAislamiento, MedidasAislamientoResDTO.class)).collect(Collectors.toList()));
                versionSolicitufinaldto.setTitulosFormacionAcademica(versionSolicitudfinal.getTitulosFormacionAcademica().stream().map(titulosFormacionAcacemica -> mapper.map(titulosFormacionAcacemica, TitulosFormacionAcacemicaResDTO.class)).collect(Collectors.toList()));
                versionSolicitufinaldto.setDiagnosticos(versionSolicitudfinal.getDiagnosticos().stream().map(diagnostico -> mapper.map(diagnostico, DiagnosticoResDTO.class)).collect(Collectors.toList()));
                versionSolicitufinaldto.setServicio(mapper.map(versionSolicitudfinal.getServicio(), ServicioResDTO.class));
                versionSolicitufinaldto.setCama(mapper.map(versionSolicitudfinal.getCama(), CamaResDTO.class));
                versionSolicitufinaldto.setBloqueServicio(mapper.map(versionSolicitudfinal.getBloqueServicio(), BloqueServicioResDTO.class));
                versionAsigSolCamaResDTO.getAsignacionCama().getSolicitudCama().setVersionSolicitud(versionSolicitufinaldto);
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
        versionAsignacionSolicitudCama.setEstado(true);
        versionAsignacionSolicitudCama.setId(versionAsignacionSolicitudCama.getAsignacionSolicitudCama().getId()+"-V1");
        return versionAsignacionSolicitudCama;
    }



    @Override
    public VersionAsignacionSolicitudCamaResDTO editarAsignacion(String id, VersionAsignacionCamaEditDTO versionAsignacionCamaEditDTO, String username) {
        VersionAsignacionSolicitudCama versionAsignacionSolicitudCamaActual = versionAsignacionSolicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("No Existe esta Asignacion"));
        VersionAsignacionSolicitudCama editarAsignacion = new VersionAsignacionSolicitudCama();
        BeanUtils.copyProperties(versionAsignacionSolicitudCamaActual, editarAsignacion);
        editarAsignacion = mapper.map(versionAsignacionCamaEditDTO, VersionAsignacionSolicitudCama.class);
        editarAsignacion.setId(incrementarVersionId(versionAsignacionSolicitudCamaActual.getId()));
        editarAsignacion.setFechaCreacion(LocalDateTime.now());
        editarAsignacion.setAsignacionSolicitudCama(versionAsignacionSolicitudCamaActual.getAsignacionSolicitudCama());
        editarAsignacion.setUsuario(usuarioRepository.findByDocumento(username).orElse(null));
        editarAsignacion.getAsignacionSolicitudCama().setSolicitudCama(versionAsignacionSolicitudCamaActual.getAsignacionSolicitudCama().getSolicitudCama());
        versionAsignacionSolicitudCamaActual.setEstado(false);
        editarAsignacion.setEstado(true);
        VersionAsignacionSolicitudCamaResDTO versionAsignacionSolicitudCamaResDTO = new VersionAsignacionSolicitudCamaResDTO();
        versionAsignacionSolicitudCamaResDTO.setId(editarAsignacion.getId());
        versionAsignacionSolicitudCamaResDTO.setAsignacionCama(mapper.map(editarAsignacion.getAsignacionSolicitudCama(), AsignacionCamaResVersionDTO.class));
        versionAsignacionSolicitudCamaResDTO.setCama(mapper.map(editarAsignacion.getCama(), CamaResDTO.class));
        versionAsignacionSolicitudCamaResDTO.setUsuario(mapper.map(editarAsignacion.getUsuario(),UsuarioResDTO.class));
        versionAsignacionSolicitudCamaResDTO.setObservacion(editarAsignacion.getObservacion());
        versionAsignacionSolicitudCamaResDTO.setEnfermeroOrigen(editarAsignacion.getEnfermeroOrigen());
        versionAsignacionSolicitudCamaResDTO.setEnfermeroDestino(editarAsignacion.getEnfermeroDestino());
        versionAsignacionSolicitudCamaResDTO.setExtension(editarAsignacion.getExtension());
        versionAsignacionSolicitudCamaResDTO.setMotivoCancelacion(editarAsignacion.getMotivo_cancelacion());
        versionAsignacionSolicitudCamaResDTO.setFechaCreacion(LocalDateTime.now());
        versionAsignacionSolicitudCamaResDTO.setServicio(mapper.map(editarAsignacion.getServicio(), ServicioResDTO.class));
        VersionSolicitudCama versionSolicitudfinal = versionSolicitudCamaRepository.findLastVersionByAsignacionCamaId(versionAsignacionSolicitudCamaActual.getAsignacionSolicitudCama().getId()).orElse(null);
        VersionSolicitudSinSolAsigResDTO versionSolicitud=  mapearVersionSolicitud(versionSolicitudfinal);
        versionAsignacionSolicitudCamaResDTO.getAsignacionCama().getSolicitudCama().setVersionSolicitud(versionSolicitud);
        versionAsignacionSolicitudCamaRepository.save(versionAsignacionSolicitudCamaActual);
        versionAsignacionSolicitudCamaRepository.save(editarAsignacion);
        return  versionAsignacionSolicitudCamaResDTO;
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

    public  VersionSolicitudSinSolAsigResDTO mapearVersionSolicitud(VersionSolicitudCama versionSolicitudCama){
        VersionSolicitudSinSolAsigResDTO versionDTO = new VersionSolicitudSinSolAsigResDTO();
        versionDTO.setId(versionSolicitudCama.getId());
        versionDTO.setRequiereAislamiento(versionSolicitudCama.getRequiereAislamiento());
        versionDTO.setEstado(versionSolicitudCama.getEstado());
        versionDTO.setMotivo(versionSolicitudCama.getMotivo());
        versionDTO.setAutorizacionFacturacion(versionSolicitudCama.getAutorizacionFacturacion());
        versionDTO.setRequerimientosEspeciales(versionSolicitudCama.getRequerimientosEspeciales());
        versionDTO.setFecha(versionSolicitudCama.getFecha());
        versionDTO.setUsuario(mapper.map(versionSolicitudCama.getUsuario(), UsuarioResDTO.class));
        versionDTO.setMedidasAislamiento(
                versionSolicitudCama.getMedidasAislamiento().stream()
                        .map(medida -> mapper.map(medida, MedidasAislamientoResDTO.class))
                        .collect(Collectors.toList()));
        versionDTO.setTitulosFormacionAcademica(
                versionSolicitudCama.getTitulosFormacionAcademica().stream()
                        .map(titulo -> mapper.map(titulo, TitulosFormacionAcacemicaResDTO.class))
                        .collect(Collectors.toList()));
        versionDTO.setDiagnosticos(
                versionSolicitudCama.getDiagnosticos().stream()
                        .map(diagnostico -> mapper.map(diagnostico, DiagnosticoResDTO.class))
                        .collect(Collectors.toList()));
        versionDTO.setServicio(mapper.map(versionSolicitudCama.getServicio(), ServicioResDTO.class));
        versionDTO.setCama(mapper.map(versionSolicitudCama.getCama(), CamaResDTO.class));
        versionDTO.setBloqueServicio(mapper.map(versionSolicitudCama.getBloqueServicio(), BloqueServicioResDTO.class));
        return versionDTO;
    }

}
