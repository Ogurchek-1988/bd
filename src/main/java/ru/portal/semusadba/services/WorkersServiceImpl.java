package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.entity.Workers;
import ru.portal.semusadba.model.repository.WorkersRepository;

import java.util.List;

@Service
public class WorkersServiceImpl implements WorkersService{
    @Autowired
    private WorkersRepository workersRepository;

    @Override
    public void save(Workers workers){
        workersRepository.save(workers);
    }

    @Override
    public Workers addWorker(Workers workers) {

        return workersRepository.saveAndFlush(workers);
    }

    @Override
    public void delete(Long id) {

        workersRepository.deleteById(id);
    }

    @Override
    public List<Workers> getAll() {
        return workersRepository.findAll();
    }
}
