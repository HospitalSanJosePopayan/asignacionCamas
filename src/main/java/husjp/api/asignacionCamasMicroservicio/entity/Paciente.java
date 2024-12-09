package husjp.api.asignacionCamasMicroservicio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name ="id_paciente", foreignKey = @ForeignKey(name = "fk_paciente_persona"))
public class Paciente extends Persona {

    private String genero;

}
