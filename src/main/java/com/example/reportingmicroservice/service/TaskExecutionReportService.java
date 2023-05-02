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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class TaskExecutionReportService {


    private  final  TaskExecutionReportRepository taskExecutionReportRepository;

    TaskExecutionReportService(TaskExecutionReportRepository taskExecutionReportRepository){
        this.taskExecutionReportRepository=taskExecutionReportRepository;
    }

   // private TaskStepExecutionReportRepository taskStepExecutionReportRepository;

    public TaskExecutionReport createTaskExecutionReport(TaskExecutionReport taskExecutionReport) {
        taskExecutionReport.setExecutionTimeSeconds();
        return taskExecutionReportRepository.save(taskExecutionReport);
    }


    public TaskExecutionReport updateTaskExecutionReport(TaskExecutionReport taskExecutionReport,Long taskExecutionReportId) {

        if(!Objects.equals(taskExecutionReport.getId(), taskExecutionReportId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict with Id");
        }




        Optional<TaskExecutionReport> taskStepExecution = taskExecutionReportRepository.findById(taskExecutionReportId);
        if(taskStepExecution.isEmpty() ){
            return null;
        }

        return createTaskExecutionReport(taskExecutionReport);

     }


    public TaskStepExecutionReport createTaskStepExecutionReport(TaskExecutionReport taskExecutionReport, String stepName) {
        TaskStepExecutionReport taskStepExecutionReport = new TaskStepExecutionReport();
        taskExecutionReport.addTaskStepExecutionReport(taskStepExecutionReport);
        taskExecutionReportRepository.save(taskExecutionReport);
        return taskStepExecutionReport;
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
        return taskExecutionReportRepository.findByStatus(status);
    }

    public List<TaskExecutionReport> getAllTaskExecutionReportsSortedByExecutionTimeSeconds() {
        return taskExecutionReportRepository.findAllByOrderByExecutionTimeSecondsAsc();
    }

//    public List<TaskStepExecutionReport> getTaskStepExecutionReportsByTaskExecutionReportIdSortedByStartDateTime(Long id) {
//        return taskStepExecutionReportRepository.findByTaskExecutionReportIdOrderByStartDateTimeAsc(id);
//    }
//
//    public List<TaskStepExecutionReport> getTaskStepExecutionReportsByTaskExecutionReportIdSortedByExecutionTimeSeconds(Long id) {
//        return taskStepExecutionReportRepository.findByTaskExecutionReportIdOrderByExecutionTimeSecondsAsc(id);
//    }



}