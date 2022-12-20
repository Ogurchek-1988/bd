package ru.portal.semusadba.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.portal.semusadba.model.entity.ProductsGroups;

public interface ProductsGroupsRepository extends JpaRepository<ProductsGroups, Long> {
}
