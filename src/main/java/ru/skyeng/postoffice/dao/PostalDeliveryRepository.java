package ru.skyeng.postoffice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.postoffice.model.PostalDelivery;

@Repository
public interface PostalDeliveryRepository extends JpaRepository<PostalDelivery,Long> {
}
