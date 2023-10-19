package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    private Contato contato;
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;
    @Enumerated
    private Status status;

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
