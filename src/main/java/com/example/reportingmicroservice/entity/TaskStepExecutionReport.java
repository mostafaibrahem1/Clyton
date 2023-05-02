package com.example.reportingmicroservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskStepExecutionReport {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_execution_id", nullable = false)
    private TaskExecutionReport taskExecutionReport;



    private String stepName;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
    private Long executionTimeSeconds;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String errorMessage;

    public void setExecutionTimeSeconds() {
        Duration duration = Duration.between(startDateTime, endDateTime);
        this.executionTimeSeconds = Math.abs(duration.getSeconds());

    }




}
