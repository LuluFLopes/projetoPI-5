package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;
import senacsp.com.ProjetoPI5.model.enumeradores.HorarioDeAtendimento;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    @ManyToOne(cascade = CascadeType.ALL)
    private Unidade unidade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Medico medico;
    @OneToOne
    private Paciente paciente;
    @Enumerated
    private Andamento andamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Andamento getAndamento() {
        return andamento;
    }

    public void setAndamento(Andamento andamento) {
        this.andamento = andamento;
    }
}
