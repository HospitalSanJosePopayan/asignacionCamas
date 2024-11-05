package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.AsignacionCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AsignacionSolicitudCamaRepository extends JpaRepository<AsignacionCama, String> {

    @Query(value = "SELECT * FROM asignacion_solicitud_cama WHERE id_asignacion_cama LIKE %?1% ORDER BY id_asignacion_cama DESC LIMIT 1", nativeQuery = true)
    Optional<AsignacionCama> findLastIdBySiglas(String siglas);
}
