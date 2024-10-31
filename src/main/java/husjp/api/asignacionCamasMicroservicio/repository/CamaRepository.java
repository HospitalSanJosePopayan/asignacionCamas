package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamaRepository extends JpaRepository<Cama, Integer> {

    Cama findByCodigo(String codigo);

}
