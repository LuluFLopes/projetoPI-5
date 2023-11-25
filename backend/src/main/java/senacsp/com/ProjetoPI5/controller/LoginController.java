package senacsp.com.ProjetoPI5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senacsp.com.ProjetoPI5.model.*;
import senacsp.com.ProjetoPI5.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PutMapping("/login")
    public ResponseEntity<Pessoa> login(@RequestBody Login login) {
        return ResponseEntity.ok(loginService.realizarLogin(login));
    }

}
