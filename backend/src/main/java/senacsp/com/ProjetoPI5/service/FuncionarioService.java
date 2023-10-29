package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Funcionario;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.Medico;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.FuncionarioRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FuncionarioService {

    private final String MENSAGEM_FUNCIONARIO_NAO_ENCONTRADO = "Funcionário não encontrado";

    private final String MENSAGEM_LISTA_FUNCIONARIO_VAZIA = "Funcionários não encontrados";

    private final FuncionarioRepository funcionarioRepository;

    private final EncriptadorService encriptadorService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, EncriptadorService encriptadorService) {
        this.funcionarioRepository = funcionarioRepository;
        this.encriptadorService = encriptadorService;
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        if (funcionarios.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_FUNCIONARIO_VAZIA);
        }

        funcionarios.forEach(encriptadorService::desencriptarSenha);

        return funcionarios;
    }

    public Funcionario buscarFuncionario(int id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_FUNCIONARIO_NAO_ENCONTRADO));

        encriptadorService.desencriptarSenha(funcionario);

        return funcionario;
    }

    @Transactional
    public void adicionarFuncionario(Funcionario funcionario) {
        trataDadosFuncionario(funcionario);
        funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void alterarFuncionario(Funcionario funcionario){
        trataDadosFuncionario(funcionario);
        funcionarioRepository.save(funcionario);
    }

    private void trataDadosFuncionario(Funcionario funcionario) {
        funcionario.setStatus(Status.ATIVO);
        encriptadorService.encriptarSenhaPorPessoa(funcionario);
    }

    @Transactional
    public void inativarFuncionario(int id) {
        funcionarioRepository.ajustarStatus(Status.INATIVO, id);
    }

    @Transactional
    public void ativarFuncionario(int id) {
        funcionarioRepository.ajustarStatus(Status.ATIVO, id);
    }

    @Transactional
    public Funcionario login(Login login){
        Funcionario funcionario = funcionarioRepository.login(login.getUsuario());

        if(validaSeSenhasBatem(login, funcionario)){
            return funcionario;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean validaSeSenhasBatem(Login login, Funcionario funcionario) {
        encriptadorService.desencriptarSenha(funcionario);
        return login.getSenha().equals(funcionario.getLogin().getSenha());
    }
}
