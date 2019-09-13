package hu.forloop.springboot.web.repository;

import hu.forloop.springboot.web.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByUser(String user);

//    public List<Todo> retrieveTodos(String user) {
//    public Todo retrieveTodo(int id) {
//    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
//    public void deleteTodo(int id) {
//    public void updateTodo(Todo todo)

}
