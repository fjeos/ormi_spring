package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.util.Helper;
import org.example.util.Test1;
import org.example.util.Test2;
import org.example.util.Test3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
