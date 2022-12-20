package ru.portal.semusadba.jsf.beans;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.portal.semusadba.config.application.ResourceBundleMap;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.portal.semusadba.model.entity.Role;
import ru.portal.semusadba.model.entity.User;
import ru.portal.semusadba.model.UserRepository;

@Getter
@Setter
@EqualsAndHashCode(of = {"login", "password", "error", "timezone", "profile", "users", "asUser"},
        doNotUseGetters = true, callSuper = false)
@Slf4j
@Component
@Scope(scopeName = "session")
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true,
        havingValue = "false")
public class AuthBean extends RestBean implements Serializable {

    private String login;
    private String password;
    private boolean error;
    private String timezone;

    private User user;

    private User asUser;

    private Role selectedRole;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("i18n")
    private ResourceBundleMap i18nMap;



    /**
     * @throws IOException
     */
    public void performLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/dologin");
    }

    /**
     * @throws IOException
     */
    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
    }
    public User getUser() {
        return this.isAsUserMode() ? this.asUser : this.user;
    }

    public boolean isAsUserMode() {
        return asUser != null;
    }


    public String getTimezone() {
        return timezone;
    }


    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


    public boolean isLoggedIn() {
        return getUser() != null;
    }
}
