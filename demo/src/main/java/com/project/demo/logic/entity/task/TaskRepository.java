package com.project.demo.logic.entity.task;

import com.project.demo.logic.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT new com.project.demo.logic.entity.task.TaskDTO(t.id, t.name, t.priority, t.description, t.startDate, t.endDate, t.visible, t.recurrent, t.repeatTimes) FROM Task t WHERE t.user.id = :userId and t.visible = true")
    List<TaskDTO> findByUser(@Param("userId") Long userId);

    @Query("SELECT new com.project.demo.logic.entity.task.TaskDTO(t.id, t.name, t.priority, t.description, t.startDate, t.endDate, t.visible, t.recurrent, t.repeatTimes) FROM Task t WHERE t.user.id = :userId AND t.visible = true AND t.endDate <= CURRENT_TIMESTAMP")
    List<TaskDTO> findHistoryByUser(@Param("userId") Long userId);

    @Query("SELECT new com.project.demo.logic.entity.task.TaskDTO(t.id, t.name, t.priority, t.description, t.startDate, t.endDate, t.visible, t.recurrent, t.repeatTimes) FROM Task t WHERE t.user.id = :userId AND t.visible = true AND t.startDate BETWEEN :startDate AND :endDate")
    List<TaskDTO> findNextTasks(@Param("userId") Long userId, @Param("startDate") Date startInterval, @Param("endDate") Date endInterval);

    void deleteByParentId(Long parentId);

    @Query("SELECT new com.project.demo.logic.entity.task.TaskDTO(t.id, t.name, t.priority, t.description, t.startDate, t.endDate, t.visible, t.recurrent, t.repeatTimes) " +
            "FROM Task t WHERE t.user.id = :userId AND t.visible = true AND t.startDate >= :startDate " +
            "AND t.isCompleted = false AND t.verified = false")
    List<TaskDTO> findFutureTasks(@Param("userId") Long userId, @Param("startDate") Date startDate);

    @Query("SELECT new com.project.demo.logic.entity.task.TaskDTO(t.id, t.name, t.priority, t.description, t.startDate, t.endDate, t.visible, t.recurrent, t.repeatTimes) " +
            "FROM Task t WHERE t.user.id = :userId AND t.visible = true " +
            "AND t.isCompleted = true AND t.verified = false")
    List<TaskDTO> findCompletedTasks(@Param("userId") Long userId);

}
