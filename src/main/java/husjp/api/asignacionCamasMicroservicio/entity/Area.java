package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "subservicio_id", foreignKey = @ForeignKey(name = "FK_area_subservicio"))
    private SubServicio subservicio;

}
