package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.enumeradores.Genero;

public class GeneroView {

    private final String descricao;
    private final String nome;

    public GeneroView(Genero genero) {
        this.descricao = genero.getDescricao();
        this.nome = genero.name();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}
