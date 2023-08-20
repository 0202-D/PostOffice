package ru.skyeng.postoffice.dto;

import lombok.Builder;
import ru.skyeng.postoffice.model.Status;
@Builder
public record StatusRs(long id, Status status) {
}
