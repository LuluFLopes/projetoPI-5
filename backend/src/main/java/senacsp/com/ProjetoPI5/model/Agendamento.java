package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;

import java.time.LocalDate;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    @ManyToOne(cascade = CascadeType.ALL)
    private Unidade unidade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Medico medico;
    @Enumerated
    private Andamento andamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Andamento getAndamento() {
        return andamento;
    }

    public void setAndamento(Andamento andamento) {
        this.andamento = andamento;
    }
}
