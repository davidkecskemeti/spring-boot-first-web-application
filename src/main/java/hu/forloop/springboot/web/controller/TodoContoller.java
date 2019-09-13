package hu.forloop.springboot.web.controller;

import hu.forloop.springboot.web.entity.Todo;
import hu.forloop.springboot.web.repository.TodoRepository;
import hu.forloop.springboot.web.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoContoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoContoller.class);

    @Autowired
    private TodoService service;

    @Autowired
    private TodoRepository repository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodoList(ModelMap model) {
        model.put("todos", repository.findByUser(getLoggedInUserName()));
//        model.put("todos", service.retrieveTodos(getLoggedInUserName()));
        return "list-todos";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }


    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo", new Todo(0, getLoggedInUserName(), "", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        LOGGER.info("Add todo");

        if (result.hasErrors()) {
            LOGGER.error("Add todo has error");
            return "todo";
        }

        todo.setUser(getLoggedInUserName());
        todo.setDone(false);
        repository.save(todo);
        LOGGER.info("Todo created: " + todo.toString());

//        service.addTodo(getLoggedInUserName(), todo.getDesc(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        repository.deleteById(id);
//        service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap modelMap) {
        Todo todo = repository.findById(id).orElse(null);
//        Todo todo = service.retrieveTodo(id);
        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        todo.setUser(getLoggedInUserName());

        if (result.hasErrors()) {
            return "todo";
        }
        repository.save(todo);
//        service.updateTodo(todo);
        return "redirect:/list-todos";
    }
}
