package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.Despatch;
import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface DespatchService {

    void save(Despatch despatch);
    void  delete(Long id);
    List<Despatch> getAll();
}
