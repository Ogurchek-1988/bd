package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface SuppliersService {

    void save(Suppliers suppliers);
    Suppliers addSuppliers(Suppliers suppliers);
    void  delete(Long id);
    Suppliers getByName(String name);
    List<Suppliers> getAll();
}
