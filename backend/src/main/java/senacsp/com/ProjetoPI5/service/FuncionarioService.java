package senacsp.com.ProjetoPI5.service;

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

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
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

    public void adicionarFuncionario(Funcionario funcionario) {
        setarCamposComoAtivoPorPadrao(funcionario);
        funcionarioRepository.save(funcionario);
    }

    public void alterarFuncionario(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
    }

    private void setarCamposComoAtivoPorPadrao(Funcionario funcionario) {
        funcionario.setStatus(Status.ATIVO);
        funcionario.getContato().setStatus(Status.ATIVO);
        funcionario.getEndereco().setStatus(Status.ATIVO);
    }

    public void inativarFuncionario(int id) {
        funcionarioRepository.ajustarStatus(Status.INATIVO, id);
    }

    public void ativarFuncionario(int id) {
        funcionarioRepository.ajustarStatus(Status.ATIVO, id);
    }

    public Funcionario login(Login login){
        return funcionarioRepository.login(login.getUsuario(), login.getSenha());
    }
}
