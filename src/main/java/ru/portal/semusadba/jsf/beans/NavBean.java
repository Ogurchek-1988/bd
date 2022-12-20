package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.config.application.ResourceBundleMap;

import ru.portal.semusadba.config.security.UserRole;
import ru.portal.semusadba.model.entity.Role;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Getter
@Setter
@Slf4j
@Component
@Scope(scopeName = "session")
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true,
        havingValue = "false")
public class NavBean extends RestBean implements Serializable {
    @Autowired
    private AuthBean authBean;
    @Autowired
    @Qualifier("i18n")
    private ResourceBundleMap i18nMap;

    private String quickSearch;

    private final String NAV_PARAM = "nav";

    private Role role;


    @PostConstruct
    public void init() {
    }



    public MenuModel getDefaultModel() {
        MenuModel model = new DefaultMenuModel();
        int i = 0;
        for (Page page : Module.ROOT.pages()) {
            model.getElements().add(createMenuItem(page, String.valueOf(i), Module.ROOT));
        }
        return model;
    }

    public MenuModel getAllModel() {
        MenuModel model = new DefaultMenuModel();

        Module module = getModuleForRole(UserRole.HISTORY);
        int j = 0;
        for (Page page : module.pages()) {
            model.getElements().add(createMenuItem(page, String.valueOf(j), module));
            ++j;
        }
        Module module2 = getModuleForRole(UserRole.PROJECTS);
        int x = 0;
        for (Page page : module2.pages()) {
            model.getElements().add(createMenuItem(page, String.valueOf(x), module2));
            ++x;
        }
        int i = 0;
        for (Page page : Module.ABOUT.hiddenPages()) {
            model.getElements().add(createMenuItem(page, String.valueOf(i), Module.ABOUT));
        }

        return model;
    }

    public MenuModel getCurrentRoleModel() {
        MenuModel model = new DefaultMenuModel();
//        if (authBean.getUser() == null) {
//            return model;
//        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null && facesContext.getExternalContext() != null) {
            String uri = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI();
//            Module module = NavBean.getModuleForUrl(uri);
//            if (module != null && module.getForRole() != null) {
//                switchRole(module.getForRole());
//            }
        }

        if ((getCurrentPage().getPageName()).equals("history") || (getCurrentPage().getPageName()).equals("school-village") ||
                (getCurrentPage().getPageName()).equals("technologies") || (getCurrentPage().getPageName()).equals("growing") ) {
            Module module = getModuleForRole(UserRole.HISTORY);
            int i = 0;
            for (Page page : module.pages()) {
                model.getElements().add(createMenuItem(page, String.valueOf(i), module));
                ++i;
            }
        } else if ((getCurrentPage().getPageName()).equals("projects") || (getCurrentPage().getPageName()).equals("photo") ||
                (getCurrentPage().getPageName()).equals("publishing")){
            Module module = getModuleForRole(UserRole.PROJECTS);
            int i = 0;
            for (Page page : module.pages()) {
                model.getElements().add(createMenuItem(page, String.valueOf(i), module));
                ++i;
            }
        }
        return model;
    }

    public Module getModuleForRole(UserRole role) {
        return Arrays.stream(Module.values())
                .filter(module -> role.equals(module.getForRole()))
                .filter(module -> !module.getPages().isEmpty())
                .findFirst().orElseGet(null);

    }

    private DefaultMenuItem createMenuItem(Page page, String itemId, Module currentModule) {
        DefaultMenuItem item = new DefaultMenuItem(i18nMap.get(getMessageId(page, currentModule)));
        item.setId(itemId);

        if (page.getPageName().equals(getCurrentPage().getPageName())) {
            item.setStyleClass("selected " + page.getPageName());
        } else {
            item.setStyleClass(page.getPageName());
        }

        item.setEscape(false);
        item.setUrl(page.urlWithNoRedirect());
        item.setAjax(false);
        return item;
    }

    public Page getCurrentPage() {
        String requestURI = getRequestURI();
        for (Page page : Page.values()) {
            if (requestURI.equals(page.getUrl())) {
                if (page.equals(Page.HISTORY)) {
                    return Page.HISTORY;
                }
                return page;
            }
        }
        return Page.PROMO;
    }

    public String getMessageId(Page page, Module currentModule) {
        return "navigation.".concat(currentModule.moduleName()).concat(".").concat(page.pageName());
    }


    public String goToLogin() {
        return goToPage(Page.LOGIN);
    }
    public String goToDonate() {
        return goToPage(Page.DONATE);
    }

    public static Module getModuleForUrl(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            log.error("Can't get module for URL.", e);
            return null;
        }
        String path = uri.getPath();
        for (Module module : Module.values()) {
            for (Page page : module.getPages()) {
                if (page.getUrl().equals(path)) {
                    log.debug("Module for path {} is {}", path, module);
                    return module;
                }
            }
        }
        log.debug("No module for path {} were found", path);
        return null;
    }

    public String goToPage(Page page) {
        return page.url();
    }
}
