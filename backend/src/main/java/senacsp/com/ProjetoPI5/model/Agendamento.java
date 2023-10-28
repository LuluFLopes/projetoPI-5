package senacsp.com.ProjetoPI5.model;

import jakarta.persistence.*;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dataAgendamento;
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Unidade unidade;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @Enumerated
    private Andamento andamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataInicio) {
        this.dataAgendamento = dataInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(LocalTime horaTermino) {
        this.horaTermino = horaTermino;
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
