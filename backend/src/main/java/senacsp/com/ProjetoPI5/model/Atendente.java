package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Atendente extends Pessoa {
}
