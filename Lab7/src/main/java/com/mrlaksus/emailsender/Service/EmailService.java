package com.mrlaksus.emailsender.Service;

import com.mrlaksus.emailsender.Entity.Email;
import com.mrlaksus.emailsender.Repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailLogRepository;

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        Email emailLog = new Email();
        emailLog.setRecipient(to);
        emailLog.setSubject(subject);
        emailLog.setContent(text);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            mailSender.send(message);
            emailLog.setStatus("SUCCESS");
        } catch (Exception e) {
            emailLog.setStatus("FAILED: " + e.getMessage());
        }

        emailLogRepository.save(emailLog);
    }
}
