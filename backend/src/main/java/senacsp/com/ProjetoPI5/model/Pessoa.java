package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.model.enumeradores.TipoCadastro;

@Entity
public abstract class Pessoa <T extends Pessoa> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    @OneToOne
    private Endereco endereco;
    @OneToOne
    private Contato contato;
    @OneToOne
    private Login login;
    @Enumerated
    private Status status;

    public Pessoa(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.endereco = pessoa.getEndereco();
        this.contato = pessoa.getContato();
        this.login = pessoa.getLogin();
        this.status = pessoa.getStatus();
    }

    public abstract boolean isSatisfiedBy(TipoCadastro tipoCadastro);

    public abstract T castStrategy(T pessoa);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
