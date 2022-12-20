package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.Returns;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.ReturnsRepository;

import java.util.List;

@Service
public class ReturnsServiceImpl implements ReturnsService{
    @Autowired
    private ReturnsRepository returnsRepository;

    @Override
    public void save(Returns returns){
        returnsRepository.save(returns);
    }

    @Override
    public void delete(Long id) {

        returnsRepository.deleteById(id);
    }

    @Override
    public List<Returns> getAll() {
        return returnsRepository.findAll();
    }
}
