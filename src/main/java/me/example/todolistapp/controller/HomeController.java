package me.example.todolistapp.controller;

import me.example.todolistapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public ModelAndView root(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todos", todoService.getAll());
        return modelAndView;
    }
}
