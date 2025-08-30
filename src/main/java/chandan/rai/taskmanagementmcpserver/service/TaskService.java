package chandan.rai.taskmanagementmcpserver.service;

import chandan.rai.taskmanagementmcpserver.dto.TaskRequest;
import chandan.rai.taskmanagementmcpserver.dto.TaskResponse;
import chandan.rai.taskmanagementmcpserver.entity.Task;
import chandan.rai.taskmanagementmcpserver.exception.ResourceNotFoundException;
import chandan.rai.taskmanagementmcpserver.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Tool(
            name = "createTask",
            description = "Create a new task with the provided details"
    )
    public TaskResponse createTask(@ToolParam TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .assignee(request.getAssignee())
                .build();
        return toResponse(taskRepository.save(task));
    }

    @Tool(
            name = "getAllTasks",
            description = "Retrieve a list of all tasks"
    )
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Tool(
            name = "getTaskById",
            description = "Retrieve a task by its ID"
    )
    public TaskResponse getTaskById(@ToolParam Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        return toResponse(task);
    }

    @Tool(
            name = "updateTask",
            description = "Update an existing task with the provided details"
    )
    public TaskResponse updateTask(@ToolParam Long id, @ToolParam TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setAssignee(request.getAssignee());

        return toResponse(taskRepository.save(task));
    }

    @Tool(
            name = "deleteTask",
            description = "Delete a task by its ID"
    )
    public void deleteTask(@ToolParam Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .assignee(task.getAssignee())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
