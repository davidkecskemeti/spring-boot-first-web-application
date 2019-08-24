package hu.forloop.springboot.web.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String userId, String password) {
      return (userId.equalsIgnoreCase("forloop") && password.equalsIgnoreCase("kda421"));
    }
}
