package senacsp.com.ProjetoPI5.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import senacsp.com.ProjetoPI5.model.Pessoa;
import senacsp.com.ProjetoPI5.repository.LoginRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final String EMPTY_STRING = "";

    private final TokenService tokenService;

    private final LoginRepository loginRepository;

    public SecurityFilter(TokenService tokenService, LoginRepository loginRepository) {
        this.tokenService = tokenService;
        this.loginRepository = loginRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            preencheAutorizacaoNoContexto(token);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        return StringUtils.hasText(authHeader)
                ? authHeader.replace("Bearer ", EMPTY_STRING)
                : null;
    }

    private void preencheAutorizacaoNoContexto(String token) {
        String login = tokenService.validateToken(token);
        Pessoa pessoa = loginRepository.buscarUsuario(login);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(pessoa, null, pessoa.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
