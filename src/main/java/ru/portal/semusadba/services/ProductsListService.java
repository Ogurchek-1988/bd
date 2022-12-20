package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.ProductsList;
import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface ProductsListService {

    void save(ProductsList productsList);
    void  delete(Long id);
    List<ProductsList> getAll();
}
