package ru.skyeng.postoffice.service.post;

import ru.skyeng.postoffice.dto.ArriveRq;
import ru.skyeng.postoffice.dto.DeliveryRegistrationRq;

public interface PostService {
    void deliveryRegistration(DeliveryRegistrationRq rq);

    void arrive(ArriveRq arriveRq);

    void gone(long id);

    void receive(long id);
}
