package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.SolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SolicitudCamaRepository extends JpaRepository<SolicitudCama, String> {

    //Consulta que trae por ingreso la solicitud en estados
    Optional<SolicitudCama> findByIngreso_IdAndAndEstado_IdIn(String ingresoId, List<Integer> estados);

    //Consulta el ultimo id de una solicitud de cama por siglas
    @Query(value = "SELECT * FROM solicitud_cama WHERE id_solicitud_cama LIKE %?1% ORDER BY id_solicitud_cama DESC LIMIT 1", nativeQuery = true)
    Optional<SolicitudCama> findLastIdBySiglas(String siglas);

}
