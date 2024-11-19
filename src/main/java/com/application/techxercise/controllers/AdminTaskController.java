package com.application.techXercise.controllers;

import com.application.techXercise.entity.TaskEntity;
import com.application.techXercise.exceptions.TaskNotFoundException;
import com.application.techXercise.exceptions.UserNotFoundException;
import com.application.techXercise.services.TaskService;
import com.application.techXercise.services.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdminTaskController {

    private final TaskService taskService;

    public AdminTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Эндпоинт создания задачи
    @PostMapping("/")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskEntity) throws UserNotFoundException {
        TaskEntity createdTaskEntity = taskService.createTask(taskEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskEntity);
    }

    // Эндпоинт вывода всех задач
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> showAllTasks() {
        List<TaskEntity> taskEntities = taskService.getAllTasks();
        return taskEntities.isEmpty() ?
                ResponseEntity.notFound().build() :  // Возвращаем 404, если задач нет
                ResponseEntity.ok(taskEntities);
    }

    // Эндпоинт для вывода задач исполнителя
    @GetMapping("/tasks/{userId}/executor")
    public ResponseEntity<List<TaskEntity>> showExecutorTasks(@PathVariable long userId) {
        List<TaskEntity> taskEntities = taskService.getTasksByExecutor(userId);
        return taskEntities.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(taskEntities);
    }

    // Эндпоинт для вывода задач автора
    @GetMapping("/tasks/{userId}/author")
    public ResponseEntity<List<TaskEntity>> showAuthorTasks(@PathVariable long userId) {
        List<TaskEntity> taskEntities = taskService.getTasksByAuthor(userId);
        return taskEntities.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(taskEntities);
    }

    // Эндпоинт вывода конкретной задачи по ID
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable long taskId) throws TaskNotFoundException {
        TaskEntity taskEntity = taskService.getTaskById(taskId);
        return taskEntity != null ?
                ResponseEntity.ok(taskEntity) :
                ResponseEntity.notFound().build();
    }

    // Эндпоинт редактирования задачи
    @PatchMapping("/")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity taskEntity) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.updateTaskByAdmin(taskEntity));
    }

    // Эндпоинт удаления задачи
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long taskId) throws TaskNotFoundException {
        boolean isDeleted = taskService.deleteTask(taskId);
        return isDeleted ?
                ResponseEntity.ok("Задача удалена успешно.") :
                ResponseEntity.notFound().build();
    }


}