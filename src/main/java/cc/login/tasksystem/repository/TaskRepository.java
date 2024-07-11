package cc.login.tasksystem.repository;

import cc.login.tasksystem.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository <Task, Long> {
}
