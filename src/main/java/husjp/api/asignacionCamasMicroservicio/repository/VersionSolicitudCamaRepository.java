package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.VersionSolicitudCama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VersionSolicitudCamaRepository extends JpaRepository<VersionSolicitudCama, String> {

    //CONSULTA PARA OBTENER LAS ULTIMAS VERSIONES REGISTRADAS POR ID DE BLOQUE DE SERVICIO
    @Query("SELECT vsc FROM VersionSolicitudCama vsc WHERE vsc.estado = true AND vsc.bloqueServicio.id = :bloqueServicioId and vsc.solicitudCama.estado.id in :estados")
    Optional<List<VersionSolicitudCama>> findBySolicitudCamaEstadoActivoPorBloque(@Param("bloqueServicioId") Integer bloqueServicioId,@Param("estados") List<Integer> estados);

    //Consulta para obtener versionSolicitudCama activa o en espera por ingreso
    @Query("SELECT vsc FROM VersionSolicitudCama vsc where vsc.estado = true AND vsc.solicitudCama.ingreso.id = :ingreso")
    Optional<VersionSolicitudCama> buscarActivaOEnEsperaByIngreso(@Param("ingreso") String ingreso);

    @Query(value = """
    SELECT vsc.*
    FROM version_solicitud_cama vsc
    WHERE vsc.solicitud_cama_id = :idSolicitudCama
      AND vsc.fecha = (
          SELECT MAX(vsc2.fecha)
          FROM version_solicitud_cama vsc2
          WHERE vsc2.solicitud_cama_id = vsc.solicitud_cama_id
      );
    
    """, nativeQuery = true)
    Optional<VersionSolicitudCama> findEndByIdSolicitudCama(@Param("idSolicitudCama") String idSolicitudCama);

    @Query("SELECT vsc " +
            "FROM VersionSolicitudCama vsc " +
            "JOIN vsc.solicitudCama sc " +
            "JOIN AsignacionSolicitudCama asc2 ON asc2.solicitudCama.id = sc.id " +
            "JOIN VersionAsignacionSolicitudCama vasc ON vasc.asignacionSolicitudCama.id = asc2.id " +
            "WHERE vsc.estado = TRUE " +
            "AND vasc.estado = TRUE " +
            "AND asc2.id = :asignacionCamaId")
    Optional<VersionSolicitudCama> findLastVersionByAsignacionCamaId(@Param("asignacionCamaId") String asignacionCamaId);

}