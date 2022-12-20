package ru.portal.semusadba.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.portal.semusadba.model.entity.ProductsList;

public interface ProductsListRepository extends JpaRepository<ProductsList, Long> {
}
