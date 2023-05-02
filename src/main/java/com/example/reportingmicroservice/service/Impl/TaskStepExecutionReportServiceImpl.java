package com.example.reportingmicroservice.service.Impl;

import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.repository.TaskStepExecutionReportRepository;
import com.example.reportingmicroservice.service.TaskExecutionReportService;
import com.example.reportingmicroservice.service.TaskStepExecutionReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStepExecutionReportServiceImpl  implements TaskStepExecutionReportService {


    private final TaskExecutionReportService taskExecutionReportService;
    private final TaskStepExecutionReportRepository taskStepExecutionReportRepository;

    public TaskStepExecutionReportServiceImpl(TaskExecutionReportService taskStepExecutionReport, TaskStepExecutionReportRepository taskStepExecutionReportRepository){
        this.taskExecutionReportService = taskStepExecutionReport;
        this.taskStepExecutionReportRepository=taskStepExecutionReportRepository;
    }


    @Override
    public Optional<TaskStepExecutionReport> findById(Long taskStepExecutionReportId) {
        return taskStepExecutionReportRepository.findById(taskStepExecutionReportId);
    }

    @Override
    public TaskStepExecutionReport save(TaskStepExecutionReport taskStepExecutionReport) {
        taskStepExecutionReport.setExecutionTimeSeconds();
        taskStepExecutionReport= taskStepExecutionReportRepository.save(taskStepExecutionReport);

        TaskExecutionReport taskExecutionReport = taskExecutionReportService.findById(taskStepExecutionReport.getTaskExecutionId()).get();

        taskExecutionReportService.save(taskExecutionReport);

        return taskStepExecutionReport;
    }

    @Override
    public List<TaskStepExecutionReport> fetchAll() {
         return taskStepExecutionReportRepository.findAll();
    }

    @Override
    public TaskStepExecutionReport update(TaskStepExecutionReport taskStepExecutionReport, Long taskStepExecutionReportId) {
        Optional<TaskStepExecutionReport> taskStepExecution = taskStepExecutionReportRepository.findById(taskStepExecutionReportId);
        if(taskStepExecution.isEmpty()){
            return null;
        }

        return save(taskStepExecutionReport);
    }

    @Override
    public void deleteById(Long taskStepExecutionReportId) {
        Optional<TaskStepExecutionReport> taskStepExecution = taskStepExecutionReportRepository.findById(taskStepExecutionReportId);


        taskStepExecutionReportRepository.deleteById(taskStepExecutionReportId);

        if (taskStepExecution.isPresent()) {

            TaskExecutionReport taskExecutionReport = taskExecutionReportService.findById(taskStepExecution.get().getTaskExecutionId()).get();

            taskExecutionReportService.save(taskExecutionReport);

        }
    }

    @Override
    public List<TaskStepExecutionReport> findByTaskExecutionIdOrderByStartDateTimeAsc(Long taskExecutionId) {
        return taskStepExecutionReportRepository.findByTaskExecutionIdOrderByStartDateTimeAsc(taskExecutionId);
    }

    @Override
    public List<TaskStepExecutionReport> findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(Long taskExecutionId) {
        return taskStepExecutionReportRepository.findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(taskExecutionId);
    }

    @Override
    public void deleteAll() {
        taskStepExecutionReportRepository.deleteAll();
    }
}