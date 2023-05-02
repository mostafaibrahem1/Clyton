package com.example.reportingmicroservice.controller;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.service.TaskExecutionReportService;
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
    @PostMapping("/")
    public TaskExecutionReport save(
            @RequestBody TaskExecutionReport taskExecutionReport)
    {
        return service.save(taskExecutionReport);
    }

    // update by Id operation
    @PutMapping("/{id}")
    public TaskExecutionReport updateById(@RequestBody TaskExecutionReport taskExecutionReport,@PathVariable("id") Long taskExecutionReportId)
    {
        return service.update(taskExecutionReport,taskExecutionReportId);
    }

    // Read All operation
    @GetMapping("/")
    public List<TaskExecutionReport> fetchAll()
    {
        return service.fetchAll();
    }
    // Read by Id operation
    @GetMapping("/{id}")
    public Optional<TaskExecutionReport> findById(@PathVariable("id") Long taskExecutionReportId)
    {
        return service.findById(taskExecutionReportId);
    }

    // delete by Id operation
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long taskExecutionReportId)
    {
         service.deleteById(taskExecutionReportId);
    }


    // Read by status operation
    @GetMapping("/findByStatus")
    public List<TaskExecutionReport> findByStatus(@RequestParam Status status)
    {
        return service.findByStatus(status);
    }

    // Read all sorted by execution time
    @GetMapping("/findAllSortedByExecutionTime")
    public List<TaskExecutionReport> findAllSortedByExecutionTime()
    {
        return service.findAllByOrderByExecutionTimeSecondsAsc();
    }

    // delete All  operation
    @DeleteMapping("/")
    public void deleteAll()
    {
        service.deleteAll();
    }



}



