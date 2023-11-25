package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.Entity;
import senacsp.com.ProjetoPI5.model.enumeradores.Cargo;

@Entity
public class Funcionario extends Pessoa {

    private Cargo cargo;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
