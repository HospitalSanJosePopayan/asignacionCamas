package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "secciones_servicio")
public class SeccionesServicio {

    @Id
    @Column(name = "id_seccion_servicio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "servicio_id", foreignKey = @ForeignKey(name = "fk_seccionesServicio_servicio"))
    private Servicio servicio;

}
