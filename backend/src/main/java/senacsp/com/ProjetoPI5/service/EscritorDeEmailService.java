package senacsp.com.ProjetoPI5.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EscritorDeEmailService {

    private static final String EMAIL = "mednoteclinicmanager@gmail.com";
    private static final String SENHA = "MedNote2024*";
    private Session sessao = null;

    @PostConstruct
    public void inicializar() {
        Properties propriedades = new Properties();
        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.starttls.enable", "true");
        propriedades.put("mail.smtp.host", "smtp.gmail.com");
        propriedades.put("mail.smtp.port", "587");

        sessao = Session.getInstance(propriedades,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL, SENHA);
                    }
                });
    }

    public void enviarEmailPosCadastro(String emailDestinatario) {
        try {
            enviar(emailDestinatario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void enviar(String emailDestinatario) throws Exception {
        Message message = new MimeMessage(sessao);
        message.setFrom(new InternetAddress(EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailDestinatario));
        message.setSubject("Nova conta criada com sucesso!");
        message.setText("Corpo do email");
        Transport.send(message);
    }

}
