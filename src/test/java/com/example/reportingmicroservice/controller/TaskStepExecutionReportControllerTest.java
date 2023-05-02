//package com.example.reportingmicroservice.controller;
//
//
//import com.example.reportingmicroservice.entity.Status;
//import com.example.reportingmicroservice.entity.TaskExecutionReport;
//import com.example.reportingmicroservice.entity.TaskStepExecutionReport;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TaskStepExecutionReportControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//    @BeforeEach
//     void setup() throws Exception {
//
//        //Setting Parent taskReport
//        TaskExecutionReport taskExecutionReport = new TaskExecutionReport();
//        taskExecutionReport.setId(1L);
//        taskExecutionReport.setStartDateTime(LocalDateTime.now());
//        taskExecutionReport.setEndDateTime(LocalDateTime.now().plusSeconds(5L));
//
//
//        mockMvc.perform(post("/taskExecutionReports/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(taskExecutionReport)))
//                .andExpect(status().isOk())
//                .andExpect( jsonPath("$.id", is(1)))
//                .andExpect( jsonPath("$.status", is("PENDING")))
//                .andExpect( jsonPath("$.executionTimeSeconds", is(5)));
//
//
//
//        TaskStepExecutionReport taskStepExecutionReport = new TaskStepExecutionReport();
//        taskStepExecutionReport.setId(1L);
//        taskStepExecutionReport.setTaskExecutionId(1L);
//        taskStepExecutionReport.setStepName("StepA");
//        taskStepExecutionReport.setStartDateTime(LocalDateTime.now());
//        taskStepExecutionReport.setEndDateTime(LocalDateTime.now().plusSeconds(5L));
//        taskStepExecutionReport.setStatus(Status.SUCCESS);
//
//        mockMvc.perform(post("/taskStepExecutionReports/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(taskStepExecutionReport)))
//                .andExpect(status().isOk())
//                .andExpect( jsonPath("$.id", is(1)))
//                .andExpect( jsonPath("$.status", is("SUCCESS")))
//                .andExpect( jsonPath("$.executionTimeSeconds", is(5)));
//
//    }
//
//    @Test
//     void testFetchAll() throws Exception {
//
//
//
//        TaskStepExecutionReport taskStepExecutionReport = new TaskStepExecutionReport();
//        taskStepExecutionReport.setId(2L);
//        taskStepExecutionReport.setTaskExecutionId(1L);
//        taskStepExecutionReport.setStartDateTime(LocalDateTime.now());
//        taskStepExecutionReport.setEndDateTime(LocalDateTime.now().plusSeconds(15L));
//        taskStepExecutionReport.setStatus(Status.SUCCESS);
//
//
//        mockMvc.perform(post("/taskStepExecutionReports/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(taskStepExecutionReport)));
//
//        mockMvc.perform(get("/taskStepExecutionReports/"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)));}
//
//    @Test
//     void testFindById() throws Exception {
//
//
//        mockMvc.perform(get("/taskStepExecutionReports/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)));
//    }
//
//    @Test
//     void testDeleteById() throws Exception {
//
//
//        mockMvc.perform(delete("/taskStepExecutionReports/{id}", 1))
//                .andExpect(status().isOk());
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}