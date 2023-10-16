package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.service.MedicoService;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicos = medicoService.listarMedicos();
        return !medicos.isEmpty() ?
                ResponseEntity.ok(medicos) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable int id) {
        Medico medico = medicoService.buscarMedico(id);
        return ResponseEntity.ok(medico);
    }

    @PostMapping("/cadastrar")
    public void adicionarMedico(@RequestBody Medico medico) {
        medicoService.adicionarMedico(medico);
    }

    @PutMapping("/inativar/{id}")
    public void inativarMedico(@PathVariable int id) {
        medicoService.inativarMedico(id);
    }

    @PutMapping("/ativar/{id}")
    public void ativarMedico(@PathVariable int id) {
        medicoService.ativarMedico(id);
    }
}
