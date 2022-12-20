package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.ProductsGroups;
import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface ProductsGroupsService {

    void save(ProductsGroups productsGroups);
    void  delete(Long id);
    List<ProductsGroups> getAll();
}
