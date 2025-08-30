package chandan.rai.taskmanagementmcpserver.configuration;

import chandan.rai.taskmanagementmcpserver.service.TaskService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpServerBean {

    @Bean
    public List<ToolCallback> taskManagementTools(TaskService taskService) {
        return List.of(ToolCallbacks.from(taskService));
    }
}
