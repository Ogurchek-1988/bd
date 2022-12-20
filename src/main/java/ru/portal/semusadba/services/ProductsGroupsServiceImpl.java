package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.portal.semusadba.model.entity.ProductsGroups;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.ProductsGroupsRepository;

import java.util.List;

public class ProductsGroupsServiceImpl implements ProductsGroupsService{
    @Autowired
    private ProductsGroupsRepository productsGroupsRepository;

    @Override
    public void save(ProductsGroups productsGroups){
        productsGroupsRepository.save(productsGroups);
    }

    @Override
    public void delete(Long id) {

        productsGroupsRepository.deleteById(id);
    }

    @Override
    public List<ProductsGroups> getAll() {
        return productsGroupsRepository.findAll();
    }
}
