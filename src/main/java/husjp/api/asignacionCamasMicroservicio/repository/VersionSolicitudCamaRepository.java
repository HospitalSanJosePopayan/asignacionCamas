package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.VersionSolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VersionSolicitudCamaRepository extends JpaRepository<VersionSolicitudCama, String> {

    @Query("SELECT vc FROM VersionSolicitudCama vc where vc.solicitudCama.estado.nombre = 'EN ESPERA'")
    Optional<List<VersionSolicitudCama>> findBySolicitudCamaEstadoActivo();

}