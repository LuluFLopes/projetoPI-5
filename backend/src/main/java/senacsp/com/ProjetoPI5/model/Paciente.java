package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Paciente extends Pessoa <Paciente> {
    @ManyToOne
    private Agendamento agendamento;

    public Paciente(Paciente paciente) {
        super(paciente);
        this.agendamento = paciente.getAgendamento();
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public boolean isSatisfiedBy(TipoCadastro tipoCadastro) {
        return TipoCadastro.PACIENTE.equals(tipoCadastro);
    }

    @Override
    public Paciente castStrategy(Paciente paciente) {
        return new Paciente(paciente);
    }
}
