package senacsp.com.ProjetoPI5.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import senacsp.com.ProjetoPI5.config.TokenService;
import senacsp.com.ProjetoPI5.model.Login;
import senacsp.com.ProjetoPI5.model.Pessoa;
import senacsp.com.ProjetoPI5.repository.LoginRepository;
import senacsp.com.ProjetoPI5.view.RespostaLoginView;

import java.util.NoSuchElementException;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final LoginRepository loginRepository;

    private final TokenService tokenService;

    public LoginService(AuthenticationManager authenticationManager, LoginRepository loginRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.loginRepository = loginRepository;
        this.tokenService = tokenService;
    }

    public RespostaLoginView realizarLogin(Login login) {
        try {
            Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha()));
            String token = tokenService.generateToken((Pessoa) auth.getPrincipal());
            Pessoa pessoa = loginRepository.buscarUsuario(login.getUsuario());
            return RespostaLoginView.geraRespostaView(pessoa, token);
        } catch (AuthenticationException ex) {
            throw new NoSuchElementException("Não foi possível autenticar");
        }
    }
}
