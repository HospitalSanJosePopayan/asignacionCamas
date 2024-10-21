package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoRepository extends JpaRepository<Ingreso, String> {
}
