package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.Workers;
import ru.portal.semusadba.services.WorkersServiceImpl;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
@Setter
@Component
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true, havingValue = "false")
public class WorkersBean extends RestBean implements Serializable {

    private WorkersServiceImpl workersService;
    public String workerFirstname;
    public String workerLastname;

    public WorkersBean(WorkersServiceImpl workersService){
        this.workersService = workersService;
    }

    public List<Workers> getAll(){
        return workersService.getAll();
    }

    public void delete(Long id){
        workersService.delete(id);
    }

    public void save(String firstname, String lastname) {
        Workers newWorkers = new Workers(firstname, lastname);
        workersService.save(newWorkers);
    }
}
