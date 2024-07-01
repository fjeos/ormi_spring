package org.example.basic.day2;

public class SmtpEmailSender implements EmailSender{
    @Override
    public void sendEmail(String to, String subject, String body) {
        // SMTP 기술을 활용한 로직이 들어감
        System.out.println("Sending SMTP email to " + to);
    }
}
