package ru.skyeng.postoffice.converter.history;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.skyeng.postoffice.dto.HistoryRs;
import ru.skyeng.postoffice.model.History;

@Component
@RequiredArgsConstructor
public class HistoryToHistoryRs implements Converter<History, HistoryRs> {

    @Override
    public HistoryRs convert(History source) {
        return HistoryRs.builder()
                .postOffice(source.getPostOffice().getId())
                .status(source.getStatus())
                .localDateTime(source.getLocalDateTime()).build();
    }
}
