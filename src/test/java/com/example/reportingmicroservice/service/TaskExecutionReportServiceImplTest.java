package com.example.reportingmicroservice.service;

import com.example.reportingmicroservice.entity.Status;
import com.example.reportingmicroservice.entity.TaskExecutionReport;
import com.example.reportingmicroservice.repository.TaskExecutionReportRepository;
import com.example.reportingmicroservice.service.Impl.TaskExecutionReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskExecutionReportServiceImplTest {

    private TaskExecutionReportService service;
    private TaskExecutionReportRepository mockRepo;

    @BeforeEach
    void setup(){
        mockRepo = mock(TaskExecutionReportRepository.class);
        service = new TaskExecutionReportServiceImpl(mockRepo);
    }



    @Test
    void findBy_Id(){

        TaskExecutionReport taskExecutionReport = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .taskId("1")
                .id(1L)
                .build();

        when(mockRepo.findById(1L)).thenReturn(Optional.of(taskExecutionReport));

        Optional<TaskExecutionReport> actualReport = service.findById(1L);

        assertTrue(actualReport.isPresent());
        assertEquals(taskExecutionReport, actualReport.get());
        verify(mockRepo).findById(1L);


    }
    @Test
    void findBy_status(){

        TaskExecutionReport taskExecutionReport = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .taskId("1")
                .id(1L)
                .build();

        when(mockRepo.findByStatus(Status.SUCCESS)).thenReturn(Collections.singletonList(taskExecutionReport));

        List<TaskExecutionReport> actualReport = service.findByStatus(Status.SUCCESS);

        assertTrue(!actualReport.isEmpty());
        assertEquals(actualReport.size(),1);
        verify(mockRepo).findByStatus(Status.SUCCESS);


    }


    @Test
    void findAll(){

        TaskExecutionReport taskExecutionReport = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .taskId("1")
                .id(1L)
                .build();

        when(mockRepo.findAll()).thenReturn(Collections.singletonList(taskExecutionReport));

        List<TaskExecutionReport> actualReport = service.fetchAll();

        assertTrue(!actualReport.isEmpty());
        assertEquals(actualReport.size(), 1);
        verify(mockRepo).findAll();


    }



    @Test
    void findAllByOrderByExecutionTimeSecondsAsc(){

        TaskExecutionReport taskExecutionReport = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .executionTimeSeconds(10L)
                .taskId("1")
                .id(1L)
                .build();

        TaskExecutionReport taskExecutionReport2 = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(15))
                .executionTimeSeconds(15L)
                .taskId("1")
                .id(1L)
                .build();
        TaskExecutionReport taskExecutionReport3 = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(23))
                .executionTimeSeconds(23L)
                .taskId("1")
                .id(1L)
                .build();


        List<TaskExecutionReport> taskExecutionReportList = new ArrayList<>();
        taskExecutionReportList.add(taskExecutionReport);
        taskExecutionReportList.add(taskExecutionReport2);
        taskExecutionReportList.add(taskExecutionReport3);

        when(mockRepo.findAllByOrderByExecutionTimeSecondsAsc()).thenReturn(taskExecutionReportList);

        List<TaskExecutionReport> actualReport = service.findAllByOrderByExecutionTimeSecondsAsc();

        assertTrue(!actualReport.isEmpty());
        assertEquals(actualReport.size(), 3);
        assertEquals(actualReport.get(0).getExecutionTimeSeconds(), 10L);

        assertEquals(actualReport.get(2).getExecutionTimeSeconds(), 23L);

        verify(mockRepo).findAllByOrderByExecutionTimeSecondsAsc();




    }





    @Test
    void findAllByStatus(){

        TaskExecutionReport taskExecutionReport = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(10))
                .executionTimeSeconds(10L)
                .taskId("1")
                .id(1L)
                .build();

        TaskExecutionReport taskExecutionReport2 = TaskExecutionReport.builder()
                .status(Status.SUCCESS)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(15))
                .executionTimeSeconds(15L)
                .taskId("1")
                .id(1L)
                .build();
        TaskExecutionReport taskExecutionReport3 = TaskExecutionReport.builder()
                .status(Status.FAILURE)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusSeconds(23))
                .executionTimeSeconds(23L)
                .taskId("1")
                .id(1L)
                .build();


        List<TaskExecutionReport> taskExecutionReportList = new ArrayList<>();
        taskExecutionReportList.add(taskExecutionReport);
        taskExecutionReportList.add(taskExecutionReport2);


        when(mockRepo.findByStatus(Status.SUCCESS)).thenReturn(taskExecutionReportList);

        List<TaskExecutionReport> actualReport = service.findByStatus(Status.SUCCESS);


        assertEquals(actualReport.size(), 2);
        assertEquals(actualReport.get(0).getStatus(), Status.SUCCESS);

        assertEquals(actualReport.get(1).getStatus(), Status.SUCCESS);

        verify(mockRepo).findByStatus(Status.SUCCESS);




    }

}
