package ru.portal.semusadba.jsf.beans;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import ru.portal.semusadba.model.entity.Role;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
public enum Page {
    PROMO("promo", "/public/promo.xhtml"),
    SHOPS("shops","/public/shops.xhtml"),
    WORKERS("workers","/public/workers.xhtml"),
    SUPPLIERS("suppliers","/public/suppliers.xhtml"),
    GOODS("goods","/public/goods.xhtml"),
    LOGIN("login", "/public/login.xhtml"),
    USER_PROFILE("user-profile", "/users/user_profile.xhtml"),
    ADMIN_PANEL("admin-panel","/admin/admin_panel.xhtml"),
    HISTORY("history","/public/history.xhtml"),
    PROJECTS("projects","/public/projects.xhtml"),
    ABOUT("about","/public/about.xhtml"),
    SCHOOL_AND_VILLAGE("school-village","/public/school-village.xhtml"),
    TECHNOLOGIES("technologies","/public/technologies.xhtml"),
    GROWING_UP("growing","/public/growing-up.xhtml"),
    PHOTO_GALLERY("photo","/public/photo.xhtml"),
    PUBLISHING("publishing","/public/publishing.xhtml"),
    DONATE("donate","/public/donate.xhtml")
;



    @Getter
    private final String pageName;
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private String url;
    @Getter
    private Role profilePageForRole;

    private Page(String pageName, String url) {
        this.pageName = pageName;
        this.url = url;
    }

    private Page(String pageName, String url, Role profilePage) {
        this(pageName, url);
        this.profilePageForRole = profilePage;
    }


    public String pageName() {
        return pageName;
    }

    public String url() {
        return url + "?faces-redirect=true";
    }

    public String urlWithNoRedirect() {
        return url;
    }

    public String urlWithParameters() {
        return url + "&faces-redirect=true";
    }

    @SafeVarargs
    public final String url(Pair<? extends CharSequence, ?>... params) {
        return url(Arrays.asList(params));
    }

    public final String url(List<Pair<? extends CharSequence, ?>> params) {
        StringBuilder buffer = new StringBuilder(url());
        for (Pair<? extends CharSequence, ?> param : params) {
            try {
                buffer.append("&")
                        .append(param.getFirst())
                        .append("=")
                        .append(URLEncoder.encode(param.getSecond().toString(), StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException exc) {
                log.error("Something went wrong - UTF-8 is unsupported by URLEncoder");
                throw new RuntimeException(exc);
            }
        }
        return buffer.toString();
    }


}
