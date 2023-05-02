package com.example.reportingmicroservice.service;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskExecutionReport;

import java.util.List;
import java.util.Optional;

public interface TaskExecutionReportService {

    //Get by id
    Optional<TaskExecutionReport>  findById(Long taskExecutionReportId);
    // Save operation
    TaskExecutionReport save(TaskExecutionReport taskExecutionReport);

    // Read operation
    List<TaskExecutionReport> fetchAll();

    // Update operation
    TaskExecutionReport update(TaskExecutionReport taskExecutionReport,
                                Long taskExecutionReportId);

    // Delete operation
    void deleteById(Long taskExecutionReportId);

    List<TaskExecutionReport>  findByStatus(Status status);
    List<TaskExecutionReport>  findAllByOrderByExecutionTimeSecondsAsc();

    // Delete all operation
    void deleteAll();

}
