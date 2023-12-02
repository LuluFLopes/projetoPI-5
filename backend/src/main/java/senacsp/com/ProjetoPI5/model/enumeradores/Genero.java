package senacsp.com.ProjetoPI5.model.enumeradores;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    PREFIRO_NAO_INFORMAR("Prefiro não informar");

    private final String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
