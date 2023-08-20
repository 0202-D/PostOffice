package ru.skyeng.postoffice.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.skyeng.postoffice.dao.HistoryRepository;
import ru.skyeng.postoffice.dao.PostOfficeRepository;
import ru.skyeng.postoffice.dao.PostalDeliveryRepository;
import ru.skyeng.postoffice.dto.ArriveRq;
import ru.skyeng.postoffice.dto.DeliveryRegistrationRq;
import ru.skyeng.postoffice.exception.NotFoundException;
import ru.skyeng.postoffice.model.History;
import ru.skyeng.postoffice.model.PostOffice;
import ru.skyeng.postoffice.model.PostalDelivery;
import ru.skyeng.postoffice.model.Status;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostalDeliveryRepository postalDeliveryRepository;

    private final ConversionService conversionService;

    private final PostOfficeRepository postOfficeRepository;

    private final HistoryRepository historyRepository;

    public void deliveryRegistration(DeliveryRegistrationRq rq) {
        PostalDelivery postalDelivery = conversionService.convert(rq, PostalDelivery.class);

        postalDeliveryRepository.save(postalDelivery);
    }

    @Override
    public void arrive(ArriveRq arriveRq) {
        PostalDelivery postalDelivery = postalDeliveryRepository.findById(arriveRq.postalDeliveryId())
                .orElseThrow(() -> new NotFoundException("Отправления с таким id не найдено"));
        PostOffice postOffice = postOfficeRepository.findById(arriveRq.postOfficeId())
                .orElseThrow(() -> new NotFoundException("Почтового отделения с таким id не найдено"));
        History history = History.builder()
                .postalDelivery(postalDelivery)
                .postOffice(postOffice)
                .status(Status.ARRIVED)
                .localDateTime(LocalDateTime.now())
                .build();
        historyRepository.save(history);

    }

    @Override
    public void gone(long id) {
        History history = historyRepository.findByPostalDeliveryIdAndStatus(id,Status.ARRIVED)
                .orElseThrow(() -> new NotFoundException("В истории нет такого прибывшего отправления"));
        History goneHistory = History.builder()
                .postalDelivery(history.getPostalDelivery())
                .postOffice(history.getPostOffice())
                .status(Status.GONE)
                .localDateTime(LocalDateTime.now())
                .build();
        historyRepository.save(goneHistory);

    }

    @Override
    public void receive(long id) {
        History history = historyRepository.findByPostalDeliveryIdAndStatus(id,Status.GONE)
                .orElseThrow(() -> new NotFoundException("В истории нет такого отправления"));
        History goneHistory = History.builder()
                .postalDelivery(history.getPostalDelivery())
                .postOffice(history.getPostOffice())
                .status(Status.RECEIVED)
                .localDateTime(LocalDateTime.now())
                .build();
        historyRepository.save(goneHistory);
    }

}
