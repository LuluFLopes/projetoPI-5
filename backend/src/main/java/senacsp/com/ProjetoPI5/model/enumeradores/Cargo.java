package senacsp.com.ProjetoPI5.model.enumeradores;

public enum Cargo {
    ADMINISTRADOR("Administrador"),
    ATENDENTE("Atendente");

    private final String descricao;

    Cargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
