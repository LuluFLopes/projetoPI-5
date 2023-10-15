package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.Agendamento;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Paciente extends Pessoa {
    @ManyToOne
    private Agendamento agendamento;

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
