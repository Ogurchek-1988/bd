package ru.portal.semusadba.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.entity.Workers;

public interface WorkersRepository extends JpaRepository<Workers, Long> {
}
