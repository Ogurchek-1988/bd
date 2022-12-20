package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.ProductsList;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.ProductsListRepository;

import java.util.List;

@Service
public class ProductsListServiceImpl implements ProductsListService{
    @Autowired
    private ProductsListRepository productsListRepository;

    @Override
    public void save(ProductsList productsList){
        productsListRepository.save(productsList);
    }

    @Override
    public void delete(Long id) {

        productsListRepository.deleteById(id);
    }

    @Override
    public List<ProductsList> getAll() {
        return productsListRepository.findAll();
    }
}
