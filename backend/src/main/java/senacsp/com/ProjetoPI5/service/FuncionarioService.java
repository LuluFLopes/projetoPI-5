package senacsp.com.ProjetoPI5.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Funcionario;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.FuncionarioRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FuncionarioService {

    private final String MENSAGEM_FUNCIONARIO_NAO_ENCONTRADO = "Funcionário não encontrado";

    private final String MENSAGEM_LISTA_FUNCIONARIO_VAZIA = "Funcionários não encontrados";

    private final FuncionarioRepository funcionarioRepository;

    private final PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        if (funcionarios.isEmpty()) {
            throw new NoSuchElementException(MENSAGEM_LISTA_FUNCIONARIO_VAZIA);
        }

        return funcionarios;
    }

    public Funcionario buscarFuncionario(int id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MENSAGEM_FUNCIONARIO_NAO_ENCONTRADO));
    }

    @Transactional
    public void adicionarFuncionario(Funcionario funcionario) {
        trataDadosFuncionario(funcionario);
        funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void alterarFuncionario(Funcionario funcionario) {
        trataDadosFuncionario(funcionario);
        funcionarioRepository.save(funcionario);
    }

    private void trataDadosFuncionario(Funcionario funcionario) {
        funcionario.setStatus(Status.ATIVO);
        funcionario.getLogin().setSenha(passwordEncoder.encode(funcionario.getLogin().getSenha()));
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
    public Funcionario login(Login login) {
        Funcionario funcionario = funcionarioRepository.login(login.getUsuario());

        if (validaSeSenhasBatem(login, funcionario)) {
            return funcionario;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean validaSeSenhasBatem(Login login, Funcionario funcionario) {
        return passwordEncoder.matches(login.getSenha(), funcionario.getLogin().getSenha());
    }
}
