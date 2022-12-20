package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.Returns;
import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface ReturnsService {

    void save(Returns returns);
    void  delete(Long id);
    List<Returns> getAll();
}
