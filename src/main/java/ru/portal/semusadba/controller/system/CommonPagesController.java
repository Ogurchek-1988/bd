package ru.portal.semusadba.controller.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true,
        havingValue = "false")
public class CommonPagesController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonPagesController.class);

    public static final String PROMO_PAGE = "public/promo.xhtml";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toPromoPage(HttpServletRequest request,
                                HttpServletResponse httpServletResponse) {
        return "redirect:" + request.getRequestURL().append(PROMO_PAGE).toString();
    }
}