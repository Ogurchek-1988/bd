package ru.portal.semusadba.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.portal.semusadba.model.entity.RefreshToken;
import ru.portal.semusadba.model.entity.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    @Transactional
    int deleteByUserEntity(User userEntity);

    RefreshToken findByRefreshToken(String refreshToken);
}