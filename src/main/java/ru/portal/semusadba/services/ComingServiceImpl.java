package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.Coming;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.ComingRepository;
import ru.portal.semusadba.model.repository.SuppliersRepository;

import java.util.List;

@Service
public class ComingServiceImpl implements ComingService{
    @Autowired
    private ComingRepository comingRepository;

    @Override
    public void save(Coming coming){
        comingRepository.save(coming);
    }

    @Override
    public void delete(Long id) {

        comingRepository.deleteById(id);
    }

    @Override
    public List<Coming> getAll() {
        return comingRepository.findAll();
    }
}
