package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.TitulosFormacionAcacemica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TitulosFormacionAcademicaRepository extends JpaRepository<TitulosFormacionAcacemica, Integer> {

    Optional<List<TitulosFormacionAcacemica>> findByTipoFormacionAcademica_Id(Integer idTipoFormacionAcademica);

}
