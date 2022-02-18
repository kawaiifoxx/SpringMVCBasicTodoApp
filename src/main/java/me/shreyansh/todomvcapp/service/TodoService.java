package me.shreyansh.todomvcapp.service;

import com.github.javafaker.Faker;
import me.shreyansh.todomvcapp.domain.Todo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@Service
public class TodoService {
    private final Map<Integer, Todo> todoRepository = new LinkedHashMap<>();
    int nextId = 1;

    @PostConstruct
    private void init() {
        Faker faker = new Faker();

        Stream.generate(() -> Todo.builder()
                .description(faker.chuckNorris().fact())
                .completed(false)
                .build()
        ).limit(100).forEach(this::addTodo);
    }

    public void addTodo(Todo todo) {
        todo.setId(nextId);
        todoRepository.put(nextId++, todo);
    }

    public List<Todo> getAllTodos() {
        List<Todo> todoList = new ArrayList<>(todoRepository.values());
        Collections.reverse(todoList);

        return todoList;
    }

    public Todo getTodo(int id) {
        return todoRepository.get(id);
    }

    public void deleteTodo(int id) {
        todoRepository.remove(id);
    }

    public void updateTodo(Todo todo) {
        if (todo.getId() == 0) {
            return;
        }

        todoRepository.put(todo.getId(), todo);
    }
}
