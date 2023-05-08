package com.example.reportingmicroservice.controller;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.service.TaskExecutionReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taskExecutionReports")
public class TaskExecutionReportController {
    private final TaskExecutionReportService service;

    public TaskExecutionReportController(TaskExecutionReportService service){
        this.service=service;
    }

    // Save operation
    @PostMapping("")
    public ResponseEntity<TaskExecutionReport> createTaskExecutionReport(@RequestParam String taskId) {
        TaskExecutionReport taskExecutionReport = service.createTaskExecutionReport(taskId);
        return ResponseEntity.ok(taskExecutionReport);
    }

    @PostMapping("/executeTaskReport/{taskId}")
    public ResponseEntity<TaskExecutionReport> executeTaskReport( @PathVariable String taskId) {

        TaskExecutionReport taskExecutionReport = service.executeTaskReport(taskId);
        return ResponseEntity.ok(taskExecutionReport);
    }


    // update by Id operation
    @PutMapping("/{id}")
    public ResponseEntity<TaskExecutionReport> updateById(@RequestBody TaskExecutionReport taskExecutionReport,@PathVariable("id") Long taskExecutionReportId)
    {
        TaskExecutionReport   taskExecutionReportRes= service.updateTaskExecutionReport(taskExecutionReport,taskExecutionReportId);
        return ResponseEntity.ok(taskExecutionReportRes);
    }

    // Read All operation
    @GetMapping("")
    public   ResponseEntity<List<TaskExecutionReport> >getAllTaskExecutionReports()
    {
        List<TaskExecutionReport> res=  service.getAllTaskExecutionReports();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/taskSteps")
    public   ResponseEntity<List<TaskStepExecutionReport> >getAllTaskStepsExecutionReports(@PathVariable Long id)
    {
        List<TaskStepExecutionReport> res=  service.getAllTaskStepsExecutionReports(id);
        return ResponseEntity.ok(res);
    }


    // Read by Id operation
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TaskExecutionReport>> getTaskExecutionReportById(@PathVariable("id") Long taskExecutionReportId)
    {
        Optional<TaskExecutionReport> res= service.getTaskExecutionReportById(taskExecutionReportId);
        return ResponseEntity.ok(res);
    }

    // delete by Id operation
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long taskExecutionReportId)
    {
         service.deleteTaskExecutionReportsById(taskExecutionReportId);
    }


    // Read by status operation

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskExecutionReport>> getTaskExecutionReportsByStatus(@PathVariable Status status) {
        List<TaskExecutionReport> taskExecutionReports = service.getTaskExecutionReportsByStatus(status);
        return ResponseEntity.ok(taskExecutionReports);
    }


    // Read all sorted by execution time
    @GetMapping("/findAllSortedByExecutionTime")
    public ResponseEntity<List<TaskExecutionReport>> getAllTaskExecutionReportsSortedByExecutionTimeSeconds()
    {
        List<TaskExecutionReport> res= service.getAllTaskExecutionReportsSortedByExecutionTimeSeconds();
        return ResponseEntity.ok(res);
    }

    // delete All  operation
    @DeleteMapping("")
    public void deleteAll()
    {
        service.deleteAllTaskExecutionReports();
    }

}



