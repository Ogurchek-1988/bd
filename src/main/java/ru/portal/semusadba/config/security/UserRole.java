package ru.portal.semusadba.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN("role.admin", 2),
    USER("role.user", 1),
    ROOT("role.root", 0),
    HISTORY("role.history", 3),
    PROJECTS("role.projects", 4);

    @Getter
    private final String localizeMenuKey;
    @Getter
    private final int order;


    UserRole(String localizeMenuKey, int order) {
        this.localizeMenuKey = localizeMenuKey;
        this.order = order;
    }

    @Override
    public String getAuthority() {
        return name();
    }


}