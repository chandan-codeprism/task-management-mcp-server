package chandan.rai.taskmanagementmcpserver.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String assignee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
