package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;

@Entity
public class Medico extends Pessoa {
    private String crm;
    @OneToOne
    private Especializacao especializacao;
    @ManyToOne
    private Agendamento agendamento;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(Especializacao especializacao) {
        this.especializacao = especializacao;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
