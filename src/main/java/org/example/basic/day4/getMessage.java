package org.example.basic.day4;

import org.springframework.stereotype.Component;

@Component
public class getMessage implements Message{
    @Override
    public void print() {
        System.out.println("Hello World!2");
    }
}
