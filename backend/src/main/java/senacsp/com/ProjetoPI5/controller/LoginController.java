package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senacsp.com.ProjetoPI5.model.*;
import senacsp.com.ProjetoPI5.service.LoginService;
import senacsp.com.ProjetoPI5.view.RespostaLoginView;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaLoginView> login(@RequestBody Login login) {
        return ResponseEntity.ok(loginService.realizarLogin(login));
    }

}
