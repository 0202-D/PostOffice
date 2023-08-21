package ru.skyeng.postoffice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.skyeng.postoffice.dto.HistoryRs;
import ru.skyeng.postoffice.dto.StatusRs;
import ru.skyeng.postoffice.model.Status;
import ru.skyeng.postoffice.service.history.HistoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


 class HistoryControllerTest {
 @Mock
 private HistoryService historyService;

 @InjectMocks
 private HistoryController historyController;

 private MockMvc mockMvc;

 @BeforeEach
 void setUp() {
  MockitoAnnotations.initMocks(this);
  mockMvc = standaloneSetup(historyController).build();
 }

 @Test
 void testGetAll() throws Exception {
  long postalDeliveryId = 1;
  List<HistoryRs> historyList = new ArrayList<>();
  historyList.add(new HistoryRs(1, Status.ARRIVED, LocalDateTime.now()));
  when(historyService.getAll(postalDeliveryId)).thenReturn(historyList);

  mockMvc.perform(MockMvcRequestBuilders.get("/history/{id}", postalDeliveryId))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(historyList.size()));
 }

 @Test
 void testGetStatusById() throws Exception {
  long postalDeliveryId = 1;
  StatusRs status = new StatusRs(postalDeliveryId, Status.ARRIVED);
  when(historyService.getStatusById(postalDeliveryId)).thenReturn(status);

  mockMvc.perform(MockMvcRequestBuilders.get("/history/status/{id}", postalDeliveryId))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(status.id()))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(status.status().toString()));
 }

}

