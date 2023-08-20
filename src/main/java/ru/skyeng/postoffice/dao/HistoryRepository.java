package ru.skyeng.postoffice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.postoffice.model.History;
import ru.skyeng.postoffice.model.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findByPostalDeliveryIdAndStatus(long id, Status arrived);

    List<History> findAllByPostalDeliveryId(long postalDeliveryId);

    Optional<History> findFirstByPostalDeliveryIdOrderByLocalDateTimeDesc(long postalDeliveryId);
}
