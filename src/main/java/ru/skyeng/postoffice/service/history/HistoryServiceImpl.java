package ru.skyeng.postoffice.service.history;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.skyeng.postoffice.dao.HistoryRepository;
import ru.skyeng.postoffice.dto.HistoryRs;
import ru.skyeng.postoffice.dto.StatusRs;
import ru.skyeng.postoffice.exception.NotFoundException;
import ru.skyeng.postoffice.model.History;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    private final ConversionService conversionService;

    @Override
    public List<HistoryRs> getAll(long postalDeliveryId) {

        List<History> historyList = historyRepository.findAllByPostalDeliveryId(postalDeliveryId);

        return historyList.stream()
                .map(el -> conversionService.convert(el, HistoryRs.class)).collect(Collectors.toList());

    }

    @Override
    public StatusRs getStatusById(long postalDeliveryId) {
        History history = historyRepository.findFirstByPostalDeliveryIdOrderByLocalDateTimeDesc(postalDeliveryId)
                .orElseThrow(() -> new NotFoundException("Отправления не найдено"));
        return StatusRs.builder()
                .status(history.getStatus())
                .id(postalDeliveryId)
                .build();
    }
}
