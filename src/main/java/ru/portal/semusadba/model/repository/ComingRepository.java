package ru.portal.semusadba.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.portal.semusadba.model.entity.Coming;

public interface ComingRepository extends JpaRepository<Coming, Long> {
}
