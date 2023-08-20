package ru.skyeng.postoffice.converter.postaldelivery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.skyeng.postoffice.dto.DeliveryRegistrationRq;
import ru.skyeng.postoffice.model.PostalDelivery;

@Component
@RequiredArgsConstructor
public class DeliveryRegistrationRqToPostalDelivery implements Converter<DeliveryRegistrationRq, PostalDelivery> {

    @Override
    public PostalDelivery convert(DeliveryRegistrationRq source) {
        PostalDelivery postalDelivery = new PostalDelivery();
        BeanUtils.copyProperties(source, postalDelivery);
        return postalDelivery;
    }
}
