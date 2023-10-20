package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.Agendamento;
import senacsp.com.ProjetoPI5.service.AgendamentoService;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Agendamento> buscarAgendamentos(@PathVariable int id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamento(id));
    }

    @PostMapping("/cadastrar")
    public void adicionarAgendamento(@RequestBody Agendamento agendamento) {
        agendamentoService.adicionarAgendamento(agendamento);
    }

    @PostMapping("/alterar")
    public void alterarAgendamento(@RequestBody Agendamento agendamento) {
        agendamentoService.alterarAgendamento(agendamento);
    }

    @PostMapping("/reagendar")
    public void reagendarAgendamento(@RequestBody Agendamento agendamento) {
        agendamentoService.cancelarAgendamento(agendamento.getId());
        agendamentoService.adicionarAgendamento(agendamento);
    }

    @PutMapping("/cancelar/{id}")
    public void cancelarAgendamento(@PathVariable int id) {
        agendamentoService.cancelarAgendamento(id);
    }

    @PutMapping("/concluir/{id}")
    public void concluirAgendamento(@PathVariable int id) {
        agendamentoService.concluirAgendamento(id);
    }

}
