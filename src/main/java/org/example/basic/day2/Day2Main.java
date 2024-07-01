package org.example.basic.day2;

public class Day2Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailService(new SmtpEmailSender());
        EmailService emailService1 = new EmailService(new AwsSesEmailSender());

        User model = new User("max@gmail.com", "김승조", 30);
        UserView userview = new UserView();
        Control userController = new Control(model, userview);

        userController.updateView();
        userController.setUserName("김승조랑말");
        userController.setUserAge(31);
        System.out.println("=========================");
        userController.updateView();

        Quiz quiz = new Quiz("손톱을 깎을 때 필요한 도구의 이름은 무엇인가요?", "손톱깎이");
        QuizView quizView = new QuizView();
        QuizController quizController = new QuizController(quiz, quizView);
        quizController.startQuiz();
    }
}
