package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;

@Entity
public class Paciente extends Pessoa {
    @ManyToOne(cascade = CascadeType.ALL)
    private Agendamento agendamento;

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
