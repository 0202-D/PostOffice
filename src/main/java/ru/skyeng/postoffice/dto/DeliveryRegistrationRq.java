package ru.skyeng.postoffice.dto;

import lombok.Builder;
import ru.skyeng.postoffice.model.DepartureType;


@Builder
public record DeliveryRegistrationRq(DepartureType type, String index, String address, String recipientsName) {
}
