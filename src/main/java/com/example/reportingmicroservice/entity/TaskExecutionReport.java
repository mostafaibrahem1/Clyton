package com.example.reportingmicroservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task_execution_report")
public class TaskExecutionReport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true)
    private String taskId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;
    private Long executionTimeSeconds;
    private String errorMessage;

  //  @JsonIgnore
    @OneToMany(mappedBy = "taskExecutionReport", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TaskStepExecutionReport> taskStepExecutionReports=new ArrayList<>();

    public TaskExecutionReport(String taskId) {
        this.taskId = taskId;
        this.startDateTime = LocalDateTime.now();
    }



    public Status getStatus() {
        if (taskStepExecutionReports != null) {
            if (taskStepExecutionReports.stream().allMatch(t -> t.getStatus().equals(Status.SUCCESS))) {
                return Status.SUCCESS;
            } else if (taskStepExecutionReports.stream().anyMatch(t -> t.getStatus().equals(Status.RUNNING))) {
                return Status.RUNNING;
            } else {
                return Status.FAILURE;
            }
        }
        return Status.SUCCESS;
    }

    // executionTimeSeconds: the amount of time (in seconds) that it took to execute the task and all task step.
    public void setExecutionTimeSeconds() {
        Long taskExecutionTimeSeconds = ChronoUnit.SECONDS.between(startDateTime, endDateTime);
        Long allTaskStepsExecutionTimeSeconds = 0L;
        if (taskStepExecutionReports != null) {
            for (TaskStepExecutionReport step : taskStepExecutionReports) {
                allTaskStepsExecutionTimeSeconds += step.getExecutionTimeSeconds();
            }
        }

        this.executionTimeSeconds = taskExecutionTimeSeconds + allTaskStepsExecutionTimeSeconds;


    }
}
