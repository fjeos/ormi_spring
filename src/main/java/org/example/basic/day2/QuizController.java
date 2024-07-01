package org.example.basic.day2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuizController {
    private Quiz quiz;
    private QuizView quizView;

    public void startQuiz(){
        quizView.displayQuestion(quiz.getQuestion());
        quizView.displayResult(quiz.getAnswer().equals(quizView.getUserAnswer()));
    }
}
