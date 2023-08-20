package ru.skyeng.postoffice.dto;

import lombok.Builder;
import ru.skyeng.postoffice.model.Status;

import java.time.LocalDateTime;
@Builder
public record HistoryRs(int postOffice, Status status, LocalDateTime localDateTime) {
}
