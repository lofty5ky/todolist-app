package me.example.todolistapp.controller;

import jakarta.validation.Valid;
import me.example.todolistapp.model.Todo;
import me.example.todolistapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoFormController {
    @Autowired
    private TodoService todoService;


    @GetMapping("/create")
    public String showCreateForm(Todo todo) {
        return "new-todo";
    }

    @PostMapping("/todo")
    public String createTodo(@Valid Todo todo, BindingResult result, Model model) {
        Todo item = new Todo();
        item.setDescription(todo.getDescription());
        item.setComplete(todo.isComplete());

        todoService.save(todo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id, Model model) {
        Todo todo = todoService
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + " not found"));

        todoService.delete(todo);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Todo todo = todoService
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + " not found"));

        model.addAttribute("todoItem", todo);
        return "edit-todo";
    }

    @PostMapping("/todo/{id}")
    public String updateTodo(@PathVariable("id") Long id, @Valid Todo todo, BindingResult result, Model model){
        Todo item = todoService
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + " not found"));

        item.setComplete(todo.isComplete());
        item.setDescription(todo.getDescription());

        todoService.save(item);
        return "redirect:/";
    }
}
