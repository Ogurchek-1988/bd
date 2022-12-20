package ru.portal.semusadba.jsf.beans;

import lombok.Getter;
import ru.portal.semusadba.config.security.UserRole;
import ru.portal.semusadba.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Module {

    ROOT(0, "root", Page.PROMO,
            asList(Page.SHOPS, Page.WORKERS,Page.SUPPLIERS, Page.GOODS),
            /*asList(Page.MESSAGES, Page.PUBLIC_CATALOG, Page.PUBLIC_PLATFORMS),*/
            asList(Page.LOGIN)),
    USER(1, "users", Page.PROMO,
            asList(Page.HISTORY, Page.PROJECTS),
            asList(Page.USER_PROFILE),
            UserRole.USER),
    ADMIN(2, "admin", Page.PROMO,
            asList(Page.ADMIN_PANEL),
            asList(Page.HISTORY),
            UserRole.ADMIN),
    HISTORY(3,"history",Page.PROMO,
            asList(Page.HISTORY,Page.SCHOOL_AND_VILLAGE, Page.GROWING_UP, Page.TECHNOLOGIES),
            asList(Page.LOGIN),
            UserRole.HISTORY),
    PROJECTS(4,"projects",Page.PROMO,
            asList(Page.PROJECTS,Page.PHOTO_GALLERY, Page.PUBLISHING),
            asList(Page.PROMO),
    UserRole.PROJECTS),
    ABOUT(5,"about",Page.ABOUT,asList(Page.ABOUT));



    private final int order;
    @Getter
    private final String moduleName;
    @Getter
    private final Page defaultPage;
    @Getter
    private final List<Page> pages;
    private final List<Page> hiddenPages;
    @Getter
    private final UserRole forRole;

    private Module(int order, String moduleName, Page defaultPage, List<Page> pages, List<Page> hiddenPages) {
        this.order = order;
        this.moduleName = moduleName;
        this.defaultPage = defaultPage;
        this.pages = pages;
        this.hiddenPages = hiddenPages;
        this.forRole = null;
    }

    Module(int order, String moduleName, Page defaultPage,List<Page> hiddenPages) {
        this.order = order;
        this.moduleName = moduleName;
        this.defaultPage = defaultPage;
        this.pages = null;
        this.hiddenPages = hiddenPages;
        this.forRole = null;
    }

    Module(int order, String moduleName, Page defaultPage, List<Page> pages, UserRole forRole) {
        this.order = order;
        this.moduleName = moduleName;
        this.defaultPage = defaultPage;
        this.pages = pages;
        this.hiddenPages = null;
        this.forRole = forRole;
    }


    Module(int order, String moduleName, Page defaultPage, List<Page> pages, List<Page> hiddenPages, UserRole forRole) {
        this.order = order;
        this.moduleName = moduleName;
        this.defaultPage = defaultPage;
        this.pages = pages;
        this.hiddenPages = hiddenPages;
        this.forRole = forRole;
    }



    public int order() {
        return order;
    }

    public String moduleName() {
        return moduleName;
    }

    public Page defaultPage() {
        return defaultPage;
    }

    public List<Page> pages() {
        return pages;
    }

    public List<Page> hiddenPages() {
        return hiddenPages;
    }

    public List<Page> allPages() {
        ArrayList<Page> allPages = new ArrayList<>(pages);
        allPages.addAll(hiddenPages);
        return allPages;
    }

    public static Module moduleForRole(Role role) {
        for (Module value : values()) {
            if (role.equals(value.forRole)) {
                return value;
            }
        }
        return null;
    }

    public static boolean hasModuleForRole(Role role) {
        return moduleForRole(role) != null;
    }
}

