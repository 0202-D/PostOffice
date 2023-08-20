package ru.skyeng.postoffice.dto;

import lombok.Builder;

@Builder
public record ArriveRq(long postalDeliveryId,int postOfficeId) {
}
