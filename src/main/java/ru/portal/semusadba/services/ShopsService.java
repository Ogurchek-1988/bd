package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.Shops;
import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface ShopsService {

    void save(Shops shops);
    void  delete(Long id);
    List<Shops> getAll();
}
