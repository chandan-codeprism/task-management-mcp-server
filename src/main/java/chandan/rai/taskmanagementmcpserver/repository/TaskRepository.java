package chandan.rai.taskmanagementmcpserver.repository;

import chandan.rai.taskmanagementmcpserver.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
