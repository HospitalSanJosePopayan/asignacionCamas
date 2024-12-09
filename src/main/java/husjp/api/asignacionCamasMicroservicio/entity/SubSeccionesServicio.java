package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subsecciones_servicio")
public class SubSeccionesServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subseccion_servicio")
    private Integer id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "seccion_servicio_id", foreignKey = @ForeignKey(name = "FK_subseccionServicio_seccionServicio"))
    private SeccionesServicio seccionServicio;

}
