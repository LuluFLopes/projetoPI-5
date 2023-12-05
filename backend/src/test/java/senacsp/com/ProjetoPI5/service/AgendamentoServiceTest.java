package senacsp.com.ProjetoPI5.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import senacsp.com.ProjetoPI5.model.Agendamento;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.repository.AgendamentoRepository;
import senacsp.com.ProjetoPI5.view.HorarioDisponivelView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;
    @Mock
    private AgendamentoRepository agendamentoRepository;
    private List<HorarioDisponivelView> horarioDisponivelViews;
    private Agendamento agendamento;

    @Test
    public void deveRetornarHorariosDisponiveisCorretamente() {
        dadoUmaListaDeAgendamentosNaBaseDeDados();
        dadoUmValorDeDataEMedico();
        aoListarAgendamentos();
        deveRetornarApenasUmHorario();
        deveRetornarHorarioDas13As14();
    }

    private void dadoUmValorDeDataEMedico() {
        agendamento = new Agendamento();
        Medico medico = new Medico();
        medico.setId(1);
        agendamento.setMedico(medico);
        agendamento.setDataAgendamento(LocalDate.of(2023,1,1));
    }

    private void deveRetornarHorarioDas13As14() {
        assertEquals("13:00", horarioDisponivelViews.get(0).getHoraInicio());
        assertEquals("14:00", horarioDisponivelViews.get(0).getHoraTermino());
    }

    private void deveRetornarApenasUmHorario() {
        assertEquals(1, horarioDisponivelViews.size());
    }

    private void aoListarAgendamentos() {
        horarioDisponivelViews = agendamentoService.listarHorariosDisponiveis(agendamento);
    }

    private void dadoUmaListaDeAgendamentosNaBaseDeDados() {
        when(agendamentoRepository.listarAgendamentosDisponiveis(any(), any())).thenReturn(criarListaAgendamento());
    }

    private List<Agendamento> criarListaAgendamento() {
        Agendamento a1 = new Agendamento();
        a1.setHoraInicio(LocalTime.of(8, 0, 0));
        a1.setHoraTermino(LocalTime.of(9, 0, 0));

        Agendamento a2 = new Agendamento();
        a2.setHoraInicio(LocalTime.of(9, 0, 0));
        a2.setHoraTermino(LocalTime.of(10, 0, 0));

        Agendamento a3 = new Agendamento();
        a3.setHoraInicio(LocalTime.of(10, 0, 0));
        a3.setHoraTermino(LocalTime.of(11, 0, 0));

        Agendamento a4 = new Agendamento();
        a4.setHoraInicio(LocalTime.of(11, 0, 0));
        a4.setHoraTermino(LocalTime.of(12, 0, 0));

        Agendamento a5 = new Agendamento();
        a5.setHoraInicio(LocalTime.of(12, 0, 0));
        a5.setHoraTermino(LocalTime.of(13, 0, 0));

        Agendamento a6 = new Agendamento();
        a6.setHoraInicio(LocalTime.of(14, 0, 0));
        a6.setHoraTermino(LocalTime.of(15, 0, 0));

        Agendamento a7 = new Agendamento();
        a7.setHoraInicio(LocalTime.of(15, 0, 0));
        a7.setHoraTermino(LocalTime.of(16, 0, 0));

        Agendamento a8 = new Agendamento();
        a8.setHoraInicio(LocalTime.of(16, 0, 0));
        a8.setHoraTermino(LocalTime.of(17, 0, 0));

        Agendamento a9 = new Agendamento();
        a9.setHoraInicio(LocalTime.of(17, 0, 0));
        a9.setHoraTermino(LocalTime.of(18, 0, 0));

        Agendamento a10 = new Agendamento();
        a10.setHoraInicio(LocalTime.of(18, 0, 0));
        a10.setHoraTermino(LocalTime.of(19, 0, 0));

        return List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
    }

}