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
    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private Procesos procesos;
    @ManyToMany(mappedBy = "servicios")
    private List<Usuario> usuarios;
    @ManyToOne
    @JoinColumn(name = "bloque_servicio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_servicio_bloqueServicio"))
    private BloqueServicio bloqueServicio;

}
