package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.VersionAsignacionSolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VersionRespuestaSolicitudCamaRepository extends JpaRepository<VersionAsignacionSolicitudCama, String> {

    @Query("SELECT vc FROM VersionAsignacionSolicitudCama vc where vc.asignacionCama.estado.nombre = 'EN ESPERA'")
    Optional<List<VersionAsignacionSolicitudCama>> findAllByRespuestaSolicitudCamaEstadoActivo();

}
