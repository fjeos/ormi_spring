package org.example.basic.day3.service;

import lombok.RequiredArgsConstructor;
import org.example.basic.day3.util.Helper;
import org.example.basic.day3.util.Test1;
import org.example.basic.day3.util.Test2;
import org.example.basic.day3.util.Test3;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyService {

    private final Helper helper;
    private final Test1 test1;
    private final Test2 test2;
    private final Test3 test3;


    public String hello() {
        return "Hello, Spring Boot!";
    }

}
