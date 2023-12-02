package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.enumeradores.Cargo;

public class CargoView {

    private final String descricao;
    private final String nome;

    public CargoView(Cargo cargo) {
        this.descricao = cargo.getDescricao();
        this.nome = cargo.name();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}
