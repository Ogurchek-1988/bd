package ru.portal.semusadba.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.portal.semusadba.model.entity.Suppliers;

public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {

    @Query("select s from Suppliers s where s.name = :name")
    Suppliers findByName(@Param("name") String name);
}
