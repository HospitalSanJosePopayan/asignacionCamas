package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "estado_solicitud_camillero")
public class EstadoSolicitudCamillero {

    public EstadoSolicitudCamillero(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadoSolicitudCamillero")
    private Integer id;
    @Column(nullable = false)
    private String nombre;

}
