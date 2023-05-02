package com.example.reportingmicroservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class TaskExecutionReport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String taskId;

    private LocalDateTime startDateTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDateTime;
    private Long executionTimeSeconds;
    private String errorMessage;



    @OneToMany(mappedBy = "taskExecutionReport", cascade = CascadeType.ALL)
    private List<TaskStepExecutionReport> taskStepExecutionReports = new ArrayList<>();


    // executionTimeSeconds: the amount of time (in seconds) that it took to execute the task and all task step.
    public void setExecutionTimeSeconds() {

        Long taskExecutionTimeSeconds = ChronoUnit.SECONDS.between(startDateTime,endDateTime);

        Long allTaskStepsExecutionTimeSeconds = 0L;
        if(taskStepExecutionReports!=null){
        for(TaskStepExecutionReport step : taskStepExecutionReports){
            allTaskStepsExecutionTimeSeconds += step.getExecutionTimeSeconds();
        }}

        this.executionTimeSeconds = taskExecutionTimeSeconds + allTaskStepsExecutionTimeSeconds;

    }

    public void addTaskStepExecutionReport(TaskStepExecutionReport taskStepExecutionReport) {
        taskStepExecutionReports.add(taskStepExecutionReport);
        taskStepExecutionReport.setTaskExecutionReport(this);
    }



    public Status getStatus() {
        if(taskStepExecutionReports!=null) {
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

}
