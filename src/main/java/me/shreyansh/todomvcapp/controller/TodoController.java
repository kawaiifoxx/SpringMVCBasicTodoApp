package me.shreyansh.todomvcapp.controller;

import me.shreyansh.todomvcapp.domain.Todo;
import me.shreyansh.todomvcapp.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    @PostMapping("/todo")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/{id}")
    public String todoActions(@PathVariable("id") int id, @RequestParam String action) {
        //noinspection EnhancedSwitchMigration
        switch (action) {
            case "toggleCompletionState":
                todoService.updateTodo(toggleTodoCompletionState(todoService.getTodo(id)));
                break;
            case "delete":
                todoService.deleteTodo(id);
        }

        return "redirect:/";
    }

    private Todo toggleTodoCompletionState(Todo todo) {
        todo.setCompleted(!todo.isCompleted());
        return todo;
    }
}
