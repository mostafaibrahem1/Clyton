package com.example.reportingmicroservice.service;

 import com.example.reportingmicroservice.entity.TaskStepExecutionReport;

import java.util.List;
import java.util.Optional;

public interface TaskStepExecutionReportService {

    //Get by id
    Optional<TaskStepExecutionReport> findById(Long taskExecutionReportId);

    // Save operation
    TaskStepExecutionReport save(TaskStepExecutionReport taskStepExecutionReport);

    // Read operation
    List<TaskStepExecutionReport> fetchAll();

    // Update operation
    TaskStepExecutionReport update(TaskStepExecutionReport taskStepExecutionReport,
                                Long taskStepExecutionReportId);

    // Delete operation
    void deleteById(Long taskStepExecutionReportId);

    List<TaskStepExecutionReport> findByTaskExecutionReportIdOrderByStartDateTimeAsc(Long taskExecutionId);
    List<TaskStepExecutionReport> findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(Long taskExecutionId );


    // Delete operation
    void deleteAll();
}
