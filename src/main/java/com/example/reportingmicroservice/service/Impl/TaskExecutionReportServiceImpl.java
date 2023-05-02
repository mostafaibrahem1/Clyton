package com.example.reportingmicroservice.service.Impl;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.repository.TaskExecutionReportRepository;
import com.example.reportingmicroservice.service.TaskExecutionReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskExecutionReportServiceImpl implements TaskExecutionReportService {

     private final TaskExecutionReportRepository taskExecutionReportRepository;

    public TaskExecutionReportServiceImpl(TaskExecutionReportRepository taskExecutionReportRepository){
        this.taskExecutionReportRepository=taskExecutionReportRepository;
    }


    @Override
    public Optional<TaskExecutionReport> findById(Long taskExecutionReportId) {
        return taskExecutionReportRepository.findById(taskExecutionReportId);
    }

    @Override
    public TaskExecutionReport save(TaskExecutionReport taskExecutionReport) {
        taskExecutionReport.setExecutionTimeSeconds();
        taskExecutionReport.setStatus();
        return taskExecutionReportRepository.save(taskExecutionReport);
    }

    @Override
    public List<TaskExecutionReport> fetchAll() {
         return taskExecutionReportRepository.findAll();
    }

    @Override
    public TaskExecutionReport update(TaskExecutionReport taskExecutionReport, Long taskExecutionReportId) {
        Optional<TaskExecutionReport> taskStepExecution = taskExecutionReportRepository.findById(taskExecutionReportId);
        if(taskStepExecution.isEmpty() ){
            return null;
        }

        if(!Objects.equals(taskStepExecution.get().getId(), taskExecutionReportId)){
            return null;
        }


        return save(taskExecutionReport);
    }

    @Override
    public void deleteById(Long taskExecutionReportId) {
        taskExecutionReportRepository.deleteById(taskExecutionReportId);

    }

    @Override
    public List<TaskExecutionReport> findByStatus(Status status) {
        return taskExecutionReportRepository.findByStatus(status);
    }

    @Override
    public List<TaskExecutionReport> findAllByOrderByExecutionTimeSecondsAsc() {
        return taskExecutionReportRepository.findAllByOrderByExecutionTimeSecondsAsc();
    }

    @Override
    public void deleteAll() {
        taskExecutionReportRepository.deleteAll();
    }

}