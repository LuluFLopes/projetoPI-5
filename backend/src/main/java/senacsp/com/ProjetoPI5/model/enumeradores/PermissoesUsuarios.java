package senacsp.com.ProjetoPI5.model.enumeradores;

public enum PermissoesUsuarios {

    ADMIN("admin"),
    USER("user");

    private final String permissao;

    PermissoesUsuarios(String permissao) {
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }
}
