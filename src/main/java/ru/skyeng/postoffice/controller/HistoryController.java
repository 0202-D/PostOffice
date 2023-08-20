package ru.skyeng.postoffice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skyeng.postoffice.dto.HistoryRs;
import ru.skyeng.postoffice.dto.StatusRs;
import ru.skyeng.postoffice.service.history.HistoryService;

import java.util.List;

@RestController
@Tag(name = "История отправления")
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/{id}")
    public List<HistoryRs> getAll(@PathVariable("id") long postalDeliveryId) {
        return historyService.getAll(postalDeliveryId);
    }

    @GetMapping("/status/{id}")
    public StatusRs getStatusById(@PathVariable("id") long postalDeliveryId) {
        return historyService.getStatusById(postalDeliveryId);
    }
}
