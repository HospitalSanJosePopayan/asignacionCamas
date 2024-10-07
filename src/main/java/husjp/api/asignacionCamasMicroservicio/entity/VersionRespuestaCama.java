package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "version_respuesta_cama")
public class VersionRespuestaCama {

    @Id
    @Column(name = "id_version_respuesta_cama")
    private String id;
    @ManyToOne
    @JoinColumn(name = "respuesta_cama_id", foreignKey = @ForeignKey(name = "fk_versionRespuestaCama_respuestaCama"))
    private RespuestaCama respuestaCama;
    @ManyToOne
    @JoinColumn(name = "cama_id", foreignKey = @ForeignKey(name = "versionRespuestaCama_cama"))
    private Cama cama;
    private String observacion;
    private String enfermero_origen;
    private String enfermero_destino;
    private String extension;
    private String motivo_cancelacion;

}
