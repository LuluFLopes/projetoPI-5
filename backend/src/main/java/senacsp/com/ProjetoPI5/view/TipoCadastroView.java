package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;

public class TipoCadastroView {

    private final String descricao;
    private final String nome;

    public TipoCadastroView(TipoCadastro tipoCadastro) {
        this.descricao = tipoCadastro.getDescricao();
        this.nome = tipoCadastro.name();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}
