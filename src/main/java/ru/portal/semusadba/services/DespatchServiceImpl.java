package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.Despatch;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.DespatchRepository;

import java.util.List;

@Service
public class DespatchServiceImpl implements DespatchService{
    @Autowired
    private DespatchRepository despatchRepository;

    @Override
    public void save(Despatch despatch){
        despatchRepository.save(despatch);
    }

    @Override
    public void delete(Long id) {

        despatchRepository.deleteById(id);
    }

    @Override
    public List<Despatch> getAll() {
        return despatchRepository.findAll();
    }
}
