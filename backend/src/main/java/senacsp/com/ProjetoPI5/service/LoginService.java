package senacsp.com.ProjetoPI5.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.Pessoa;
import senacsp.com.ProjetoPI5.model.enumeradores.Status;
import senacsp.com.ProjetoPI5.repository.LoginRepository;

import java.util.NoSuchElementException;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Pessoa realizarLogin(Login login) {
        Pessoa pessoa = loginRepository.login(login.getUsuario(), Status.ATIVO);

        if (validaSeSenhasBatem(login, pessoa)) {
            return pessoa;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean validaSeSenhasBatem(Login login, Pessoa medico) {
        return passwordEncoder.matches(login.getSenha(), medico.getLogin().getSenha());
    }

}
