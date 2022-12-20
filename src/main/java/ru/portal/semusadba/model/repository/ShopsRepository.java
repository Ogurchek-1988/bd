package ru.portal.semusadba.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.portal.semusadba.model.entity.Shops;

public interface ShopsRepository extends JpaRepository<Shops, Long> {
}
