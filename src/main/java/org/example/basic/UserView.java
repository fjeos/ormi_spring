package org.example.basic;

public class UserView {
    public void printUserDetails(User user) {
        System.out.println("User details: ");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }
}
