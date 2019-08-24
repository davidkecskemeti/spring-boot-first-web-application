package hu.forloop.springboot.web.service;

import hu.forloop.springboot.web.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();
    private static int todoCount = 3;

    static {
        todoList.add(new Todo(1, "forloop", "Learn Spring MVC", new Date(), false));
        todoList.add(new Todo(2, "forloop", "Learn Struts", new Date(), false));
        todoList.add(new Todo(3, "forloop", "Learn Hibernate", new Date(), false));
    }

    public List<Todo> retrieveTodos(String user) {
        System.out.println("Get todo list by user:" + user);
        List<Todo> filteredTodoList = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getUser().equals(user)) {
                filteredTodoList.add(todo);
                System.out.println(todo);
            }
        }
        return filteredTodoList;
    }

    public Todo retrieveTodo(int id) {
        System.out.println("Search todo with id: " + id);
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                System.out.println("Todo found: " + todo.toString());
                return todo;
            }
        }
        return null;
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todoList.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todoList.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

    public void updateTodo(Todo todo) {
        System.out.println("Updating todo: " + todo.toString());
        todoList.remove(retrieveTodo(todo.getId()));
        todoList.add(todo);
    }
}
