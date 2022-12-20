package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.ProductsGroups;
import ru.portal.semusadba.model.entity.ProductsList;
import ru.portal.semusadba.model.entity.Suppliers;
import ru.portal.semusadba.services.ProductsGroupsServiceImpl;
import ru.portal.semusadba.services.ProductsListServiceImpl;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
@Setter
@Component
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true, havingValue = "false")
public class ProductsBean extends RestBean implements Serializable {

    private ProductsListServiceImpl productsListService;
    private ProductsGroupsServiceImpl productsGroupsService;
    public String groupName;
    public String description;
    public String productName;
    public Integer price;
    public Integer count;

    public ProductsBean(ProductsListServiceImpl productsListService,
                        ProductsGroupsServiceImpl productsGroupsService){
        this.productsListService = productsListService;
        this.productsGroupsService = productsGroupsService;
    }

    public List<ProductsList> getAllProduct(){
        return productsListService.getAll();
    }

    public List<ProductsGroups> getAllProductsGroups(){
        return productsGroupsService.getAll();
    }

    public void deleteProduct(Long id){
        productsListService.delete(id);
    }

    public void deleteProductGroup(Long id){
        productsGroupsService.delete(id);
    }

    public void saveProduct(String name, Long groupId, int price, int count) {
        ProductsList newProduct = new ProductsList(name, groupId, price, count);
        productsListService.save(newProduct);
    }

    public void saveProductGroup(String name, String description) {
        ProductsGroups newGroup = new ProductsGroups(name, description);
        productsGroupsService.save(newGroup);
    }
}
