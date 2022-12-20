//package ru.portal.semusadba.model.staff.repository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import ru.portal.semusadba.model.staff.PromoContent;
//
//import java.util.List;
//@Repository
//public interface PromoContentRepository extends CrudRepository<PromoContent, Long> {
//    @Query("SELECT pr FROM PromoContent pr " +
//            "WHERE pr.id = :id"
//    )
//    PromoContent findWithEmployerWithoutAgency(@Param("id") Long id);
//    @Query("select pr FROM PromoContent pr order by pr.id desc")
//    List<PromoContent> findAllByEmployerWithoutAgency(@Param("actual") Boolean actual);
//
//
//}
