package ru.skyeng.postoffice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skyeng.postoffice.dto.ArriveRq;
import ru.skyeng.postoffice.dto.DeliveryRegistrationRq;
import ru.skyeng.postoffice.model.DepartureType;
import ru.skyeng.postoffice.service.post.PostServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


 class PostControllerTest {

 @Mock
 private PostServiceImpl postService;

 @InjectMocks
 private PostController postController;

 private MockMvc mockMvc;
 private ObjectMapper objectMapper;

 @BeforeEach
 void setUp() {
  MockitoAnnotations.initMocks(this);
  mockMvc = standaloneSetup(postController).build();
  objectMapper = new ObjectMapper();
 }

 @Test
 public void testDeliveryRegistration() throws Exception {
  DeliveryRegistrationRq rq = new DeliveryRegistrationRq(DepartureType.LETTER, "123", "Address", "Recipients");

  mockMvc.perform(MockMvcRequestBuilders.post("/post/registration")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(rq)))
          .andExpect(status().isOk());

  verify(postService).deliveryRegistration(rq);
 }

 @Test
 public void testGone() throws Exception {
  long postalDeliveryId = 1;

  mockMvc.perform(MockMvcRequestBuilders.put("/post/gone/{postalDeliveryId}", postalDeliveryId))
          .andExpect(status().isOk());

  verify(postService).gone(postalDeliveryId);
 }

 @Test
 public void testArrive() throws Exception {
  ArriveRq arriveRq = new ArriveRq(1L, 1);

  mockMvc.perform(MockMvcRequestBuilders.put("/post/arrive")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(arriveRq)))
          .andExpect(status().isOk());

  verify(postService).arrive(arriveRq);
 }

 @Test
 public void testReceive() throws Exception {
  long postalDeliveryId = 1;

  mockMvc.perform(MockMvcRequestBuilders.put("/post/receive/{postalDeliveryId}", postalDeliveryId))
          .andExpect(status().isOk());

  verify(postService).receive(postalDeliveryId);
 }
}


