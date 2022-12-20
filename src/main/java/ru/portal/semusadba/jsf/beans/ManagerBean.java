package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.Coming;
import ru.portal.semusadba.model.entity.Despatch;
import ru.portal.semusadba.model.entity.Returns;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.services.ComingServiceImpl;
import ru.portal.semusadba.services.DespatchServiceImpl;
import ru.portal.semusadba.services.ReturnsServiceImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@Setter
@Component
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true, havingValue = "false")
public class ManagerBean extends RestBean implements Serializable {

    private ComingServiceImpl comingService;
    private DespatchServiceImpl despatchService;
    private ReturnsServiceImpl returnsService;
    public Long productId;
    public int count;
    public Date date;
    public Long shopId;
    public Long workerId;
    public int price;
    public Long supplierId;
    public String productName;
    public String description;

    public ManagerBean(ComingServiceImpl comingService,
                       DespatchServiceImpl despatchService,
                       ReturnsServiceImpl returnsService){
        this.comingService = comingService;
        this.despatchService = despatchService;
        this.returnsService = returnsService;
    }

    public List<Coming> getAllComings(){
        return comingService.getAll();
    }

    public List<Despatch> getAllDespatchs(){
        return despatchService.getAll();
    }

    public List<Returns> getAllReturns(){
        return returnsService.getAll();
    }

    public void deleteComing(Long id){
        comingService.delete(id);
    }

    public void deleteDespatch(Long id){
        despatchService.delete(id);
    }

    public void deleteReturns(Long id){
        returnsService.delete(id);
    }

    public void saveComing(Long productId, String productName, int count, Date date, Long supplierId, Long workerId, int price) {
        Coming newComing = new Coming(productId, productName, count, date, supplierId, workerId, price);
        comingService.save(newComing);
    }

    public void saveDespatch(Long productId, int count, Date date, Long shopId, Long workerId, int price) {
        Despatch newDespatch = new Despatch(productId, count, date, shopId, workerId, price);
        despatchService.save(newDespatch);
    }

    public void saveReturn(Long productId, int count, Date date, Long shopId, String description, Long workerId) {
        Returns newReturns = new Returns(productId, count, date, shopId, description, workerId);
        returnsService.save(newReturns);
    }
}
