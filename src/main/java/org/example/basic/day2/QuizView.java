package org.example.basic.day2;

import java.util.Scanner;

public class QuizView {
    public void displayQuestion(String question) {
        System.out.println("Question: " + question);
    }
    public String getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        scanner.close();
        return answer;
    }
    public void displayResult(boolean isCorrect){
        if(isCorrect){
            System.out.println("정답입니다!");
        } else {
            System.out.println("오답입니다.");
        }
    }
}
