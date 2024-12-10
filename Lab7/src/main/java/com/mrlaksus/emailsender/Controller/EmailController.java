package com.mrlaksus.emailsender.Controller;

import com.mrlaksus.emailsender.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;
    @GetMapping("/")
    public String showEmailForm() {
        return "email-form";
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam String email, @RequestParam String name, @RequestParam String message, Model model) {
        try {
            emailService.sendEmail(email, "Hello from " + name, message);
            model.addAttribute("message", "Email sent successfully");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to send email: " + e.getMessage());
        }
        return "email-form";
    }
}
