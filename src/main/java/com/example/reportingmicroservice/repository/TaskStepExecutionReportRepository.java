package com.example.reportingmicroservice.repository;

import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Interface extending JpaRepository
public interface TaskStepExecutionReportRepository
        extends JpaRepository<TaskStepExecutionReport, Long> {

    List<TaskStepExecutionReport> findByTaskExecutionReportIdOrderByStartDateTimeAsc(Long id);

    List<TaskStepExecutionReport> findByTaskExecutionReportIdOrderByExecutionTimeSecondsAsc(Long id);

 }