package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proceso_id", foreignKey = @ForeignKey(name = "fk_servicio_proceso"))
    private Procesos procesos;
    @ManyToMany(mappedBy = "servicios", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloque_servicio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_proceso_bloqueServicio"))
    private BloqueServicio bloqueServicio;

}
