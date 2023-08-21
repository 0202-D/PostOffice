package ru.skyeng.postoffice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import ru.skyeng.postoffice.dao.HistoryRepository;
import ru.skyeng.postoffice.dao.PostOfficeRepository;
import ru.skyeng.postoffice.dao.PostalDeliveryRepository;
import ru.skyeng.postoffice.dto.ArriveRq;
import ru.skyeng.postoffice.dto.DeliveryRegistrationRq;
import ru.skyeng.postoffice.model.*;
import ru.skyeng.postoffice.service.post.PostServiceImpl;


import java.util.Optional;

import static org.mockito.Mockito.*;

public class PostServiceImplTest {
    @Mock
    private PostalDeliveryRepository postalDeliveryRepository;

    @Mock
    private ConversionService conversionService;

    @Mock
    private PostOfficeRepository postOfficeRepository;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testDeliveryRegistration() {
        DeliveryRegistrationRq rq = new DeliveryRegistrationRq(DepartureType.LETTER, "123", "Address", "Recipients");
        PostalDelivery mockPostalDelivery = new PostalDelivery();

        when(conversionService.convert(rq, PostalDelivery.class)).thenReturn(mockPostalDelivery);

        postService.deliveryRegistration(rq);

        verify(postalDeliveryRepository).save(mockPostalDelivery);
    }

    @Test
     void testArrive() {
        long postalDeliveryId = 1L;
        int postOfficeId = 2;
        ArriveRq arriveRq = new ArriveRq(postalDeliveryId, postOfficeId);
        PostalDelivery postalDelivery = new PostalDelivery();
        PostOffice postOffice = new PostOffice();
        when(postalDeliveryRepository.findById(postalDeliveryId)).thenReturn(Optional.of(postalDelivery));
        when(postOfficeRepository.findById(postOfficeId)).thenReturn(Optional.of(postOffice));

        postService.arrive(arriveRq);

        ArgumentCaptor<History> argumentCaptor = ArgumentCaptor.forClass(History.class);
        verify(historyRepository, times(1)).save(argumentCaptor.capture());
        History savedHistory = argumentCaptor.getValue();
        Assertions.assertEquals(savedHistory.getPostalDelivery(), postalDelivery);
        Assertions.assertEquals(savedHistory.getPostOffice(), postOffice);
        Assertions.assertEquals(savedHistory.getStatus(), Status.ARRIVED);
        Assertions.assertNotNull(savedHistory.getLocalDateTime());
    }

    @Test
     void testGone() {
        long postalDeliveryId = 1L;
        History history = new History();
        history.setPostalDelivery(new PostalDelivery());
        history.setPostOffice(new PostOffice());
        history.setStatus(Status.ARRIVED);
        when(historyRepository.findByPostalDeliveryIdAndStatus(postalDeliveryId, Status.ARRIVED)).thenReturn(Optional.of(history));

        postService.gone(postalDeliveryId);

        ArgumentCaptor<History> argumentCaptor = ArgumentCaptor.forClass(History.class);
        verify(historyRepository, times(1)).save(argumentCaptor.capture());
        History savedHistory = argumentCaptor.getValue();
        Assertions.assertEquals(savedHistory.getPostalDelivery(), history.getPostalDelivery());
        Assertions.assertEquals(savedHistory.getPostOffice(), history.getPostOffice());
        Assertions.assertEquals(savedHistory.getStatus(), Status.GONE);
        Assertions.assertNotNull(savedHistory.getLocalDateTime());
    }
}
