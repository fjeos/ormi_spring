package org.example.basic.day2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Control {
    private User model;
    private UserView view;

    public void updateView() {
        view.printUserDetails(model);
    }

    public void setUserName(String name){
        model.setName(name);
    }

    public void setUserAge(int age) {
        model.setAge(age);
    }
}
