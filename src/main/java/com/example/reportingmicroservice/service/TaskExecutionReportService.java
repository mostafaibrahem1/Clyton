package com.example.reportingmicroservice.service;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.repository.TaskExecutionReportRepository;
import com.example.reportingmicroservice.repository.TaskStepExecutionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskExecutionReportService {
    private  final  TaskExecutionReportRepository taskExecutionReportRepository;
    TaskExecutionReportService(TaskExecutionReportRepository taskExecutionReportRepository){
        this.taskExecutionReportRepository=taskExecutionReportRepository;
    }

    public TaskExecutionReport createTaskExecutionReport(String taskId) {
        TaskExecutionReport taskExecutionReport = new TaskExecutionReport(taskId);
        return taskExecutionReportRepository.save(taskExecutionReport);
    }
    public TaskExecutionReport executeTaskReport(String taskId) {

        TaskExecutionReport taskExecutionReport = taskExecutionReportRepository.findByTaskId(taskId);
        taskExecutionReport.setEndDateTime(LocalDateTime.now());
        taskExecutionReport.setExecutionTimeSeconds();
        return taskExecutionReportRepository.save(taskExecutionReport);
    }

    public TaskExecutionReport updateTaskExecutionReport(TaskExecutionReport taskExecutionReport,Long taskExecutionReportId) {
        if(!Objects.equals(taskExecutionReport.getId(), taskExecutionReportId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict with Id");
        }

        Optional<TaskExecutionReport> oldTaskExecution = taskExecutionReportRepository.findById(taskExecutionReportId);
        if(oldTaskExecution.isEmpty() ){
            return null;
        }

        return   taskExecutionReportRepository.save(taskExecutionReport);
     }


    public List<TaskExecutionReport> getAllTaskExecutionReports() {
        return taskExecutionReportRepository.findAll();
    }

    public void deleteTaskExecutionReportsById(Long taskExecutionId) {
         taskExecutionReportRepository.deleteById(taskExecutionId);
    }

    public void deleteAllTaskExecutionReports() {
        taskExecutionReportRepository.deleteAll();
    }
    public Optional<TaskExecutionReport> getTaskExecutionReportById(Long id) {
        return taskExecutionReportRepository.findById(id);
    }

    public List<TaskExecutionReport> getTaskExecutionReportsByStatus(Status status) {
        return taskExecutionReportRepository.findAll().stream().filter(a ->a.getStatus().equals(status)).collect(Collectors.toList());
    }

    public List<TaskExecutionReport> getAllTaskExecutionReportsSortedByExecutionTimeSeconds() {
        return taskExecutionReportRepository.findAllByOrderByExecutionTimeSecondsAsc();
    }


    public List<TaskStepExecutionReport> getAllTaskStepsExecutionReports(Long id) {
        return taskExecutionReportRepository.findById(id).orElseThrow().getTaskStepExecutionReports();
    }
}