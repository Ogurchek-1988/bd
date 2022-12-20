package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.entity.Workers;

import java.util.List;

public interface WorkersService {

    void save(Workers workers);
    Workers addWorker(Workers workers);
    void  delete(Long id);
    List<Workers> getAll();
}
