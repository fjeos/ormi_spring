package org.example.basic.day6;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers(){
        // 로직
        return null;
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        // 로직
        return null;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        // 특정 사용자 찾기
        return null;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updateUser) {
        // 로직
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        // 로직
    }
}
