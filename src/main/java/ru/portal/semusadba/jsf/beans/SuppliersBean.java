package ru.portal.semusadba.jsf.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.services.SuppliersService;
import ru.portal.semusadba.services.SuppliersServiceImpl;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
@Setter
@Component
@AllArgsConstructor
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true, havingValue = "false")
public class SuppliersBean extends RestBean implements Serializable {

    private SuppliersServiceImpl suppliersService;

    public List<Suppliers> getAll(){
        return suppliersService.getAll();
    }
    public void delete(Long id){
        suppliersService.delete(id);
    }
    
}
