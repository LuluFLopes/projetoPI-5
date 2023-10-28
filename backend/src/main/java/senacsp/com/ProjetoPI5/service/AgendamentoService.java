package senacsp.com.ProjetoPI5.service;

import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Agendamento;
import senacsp.com.ProjetoPI5.model.enumeradores.Andamento;
import senacsp.com.ProjetoPI5.model.enumeradores.HorarioDeAtendimento;
import senacsp.com.ProjetoPI5.repository.AgendamentoRepository;
import senacsp.com.ProjetoPI5.view.HorarioDisponivelView;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class AgendamentoService {
    private final String MENSAGEM_AGENDAMENTO_NAO_ENCONTRADO = "Agendamento não encontrado";

    private final String MENSAGEM_LISTA_AGENDAMENTO_VAZIA = "Agendamentos não encontrados";

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        if (agendamentos.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_AGENDAMENTO_VAZIA);
        }
        return agendamentos;
    }

    public Agendamento buscarAgendamento(int id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_AGENDAMENTO_NAO_ENCONTRADO));
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        agendamento.setAndamento(Andamento.AGENDADO);
        agendamentoRepository.save(agendamento);
    }

    public void alterarAgendamento(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public List<HorarioDisponivelView> listarHorariosDisponiveis(Agendamento agendamento) {
        List<Agendamento> agendamentos = agendamentoRepository.listarAgendamentos(agendamento.getPaciente().getId(), agendamento.getDataAgendamento());
        return filtrarHorariosSemAgendamento(agendamentos, Arrays.asList(HorarioDeAtendimento.values()));
    }

    private List<HorarioDisponivelView> filtrarHorariosSemAgendamento(List<Agendamento> agendados, List<HorarioDeAtendimento> horarioDeAtendimento) {
        List<HorarioDisponivelView> horariosDisponiveis = new ArrayList<>();
        for (HorarioDeAtendimento horario : horarioDeAtendimento) {
            boolean horarioDisponivel = true;
            for (Agendamento agendamento : agendados) {
                LocalTime dataInicio = agendamento.getHoraInicio();
                LocalTime dataTermino = agendamento.getHoraTermino();
                if (horario.getHoraDeInicio().equals(dataInicio)
                        && horario.getHoraDeTermino().equals(dataTermino)) {
                    horarioDisponivel = false;
                    break;
                }
            }
            if (horarioDisponivel) {
                horariosDisponiveis.add(HorarioDisponivelView.conversor(horario));
            }
        }
        return horariosDisponiveis;
    }

    public void cancelarAgendamento(int id) {
        agendamentoRepository.ajustarAgendamento(Andamento.CANCELADO, id);
    }

    public void concluirAgendamento(int id) {
        agendamentoRepository.ajustarAgendamento(Andamento.CONCLUIDO, id);
    }
}
