package com.example.reportingmicroservice.service;

import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.repository.TaskExecutionReportRepository;
import com.example.reportingmicroservice.repository.TaskStepExecutionReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStepExecutionReportService {


    private final TaskExecutionReportRepository taskExecutionReportRepository;
    private final TaskStepExecutionReportRepository taskStepExecutionReportRepository;

    public TaskStepExecutionReportService(TaskExecutionReportRepository taskExecutionReportRepository, TaskStepExecutionReportRepository taskStepExecutionReportRepository){
        this.taskExecutionReportRepository = taskExecutionReportRepository;
        this.taskStepExecutionReportRepository=taskStepExecutionReportRepository;
    }



    public Optional<TaskStepExecutionReport> findById(Long taskStepExecutionReportId) {
        return taskStepExecutionReportRepository.findById(taskStepExecutionReportId);
    }


    public TaskStepExecutionReport save(TaskStepExecutionReport taskStepExecutionReport, TaskExecutionReport taskExecutionReport) {



        taskStepExecutionReport.setTaskExecutionReport(taskExecutionReport);
        taskStepExecutionReport.setExecutionTimeSeconds();
        taskStepExecutionReportRepository.save(taskStepExecutionReport);

        return taskStepExecutionReport;
    }



    public List<TaskStepExecutionReport> fetchAll() {
         return taskStepExecutionReportRepository.findAll();
    }


    public TaskStepExecutionReport update(TaskStepExecutionReport taskStepExecutionReport) {
        taskStepExecutionReport.setExecutionTimeSeconds();
        taskStepExecutionReportRepository.save(taskStepExecutionReport);

        return taskStepExecutionReport;
    }


    public void deleteById(Long taskStepExecutionReportId) {

//     TaskStepExecutionReport taskStepExecutionReport=    taskStepExecutionReportRepository.findById(taskStepExecutionReportId).get();
//
//     TaskExecutionReport task = taskExecutionReportRepository.findById(taskStepExecutionReport.getTaskExecutionReport().getId()).get();
//     tas
        taskStepExecutionReportRepository.deleteById(taskStepExecutionReportId);



    }


    public List<TaskStepExecutionReport> findByTaskExecutionReportIdOrderByStartDateTimeAsc(Long taskExecutionId) {
        return taskStepExecutionReportRepository.findByTaskExecutionReportIdOrderByStartDateTimeAsc(taskExecutionId);
    }


    public List<TaskStepExecutionReport> findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(Long taskExecutionId) {
        return taskStepExecutionReportRepository.findByTaskExecutionReportIdOrderByExecutionTimeSecondsAsc(taskExecutionId);
    }


    public void deleteAll() {
        taskStepExecutionReportRepository.deleteAll();
    }

    public List<TaskStepExecutionReport> findAllTaskSteps(Long taskExecutionReportId) {

        return taskStepExecutionReportRepository.findAllByTaskExecutionReportId(taskExecutionReportId);

    }
}