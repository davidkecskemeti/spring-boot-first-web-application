package hu.forloop.springboot.web.springbootfirstwebapplication.controller;

import hu.forloop.springboot.web.springbootfirstwebapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
//        model.put("name", name);
//        System.out.println("name is: " + name);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {

        if (!loginService.validateUser(name, password)){
            model.put("errorMessage","Invalid credentials");
            return "login";
        }

        model.put("name", name);
//        System.out.println("name is: " + name);
        return "welcome";
    }
}
