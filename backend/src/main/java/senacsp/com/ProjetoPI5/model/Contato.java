package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String email;
    String telefone;
    @Enumerated
    Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
