package ru.portal.semusadba.jsf.beans;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.*;
import ru.portal.semusadba.services.ComingServiceImpl;
import ru.portal.semusadba.services.DespatchServiceImpl;
import ru.portal.semusadba.services.ReturnsServiceImpl;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ProductsBean productsBean;
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
                       ReturnsServiceImpl returnsService,
                       ProductsBean productsBean){
        this.comingService = comingService;
        this.despatchService = despatchService;
        this.returnsService = returnsService;
        this.productsBean = productsBean;
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

    public void saveComing(Long productId, String productName, Long groupId, int count, Date date, Long supplierId, Long workerId, int price) {
        ProductsList productsList = new ProductsList(productName, groupId, price, count);
        productsBean.saveProductV2(productsList);
        Coming newComing = new Coming(productsList.getId(), productName, count, date, supplierId, workerId, price);
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

    public List<LocalComing> getAllLocalComing(List<Coming> comings, List<Suppliers> suppliers, List<Workers> workers){
        List<LocalComing> result = new ArrayList<>();
        String supplierName = null;
        String workerName = null;
        LocalComing localComing;
        for (Coming coming: comings){
            for (Suppliers supplier: suppliers){
                if (coming.getSupplierId() == supplier.getId()){
                    supplierName = supplier.getName();
                    break;
                }
            }
            for (Workers worker: workers){
                if (coming.getWorkerId() == worker.getId()){
                    workerName = worker.getFirstname() + worker.getLastname();
                    break;
                }
            }
            localComing = new LocalComing(coming.getProductName(), coming.getCount(), coming.getDate(), supplierName, workerName, coming.getPrice());
            result.add(localComing);
        }
        return result;
    }

    public List<LocalDespatch> getAllLocalDespatch(List<Despatch> despatches, List<ProductsList> productsLists, List<Shops> shops, List<Workers> workers){
        List<LocalDespatch> result = new ArrayList<>();
        String productName = null;
        String shopName = null;
        String workerName = null;
        LocalDespatch localDespatch;
        for (Despatch despatch: despatches){
            for (ProductsList productsList: productsLists){
                if (despatch.getProductId() == productsList.getId()){
                    productName = productsList.getName();
                    break;
                }
            }
            for (Shops shop: shops){
                if (despatch.getShopId() == shop.getId()){
                    shopName = shop.getName();
                    break;
                }
            }
            for (Workers worker: workers){
                if (despatch.getWorkerId() == worker.getId()){
                    workerName = worker.getFirstname() + worker.getLastname();
                    break;
                }
            }
            localDespatch = new LocalDespatch(productName, despatch.getCount(), despatch.getDate(), shopName, workerName, despatch.getPrice());
            result.add(localDespatch);
        }
        return result;
    }

    public List<LocalReturn> getAllLocalReturn(List<Returns> returns, List<ProductsList> productsLists, List<Shops> shops, List<Workers> workers){
        List<LocalReturn> result = new ArrayList<>();
        String productName = null;
        String shopName = null;
        String workerName = null;
        LocalReturn localReturn;
        for (Returns retun: returns){
            for (ProductsList productsList: productsLists){
                if (retun.getProductId() == productsList.getId()){
                    productName = productsList.getName();
                    break;
                }
            }
            for (Shops shop: shops){
                if (retun.getShopId() == shop.getId()){
                    shopName = shop.getName();
                    break;
                }
            }
            for (Workers worker: workers){
                if (retun.getWorkerId() == worker.getId()){
                    workerName = worker.getFirstname() + worker.getLastname();
                    break;
                }
            }
            localReturn = new LocalReturn(productName, retun.getCount(), retun.getDate(), shopName, retun.getDescription(), workerName);
            result.add(localReturn);
        }
        return result;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LocalComing {
        private String productName;
        private int count;
        private Date date;
        private String supplierName;
        private String workerName;
        private int price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LocalDespatch {
        private String productName;
        private int count;
        private Date date;
        private String shopName;
        private String workerName;
        private int price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LocalReturn {
        private String productName;
        private int count;
        private Date date;
        private String shopName;
        private String description;
        private String workerName;
    }
}
