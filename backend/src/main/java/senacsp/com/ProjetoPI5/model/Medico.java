package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;

@Entity
public class Medico extends Pessoa {

    private String crm;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Especializacao especializacao;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Unidade unidade;

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

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
}
