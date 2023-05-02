package com.example.reportingmicroservice.service;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
import com.example.reportingmicroservice.repository.TaskStepExecutionReportRepository;
import com.example.reportingmicroservice.service.Impl.TaskStepExecutionReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskStepExecutionReportServiceImplTest {

    private TaskStepExecutionReportService service;

    private TaskExecutionReportService taskService;
    private TaskStepExecutionReportRepository mockRepo;

    @BeforeEach
    void setup(){
        mockRepo = mock(TaskStepExecutionReportRepository.class);
        service = new TaskStepExecutionReportServiceImpl(taskService,mockRepo);
    }



    @Test
    void findBy_Id(){

        TaskStepExecutionReport taskExecutionReport = TaskStepExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .taskExecutionId(1L)
                .id(1L)
                .build();

        when(mockRepo.findById(1L)).thenReturn(Optional.of(taskExecutionReport));

        Optional<TaskStepExecutionReport> actualReport = service.findById(1L);

        assertTrue(actualReport.isPresent());
        assertEquals(taskExecutionReport, actualReport.get());
        verify(mockRepo).findById(1L);


    }
    @Test
    void findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(){
        TaskStepExecutionReport taskStepExecutionReport = TaskStepExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(3))
                .executionTimeSeconds(3L)
                .taskExecutionId(1L)
                .id(1L)
                .build();
        TaskStepExecutionReport taskStepExecutionReport2 = TaskStepExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .executionTimeSeconds(10L)
                .taskExecutionId(1L)
                .id(2L)
                .build();

        List<TaskStepExecutionReport> taskStepExecutionReportList = new ArrayList<>();
        taskStepExecutionReportList.add(taskStepExecutionReport);
        taskStepExecutionReportList.add(taskStepExecutionReport2);


        when(mockRepo.findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(1L)).thenReturn(taskStepExecutionReportList);

        List<TaskStepExecutionReport> actualReport = service.findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(1L);

        assertTrue(!actualReport.isEmpty());
        assertEquals(actualReport.size(),2);
        verify(mockRepo).findByTaskExecutionIdOrderByExecutionTimeSecondsAsc(1L);


    }


    @Test
    void findByTaskExecutionIdOrderByStartDateTimeAsc(){
        TaskStepExecutionReport taskStepExecutionReport = TaskStepExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(3))
                .executionTimeSeconds(3L)
                .taskExecutionId(1L)
                .id(1L)
                .build();
        TaskStepExecutionReport taskStepExecutionReport2 = TaskStepExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .executionTimeSeconds(10L)
                .taskExecutionId(1L)
                .id(2L)
                .build();

        List<TaskStepExecutionReport> taskStepExecutionReportList = new ArrayList<>();
        taskStepExecutionReportList.add(taskStepExecutionReport);
        taskStepExecutionReportList.add(taskStepExecutionReport2);


        when(mockRepo.findByTaskExecutionIdOrderByStartDateTimeAsc(1L)).thenReturn(taskStepExecutionReportList);

        List<TaskStepExecutionReport> actualReport = service.findByTaskExecutionIdOrderByStartDateTimeAsc(1L);

        assertTrue(!actualReport.isEmpty());
        assertEquals(actualReport.size(),2);
        verify(mockRepo).findByTaskExecutionIdOrderByStartDateTimeAsc(1L);


    }




}
