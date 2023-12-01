package senacsp.com.ProjetoPI5.view;

import senacsp.com.ProjetoPI5.model.Agendamento;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.Paciente;
import senacsp.com.ProjetoPI5.model.Unidade;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AgendamentoView {

    private int id;
    private String dataAgendamento;
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    private Unidade unidade;
    private Medico medico;
    private Paciente paciente;
    private Andamento andamento;

    public AgendamentoView(Agendamento agendamento) {
        this.id = agendamento.getId();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAgendamento = formatador.format(agendamento.getDataAgendamento());
        this.horaInicio = agendamento.getHoraInicio();
        this.horaTermino = agendamento.getHoraTermino();
        this.unidade = agendamento.getUnidade();
        this.medico = agendamento.getMedico();
        this.paciente = agendamento.getPaciente();
        this.andamento = agendamento.getAndamento();
    }

    public int getId() {
        return id;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Andamento getAndamento() {
        return andamento;
    }
}
