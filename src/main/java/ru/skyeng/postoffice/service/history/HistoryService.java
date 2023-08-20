package ru.skyeng.postoffice.service.history;

import ru.skyeng.postoffice.dto.HistoryRs;
import ru.skyeng.postoffice.dto.StatusRs;

import java.util.List;


public interface HistoryService {
    List<HistoryRs> getAll(long postalDeliveryId);


    StatusRs getStatusById(long postalDeliveryId);
}
