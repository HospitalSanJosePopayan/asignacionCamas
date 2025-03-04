package husjp.api.asignacionCamasMicroservicio.service.impl;

import husjp.api.asignacionCamasMicroservicio.entity.*;
import husjp.api.asignacionCamasMicroservicio.entity.enums.EstadoSolicitudCama;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadNoExisteException;
import husjp.api.asignacionCamasMicroservicio.exceptionsControllers.exceptions.EntidadSinCambiosException;
import husjp.api.asignacionCamasMicroservicio.repository.UsuarioRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionAsignacionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.repository.VersionSolicitudCamaRepository;
import husjp.api.asignacionCamasMicroservicio.service.*;
import husjp.api.asignacionCamasMicroservicio.service.dto.request.*;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.*;
import husjp.api.asignacionCamasMicroservicio.service.dto.response.BloqueServicioResDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VersionSolicitudCamaServiceImpl implements VersionSolicitudCamaService {

    private final UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    private SolicitudCamaService solicitudCamaService;
    private VersionSolicitudCamaRepository versionSolicitudCamaRepository;
    private VersionAsignacionSolicitudCamaRepository versionAsignacionSolicitudCamaRepository;
    private PacienteService pacienteService;
    private CamaService camaService;

    @Override
    public VersionSolicitudResDTO guardarVersionSolicitudCama(VersionSolicitudCamaReqDTO versionSolicitudCamaReqDTO, String username) {
        // si hay una solicitud activa se lanza excepcion
        solicitudCamaService.validarSiExisteSolicitudVigente(versionSolicitudCamaReqDTO.getSolicitudCama());
        VersionSolicitudCama versionSolicitudCama = crearVersionSolicitudCama(versionSolicitudCamaReqDTO, username);
        VersionSolicitudCama r = versionSolicitudCamaRepository.save(versionSolicitudCama);
        return modelMapper.map(r, VersionSolicitudResDTO.class);
    }

    private VersionSolicitudCama crearVersionSolicitudCama(VersionSolicitudCamaReqDTO versionSolicitudCamaReqDTO, String username) {

        VersionSolicitudCama versionSolicitudCama = modelMapper.map(versionSolicitudCamaReqDTO, VersionSolicitudCama.class);
        Paciente paciente = pacienteService.findByIdentificacion(versionSolicitudCamaReqDTO.getSolicitudCama().getIngreso().getPaciente().getDocumento());
        if(paciente != null ){
            versionSolicitudCama.getSolicitudCama().getIngreso().getPaciente().setIdPersona(paciente.getIdPersona());
        }
        //agregar la cama
        Cama cama = camaService.findByCodigo(versionSolicitudCamaReqDTO.getCama().getCodigo());
        versionSolicitudCama.setCama(cama);
        //agregar el subservicio
        //Servicio servicio = servicioService.findByNombre(versionSolicitudCamaDTO.getServicio().getNombre());
        versionSolicitudCama.setServicio(cama.getSubseccion() == null ? cama.getServicio() : cama.getSubseccion().getSeccionServicio().getServicio());
        //agregar usuario
        Usuario usuario = usuarioRepository.findByDocumento(username).orElse(null);
        versionSolicitudCama.setUsuario(usuario);
        String codigoSolicitudCama = solicitudCamaService.generarCodigoSolicitudCama(cama.getSubseccion() == null ? cama.getServicio().getNombre() : cama.getSubseccion().getSeccionServicio().getServicio().getNombre());
        versionSolicitudCama.setFecha(LocalDateTime.now());
        versionSolicitudCama.setAutorizacionFacturacion("EN ESPERA");//POR DEFECTO SIEMPRE ES EN ESPERA
        //creamos la solicitud de la cama
        versionSolicitudCama.getSolicitudCama().setEstado(EstadoSolicitudCama.EN_ESPERA.toEntity());
        versionSolicitudCama.getSolicitudCama().setId(String.valueOf(codigoSolicitudCama));  // Asignar el id manualmente
        //Actualizamos la fecha de la solicitud de cama y version de solicitud
        versionSolicitudCama.getSolicitudCama().setFechaInicial(LocalDateTime.now());
        versionSolicitudCama.setEstado(true);
        versionSolicitudCama.setId(codigoSolicitudCama+"-V1");  // Asignar el id manualmente
        //buscamos al paciente si el paciente no esta se crea_todo sin ninguna otra resticcion

        return versionSolicitudCama;
    }

    @Override
    public List<VersionSolicitudResDTO> getVersionSolicitudCamaActivasEnEsperaByIdBloque(Integer idBloqueServicio) {
        List<VersionSolicitudCama> response = versionSolicitudCamaRepository.findBySolicitudCamaEstadoActivoPorBloque(idBloqueServicio,List.of(1,2)).orElseThrow(null);
        assert response != null;

        List<VersionSolicitudResDTO> listResponse = new ArrayList<>();
        for(VersionSolicitudCama verSolCamaEntity : response){
            VersionSolicitudResDTO verSolResDTO = new VersionSolicitudResDTO();
            verSolResDTO.setRequiereAislamiento(verSolCamaEntity.getRequiereAislamiento());
            verSolResDTO.setId(verSolCamaEntity.getId());
            verSolResDTO.setEstado(verSolCamaEntity.getEstado());
            verSolResDTO.setMotivo(verSolCamaEntity.getMotivo());
            verSolResDTO.setAutorizacionFacturacion(verSolCamaEntity.getAutorizacionFacturacion());
            verSolResDTO.setRequerimientosEspeciales(verSolCamaEntity.getRequerimientosEspeciales());
            verSolResDTO.setFecha(verSolCamaEntity.getFecha());
            verSolResDTO.setUsuario(modelMapper.map(verSolCamaEntity.getUsuario(), UsuarioResDTO.class));
            verSolResDTO.setSolicitudCama(modelMapper.map(verSolCamaEntity.getSolicitudCama(), SolicitudCamaResDTO.class));
            verSolResDTO.setMedidasAislamiento(verSolCamaEntity.getMedidasAislamiento().stream().map(medidasAislamiento -> modelMapper.map(medidasAislamiento, MedidasAislamientoResDTO.class)).collect(Collectors.toList()));
            verSolResDTO.setTitulosFormacionAcademica(verSolCamaEntity.getTitulosFormacionAcademica().stream().map(titulosFormacionAcacemica -> modelMapper.map(titulosFormacionAcacemica, TitulosFormacionAcacemicaResDTO.class)).collect(Collectors.toList()));
            verSolResDTO.setDiagnosticos(verSolCamaEntity.getDiagnosticos().stream().map(diagnostico -> modelMapper.map(diagnostico, DiagnosticoResDTO.class)).collect(Collectors.toList()));
            verSolResDTO.setServicio(modelMapper.map(verSolCamaEntity.getServicio(), ServicioResDTO.class));
            verSolResDTO.setCama(modelMapper.map(verSolCamaEntity.getCama(), CamaResDTO.class));
            verSolResDTO.setBloqueServicio(modelMapper.map(verSolCamaEntity.getBloqueServicio(), BloqueServicioResDTO.class));

            VersionAsignacionSolicitudCama verAsigSolCamaEntity = versionAsignacionSolicitudCamaRepository.findActiveAsignacionCamaByIdSolicitudCamaByEstadoSolicitudCamaByEstadoVersionSolicitudCama(verSolCamaEntity.getSolicitudCama().getId(), EstadoSolicitudCama.CANCELADA.getId()).orElse(null);
            if(verAsigSolCamaEntity != null){
                AsignacionCamaSinSolCamaResDTO asigCamaResDTO = new AsignacionCamaSinSolCamaResDTO();
                asigCamaResDTO.setId(verAsigSolCamaEntity.getAsignacionSolicitudCama().getId());
                asigCamaResDTO.setEstado(modelMapper.map(verAsigSolCamaEntity.getAsignacionSolicitudCama().getEstado(), EstadoSolicitudCamaResDTO.class));
                asigCamaResDTO.setVersionSolicitudAsignacion(modelMapper.map(verAsigSolCamaEntity, VersionSolicitudAsignacionSinAsigCamaResDTO.class));
                verSolResDTO.setAsignacionCama(asigCamaResDTO);
            }
            listResponse.add(verSolResDTO);
        }
        return listResponse;
    }

    @Override
    public VersionSolicitudResDTO editarVersionSolicitudCama(String id, VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO, String username) {
        VersionSolicitudCama versionActual = versionSolicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("Solicitud no encontrada"));
        if(!hayCambios(versionActual,versionSolicitudCamaEditDTO)){
            throw  new EntidadSinCambiosException("NO SE  REALIZO NINGUN CAMBIO EN ESTA VERSION");
        }
        VersionSolicitudCama nuevaVersion = prepararNuevaVersion(versionActual, versionSolicitudCamaEditDTO, username);
        VersionSolicitudCama nuevaVersionGuardada = versionSolicitudCamaRepository.save(nuevaVersion);
        versionActual.setEstado(false);
        versionSolicitudCamaRepository.save(versionActual);
        return modelMapper.map(nuevaVersionGuardada, VersionSolicitudResDTO.class);
    }
    @Override
    public VersionSolicitudResDTO EstadoSolicitud(String id) {
        VersionSolicitudCama versionSolicitudCama = versionSolicitudCamaRepository.findById(id).orElseThrow(() -> new EntidadNoExisteException("Solicitud no encontrada"));
        versionSolicitudCama.setAutorizacionFacturacion(versionSolicitudCama.getAutorizacionFacturacion().equals("NO") ? "SI" : "NO");
        versionSolicitudCamaRepository.save(versionSolicitudCama);
        return modelMapper.map(versionSolicitudCama, VersionSolicitudResDTO.class);
    }

    @Override
    public VersionSolicitudResDTO findEndVersionByIdSolicitudCama(String id) {
        VersionSolicitudCama resEntity = versionSolicitudCamaRepository.findEndByIdSolicitudCama(id).orElse(null);
        VersionSolicitudResDTO responseDTO =modelMapper.map(resEntity, VersionSolicitudResDTO.class);
        responseDTO.setSolicitudCama(null);
        if (resEntity != null) {
            return responseDTO;
        }
        return null;
    }

    @Override
    public VersionSolicitudResDTO CambiarEstadoCanceladaSolicitud(String id, String motivoCancelar) {
        VersionSolicitudCama versionSolicitudCama = versionSolicitudCamaRepository.findById(id).orElseThrow(()-> new EntidadNoExisteException("No existe esta solicitud"));
        versionSolicitudCama.getSolicitudCama().setMotivoCancelacion(motivoCancelar);
        versionSolicitudCama.getSolicitudCama().setEstado(EstadoSolicitudCama.CANCELADA.toEntity());
        versionSolicitudCamaRepository.save(versionSolicitudCama);
        return  modelMapper.map(versionSolicitudCama, VersionSolicitudResDTO.class);
    }

    @Override
    public VersionSolicitudResDTO obtenerVerionSolicitudCamaActivaPorIngreso(String ingreso) {
        VersionSolicitudCama versionSolicitudCama =  versionSolicitudCamaRepository.buscarActivaOEnEsperaByIngreso(ingreso).orElseThrow(() -> new EntidadNoExisteException("no se encotnro solicitud de cama con ingreso "+ingreso));
        return modelMapper.map(versionSolicitudCama, VersionSolicitudResDTO.class);
    }

    private VersionSolicitudCama prepararNuevaVersion(VersionSolicitudCama versionActual, VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO, String username) {
        VersionSolicitudCama nuevaVersion = new VersionSolicitudCama();
        BeanUtils.copyProperties(versionActual, nuevaVersion, "bloqueServicio");
        nuevaVersion = modelMapper.map(versionSolicitudCamaEditDTO, VersionSolicitudCama.class);
        if (nuevaVersion.getRequiereAislamiento() == false) {
            nuevaVersion.setMedidasAislamiento(null);
            nuevaVersion.setMotivo(null);
        }
        nuevaVersion.setCama(versionActual.getCama());
        nuevaVersion.setFecha(LocalDateTime.now());
        nuevaVersion.setServicio(versionActual.getServicio());
        nuevaVersion.setAutorizacionFacturacion(versionActual.getAutorizacionFacturacion());
        nuevaVersion.setUsuario(usuarioRepository.findByDocumento(username).orElse(null));
        nuevaVersion.setSolicitudCama(versionActual.getSolicitudCama());
        nuevaVersion.setId(incrementarVersionId(versionActual.getId()));
        nuevaVersion.setEstado(true);
        return nuevaVersion;
    }

    private boolean hayCambios(VersionSolicitudCama versionActual, VersionSolicitudCamaEditDTO versionSolicitudCamaEditDTO) {
        if (!listasDeIdsIguales(
                versionActual.getDiagnosticos() != null ? versionActual.getDiagnosticos().stream().map(Diagnostico::getId).toList() : null,
                versionSolicitudCamaEditDTO.getDiagnosticos() != null ? versionSolicitudCamaEditDTO.getDiagnosticos().stream().map(DiagnosticoReqDTO::getId).toList() : null)) {
            return true;
        }
        if(versionActual.getRequiereAislamiento()!=versionSolicitudCamaEditDTO.getRequiereAislamiento()){
            return true;
        }
        if (!listasDeIdsIguales(
                versionActual.getMedidasAislamiento() != null ? versionActual.getMedidasAislamiento().stream().map(MedidasAislamiento::getId).toList() : null,
                versionSolicitudCamaEditDTO.getMedidasAislamiento() != null ? versionSolicitudCamaEditDTO.getMedidasAislamiento().stream().map(MedidasAislamientoReqDTO::getId).toList() : null)) {
            return true;
        }
        if (!listasDeIdsIguales(
                versionActual.getTitulosFormacionAcademica() != null ? versionActual.getTitulosFormacionAcademica().stream().map(TitulosFormacionAcacemica::getId).toList() : null,
                versionSolicitudCamaEditDTO.getTitulosFormacionAcademica() != null ? versionSolicitudCamaEditDTO.getTitulosFormacionAcademica().stream().map(EspecialidadesDTO::getId).toList() : null)) {
            return true;
        }
        if (!Objects.equals(versionActual.getBloqueServicio().getId(), versionSolicitudCamaEditDTO.getBloqueServicio().getId())) {
            return true;
        }
        if (!compararCadenas(versionActual.getMotivo(), versionSolicitudCamaEditDTO.getMotivo())) {
            return true;
        }
        if (!compararCadenas(versionActual.getRequerimientosEspeciales(), versionSolicitudCamaEditDTO.getRequerimientosEspeciales())) {
            return true;
        }
        return false;
    }
    private boolean listasDeIdsIguales(List<?> lista1, List<?> lista2) {
        if (lista1 == null && lista2 == null) {
            return true;
        }

        if ((lista1 == null && lista2.isEmpty()) || (lista2 == null && lista1.isEmpty())) {
            return true;
        }

        if (lista1.size() != lista2.size()) {
            return false;
        }

        for (int i = 0; i < lista1.size(); i++) {
            if (!Objects.equals(lista1.get(i), lista2.get(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean compararCadenas(String valor1, String valor2) {
        if (valor1 == null && valor2 == null) {
            return true;
        }
        if ((valor1 == null || valor1.trim().isEmpty()) &&
                (valor2 == null || valor2.trim().isEmpty())) {
            return true;
        }
        return valor1.trim().equals(valor2.trim());
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
