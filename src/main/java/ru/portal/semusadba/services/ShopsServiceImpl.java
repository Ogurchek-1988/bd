package ru.portal.semusadba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.portal.semusadba.model.entity.Shops;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.model.repository.ShopsRepository;

import java.util.List;

@Service
public class ShopsServiceImpl implements ShopsService{
    @Autowired
    private ShopsRepository shopsRepository;

    @Override
    public void save(Shops shops){
        shopsRepository.save(shops);
    }

    @Override
    public void delete(Long id) {

        shopsRepository.deleteById(id);
    }

    @Override
    public List<Shops> getAll() {
        return shopsRepository.findAll();
    }
}
