package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.Pessoa;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;

public class RespostaLoginView {

    private int id;
    private String usuario;
    private String nome;
    private TipoCadastro tipoCadastro;
    private String token;

    public RespostaLoginView(int id, String usuario, String nome, TipoCadastro tipoCadastro, String token) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.tipoCadastro = tipoCadastro;
        this.token = token;
    }

    public static RespostaLoginView geraRespostaView(Pessoa pessoa, String token) {
        return new RespostaLoginView(
                pessoa.getId(),
                pessoa.getLogin().getUsuario(),
                pessoa.getNome(),
                pessoa.getTipoCadastro(),
                token
        );
    }

}
