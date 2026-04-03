
package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoApiApplication.class, args);
    }
}

// =======================
// File: model/Todo.java
// =======================
package com.example.todo.model;

public class Todo {
    private Long id;
    private String title;
    private boolean completed;

    public Todo() {}

    public Todo(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}

// =======================
// File: service/TodoService.java
// =======================
package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {

    private final Map<Long, Todo> todoMap = new HashMap<>();
    private Long idCounter = 1L;

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todoMap.values());
    }

    public Todo getTodoById(Long id) {
        return todoMap.get(id);
    }

    public Todo createTodo(Todo todo) {
        todo.setId(idCounter++);
        todoMap.put(todo.getId(), todo);
        return todo;
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        if (!todoMap.containsKey(id)) return null;
        updatedTodo.setId(id);
        todoMap.put(id, updatedTodo);
        return updatedTodo;
    }

    public void deleteTodo(Long id) {
        todoMap.remove(id);
    }
}

// =======================
// File: controller/TodoController.java
// =======================
package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        return todo != null ? ResponseEntity.ok(todo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updated = todoService.updateTodo(id, todo);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
