package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Medico extends Pessoa<Medico> {
    private String crm;
    @OneToOne
    private Especializacao especializacao;
    @OneToMany
    private Agendamento agendamento;

    public Medico(Medico medico) {
        super(medico);
        this.crm = medico.getCrm();
        this.especializacao = medico.getEspecializacao();
        this.agendamento = medico.getAgendamento();
    }

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

    @Override
    public boolean isSatisfiedBy(TipoCadastro tipoCadastro) {
        return TipoCadastro.MEDICO.equals(tipoCadastro);
    }

    @Override
    public Medico castStrategy(Medico medico) {
        return new Medico(medico);
    }
}
