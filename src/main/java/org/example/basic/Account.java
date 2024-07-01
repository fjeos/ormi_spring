package org.example.basic;

public class Account {
    private String accountNumber; //계좌번호
    private double balance; //돈

    public void changeAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void drawMoney(double money) {
        if (balance < money) {
            System.out.println("잔액이 부족합니다.");
            return;
        }
        balance -= money;
    }
}
