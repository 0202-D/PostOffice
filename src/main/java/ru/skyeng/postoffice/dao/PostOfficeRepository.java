package ru.skyeng.postoffice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skyeng.postoffice.model.PostOffice;

public interface PostOfficeRepository extends JpaRepository<PostOffice,Integer> {
}
