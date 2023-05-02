package com.example.reportingmicroservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.CascadeType;
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
    private String taskId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDateTime;
    private Long executionTimeSeconds;
    private String errorMessage;


    @Enumerated(EnumType.STRING)
    private Status status;

   // @OneToMany(mappedBy = "taskExecutionId")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taskExecutionId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    private List<TaskStepExecutionReport> taskStepExecutionReports;



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

    public void setStatus() {
        Status status = Status.PENDING;
        if(taskStepExecutionReports!=null){
            status=Status.SUCCESS;// unless there's other indicators
        for(TaskStepExecutionReport step : taskStepExecutionReports){
            if(step.getStatus().equals(Status.RUNNING))
            {
                status=Status.RUNNING;
                break;
            } else if (step.getStatus().equals(Status.FAILURE)) {
                status=Status.FAILURE;

            }
        }

        }

        this.status = status;
    }
}
