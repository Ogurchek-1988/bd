//package ru.portal.semusadba.jsf.beans;
//
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import ru.portal.semusadba.config.application.ResourceBundleMap;
//import ru.portal.semusadba.model.staff.PromoContent;
//import ru.portal.semusadba.model.staff.repository.PromoContentRepository;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//@Getter
//@Setter
//@Component
//@Slf4j
//@ViewScoped
//public class PromoContentBean extends RestBean implements Serializable {
//
//    @Autowired
//    private PromoContentRepository promoContentRepository;
//
//    @Autowired
//    @Qualifier("i18n")
//    private ResourceBundleMap i18n;
//
//    private List<PromoContent> promoContents = new ArrayList<>();
//
//    private List<PromoContent> actualPromoContents = new ArrayList<>();
//    private PromoContent selectedPromoContent;
//
//    @PostConstruct
//    public void init() {
//        Long contentId;
//        if ((contentId = getLongRequestParam("contentId")) != null) {
//            var content = promoContentRepository.findWithEmployerWithoutAgency(contentId);
//            if (content != null) {
//                selectedPromoContent(content);
//            }else {
//                reset();
//            }
//        } else {
//            reset();
//        }
//        //currentLanguages = fromLanguages(userRepository.getLanguages(currentUser));
//    }
//
//    public void selectedPromoContent(PromoContent promoContent) {
//        selectedPromoContent = promoContent;
//    }
//
//    public void reset() {
//        loadActualVacancies();
//        promoContents = actualPromoContents;
//        resetVacancy();
//    }
//
//    public void resetVacancy() {
//        selectedPromoContent = null;
//    }
//    private void loadActualVacancies() {
//        actualPromoContents.clear();
//        actualPromoContents.addAll(promoContentRepository.findAllByEmployerWithoutAgency(Boolean.TRUE));
//    }
//}
