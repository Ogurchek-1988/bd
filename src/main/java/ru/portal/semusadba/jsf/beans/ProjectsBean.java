package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.portal.semusadba.model.projects.Projects;
import ru.portal.semusadba.model.projects.ProjectsRepository;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;

@Getter
@Setter
@ManagedBean
public class ProjectsBean {

    @Autowired
    private ProjectsRepository projectsRepository;

    private Projects selectedProject;


    public List <Projects> getProjects(){
        return projectsRepository.findAll();
    }

    public void save() {
        projectsRepository.save(selectedProject);

    }
    public void createProject() {
        selectedProject = new Projects();
    }
}
