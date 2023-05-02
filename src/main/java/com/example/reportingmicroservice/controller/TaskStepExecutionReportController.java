package com.example.reportingmicroservice.controller;

import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.service.TaskExecutionReportService;
import com.example.reportingmicroservice.service.TaskStepExecutionReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taskStepExecutionReports")
public class TaskStepExecutionReportController {
    private final TaskStepExecutionReportService service;
    private final TaskExecutionReportService taskService;


    public TaskStepExecutionReportController(TaskStepExecutionReportService service,TaskExecutionReportService taskService){
        this.service=service;
        this.taskService=taskService;
    }

    // Save operation
    @PostMapping("")
    public TaskStepExecutionReport save(
            @RequestBody TaskStepExecutionReport taskStepExecutionReport)
    {

        TaskExecutionReport taskExecutionReport = taskService.getTaskExecutionReportById(taskStepExecutionReport.getTaskExecutionReport().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task execution report not found"));
       taskStepExecutionReport= service.save(taskStepExecutionReport,taskExecutionReport);
        return taskStepExecutionReport;
    }
    // Save operation
    @PutMapping("/{id}")
    public TaskStepExecutionReport update(@RequestBody TaskStepExecutionReport taskStepExecutionReport,@PathVariable("id") Long taskStepExecutionReportId)
    {
        TaskStepExecutionReport oldTaskStepExecutionReport = service.findById(taskStepExecutionReportId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task step execution report not found"));

        return service.update(taskStepExecutionReport);
    }

    // Read All operation
    @GetMapping("")
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
    @DeleteMapping("")
    public void deleteAll( )
    {
        service.deleteAll();
    }


    @GetMapping("/findAllTaskSteps/{id}")
    public List<TaskStepExecutionReport> findAllTaskSteps(@PathVariable("id") Long taskExecutionReportId)
    {
        return service.findAllTaskSteps(taskExecutionReportId);
    }
}



