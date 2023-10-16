package senacsp.com.ProjetoPI5.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            SQLException.class,
            NullPointerException.class,
            SQLSyntaxErrorException.class
    })
    public <T> ResponseEntity<T> exceptionHandler() {
        return ResponseEntity.notFound().build();
    }

}
