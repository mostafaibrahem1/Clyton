package com.example.reportingmicroservice.controller;

import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.service.TaskStepExecutionReportService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taskStepExecutionReports")
public class TaskStepExecutionReportController {
    private final TaskStepExecutionReportService service;

    public TaskStepExecutionReportController(TaskStepExecutionReportService service){
        this.service=service;
    }

    // Save operation
    @PostMapping("/")
    public TaskStepExecutionReport save(
            @RequestBody TaskStepExecutionReport taskStepExecutionReport)
    {
        return service.save(taskStepExecutionReport);
    }
    // Save operation
    @PutMapping("/{id}")
    public TaskStepExecutionReport update(@RequestBody TaskStepExecutionReport taskStepExecutionReport,@PathVariable("id") Long taskStepExecutionReportId)
    {
        return service.update(taskStepExecutionReport,taskStepExecutionReportId);
    }

    // Read All operation
    @GetMapping("/")
    public List<TaskStepExecutionReport> fetchAll()
    {
        return service.fetchAll();
    }
    // Read by Id operation
    @GetMapping("/{id}")
    public Optional<TaskStepExecutionReport> findById(@PathVariable("id") Long taskStepExecutionReportId)
    {
        return service.findById(taskStepExecutionReportId);
    }

    // delete by Id operation
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long taskStepExecutionReportId)
    {
        service.deleteById(taskStepExecutionReportId);
    }



    // Read all OrderByStartDateTime
    @GetMapping("/findByTaskExecutionIdOrderByStartDateTime/{id}")
    public List<TaskStepExecutionReport> findByTaskExecutionIdOrderByStartDateTime(@PathVariable("id") Long taskExecutionReportId)
    {
        return service.findByTaskExecutionReportIdOrderByStartDateTimeAsc(taskExecutionReportId);
    }


    // Read all OrderBy executionTimeSeconds
    @GetMapping("/findByTaskExecutionIdOrderByExecutionTimeSeconds/{id}")
    public List<TaskStepExecutionReport> findByTaskExecutionIdOrderByExecutionTimeSeconds(@PathVariable("id") Long taskExecutionReportId)
    {
        return service.findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(taskExecutionReportId);
    }



    // delete by Id operation
    @DeleteMapping("/")
    public void deleteAll( )
    {
        service.deleteAll();
    }


}



