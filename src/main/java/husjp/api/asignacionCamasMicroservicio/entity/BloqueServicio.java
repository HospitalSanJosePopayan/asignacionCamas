package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "bloque_servicio")
public class BloqueServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloque_servicio")
    private Integer id;
    private String nombre;
    @OneToMany(mappedBy = "bloqueServicio")
    private List<Servicio> servicios;
}
