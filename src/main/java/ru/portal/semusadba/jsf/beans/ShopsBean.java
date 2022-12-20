package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.Shops;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.services.ShopsServiceImpl;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
@Setter
@Component
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true, havingValue = "false")
public class ShopsBean extends RestBean implements Serializable {

    private ShopsServiceImpl shopsService;
    public String shopName;

    public ShopsBean(ShopsServiceImpl shopsService){
        this.shopsService = shopsService;
    }

    public List<Shops> getAll(){
        return shopsService.getAll();
    }

    public void delete(Long id){
        shopsService.delete(id);
    }

    public void save(String name) {
        Shops newShops = new Shops(name);
        shopsService.save(newShops);
    }
}
