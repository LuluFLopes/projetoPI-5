package senacsp.com.ProjetoPI5.model.enumeradores;

public enum TipoCadastro {
    ADMINISTRADOR("Administrador"),
    FUNCIONARIO("Funcionário"),
    MEDICO("Médico"),
    PACIENTE("Paciente");

    private final String descricao;

    TipoCadastro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
