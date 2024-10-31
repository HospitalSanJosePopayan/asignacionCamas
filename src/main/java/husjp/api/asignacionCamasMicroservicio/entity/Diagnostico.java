package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diagnostico")
public class Diagnostico {

    @Id
    @Column(name = "codigo")
    private String id;
    private String nombre;

}