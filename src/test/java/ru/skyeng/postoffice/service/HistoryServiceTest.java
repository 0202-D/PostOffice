package ru.skyeng.postoffice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import ru.skyeng.postoffice.dao.HistoryRepository;
import ru.skyeng.postoffice.dto.HistoryRs;
import ru.skyeng.postoffice.dto.StatusRs;
import ru.skyeng.postoffice.model.History;
import ru.skyeng.postoffice.model.Status;
import ru.skyeng.postoffice.service.history.HistoryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
 class HistoryServiceTest {
    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private HistoryServiceImpl historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testGetAll() {
        long postalDeliveryId = 1;
        List<History> mockHistoryList = new ArrayList<>();
        mockHistoryList.add(new History());

        Mockito.when(historyRepository.findAllByPostalDeliveryId(anyLong())).thenReturn(mockHistoryList);
        Mockito.when(conversionService.convert(Mockito.any(), Mockito.eq(HistoryRs.class)))
                .thenReturn(HistoryRs.builder().build());

        List<HistoryRs> result = historyService.getAll(postalDeliveryId);

        assertEquals(1, result.size());
    }
    @Test
     void testGetStatusById() {
        long postalDeliveryId = 1;
        History mockHistory = new History();
        mockHistory.setStatus(Status.ARRIVED);

        Mockito.when(historyRepository.findFirstByPostalDeliveryIdOrderByLocalDateTimeDesc(anyLong()))
                .thenReturn(Optional.of(mockHistory));

        StatusRs result = historyService.getStatusById(postalDeliveryId);

        assertEquals(Status.ARRIVED, result.status());
        assertEquals(postalDeliveryId, result.id());
    }
}