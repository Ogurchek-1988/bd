package ru.portal.semusadba.services;

import ru.portal.semusadba.model.entity.Coming;
import ru.portal.semusadba.model.entity.Suppliers;

import java.util.List;

public interface ComingService {
    void save(Coming coming);
    void  delete(Long id);
    List<Coming> getAll();
}
