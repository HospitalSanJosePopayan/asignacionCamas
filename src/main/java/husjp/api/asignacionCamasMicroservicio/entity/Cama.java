package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cama")
public class Cama {

    @Id
    @Column(name = "id_cama")
    private Integer id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "area_id", foreignKey = @ForeignKey(name = "FK_cama_area"))
    private Area area;
    @ManyToOne
    @JoinColumn(name = "cama_estado_id", foreignKey = @ForeignKey(name = "FK_cama_estadoCama"))
    private CamaEstado camaEstado;
}
