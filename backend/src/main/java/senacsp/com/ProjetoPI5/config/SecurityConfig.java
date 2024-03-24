package senacsp.com.ProjetoPI5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, ("/agendamentos/listar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/agendamentos/listarPorPaciente")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/agendamentos/listarPorDataEMedico")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/agendamentos/listarPorData")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/agendamentos/buscar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/agendamentos/cadastrar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/agendamentos/alterar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/agendamentos/reagendar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/agendamentos/listarHorariosDisponiveisPorData")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/agendamentos/cancelar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/agendamentos/concluir")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/salvarImagem")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/especializacao/listar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/especializacao/listarAtiva")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/especializacao/buscar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/especializacao/cadastrar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/especializacao/alterar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/especializacao/inativar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/especializacao/ativar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/funcionarios/listar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/funcionarios/buscar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/funcionarios/cadastrar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/funcionarios/alterar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/funcionarios/inativar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/funcionarios/ativar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/listas/uf")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/listas/tiposCadastros")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/listas/generos")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/listas/cargos")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/login/login")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/medicos/listar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/medicos/listarPorUnidadeEPorEspecializacao")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/medicos/buscar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/medicos/cadastrar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/medicos/alterar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/medicos/inativar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/medicos/ativar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/pacientes/listar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/pacientes/buscar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/pacientes/cadastrar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/pacientes/alterar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/pacientes/inativar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/pacientes/ativar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/unidades/listar")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/unidades/listarAtivas")).permitAll()
                        .requestMatchers(HttpMethod.GET, ("/unidades/buscar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/unidades/cadastrar")).permitAll()
                        .requestMatchers(HttpMethod.POST, ("/unidades/alterar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/unidades/inativar")).permitAll()
                        .requestMatchers(HttpMethod.PUT, ("/unidades/ativar")).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
