package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.SuppliersRepository;

import java.util.List;

@Service
public class SuppliersServiceImpl implements SuppliersService {

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Override
    public void save(Suppliers suppliers){
        suppliersRepository.save(suppliers);
    }
    @Override
    public Suppliers addSuppliers(Suppliers suppliers) {

        return suppliersRepository.saveAndFlush(suppliers);
    }

    @Override
    public void delete(Long id) {

        suppliersRepository.deleteById(id);
    }

    @Override
    public Suppliers getByName(String name) {

        return suppliersRepository.findByName(name);
    }

    @Override
    public List<Suppliers> getAll() {
        return suppliersRepository.findAll();
    }
}
