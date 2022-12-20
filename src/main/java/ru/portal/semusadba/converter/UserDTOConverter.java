package ru.portal.semusadba.converter;

import ru.portal.semusadba.model.entity.Role;
import ru.portal.semusadba.model.entity.User;
import ru.portal.semusadba.network.UserDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTOConverter {
    public static User dtoToEntity(UserDTO userDto) {
        Set<Role> roleSet = userDto.getRoleSet().stream().map(Role::new).collect(Collectors.toSet());
        return new User(userDto.getUsername(),
                userDto.getPassword(),
                roleSet);
    }

    public static UserDTO entityToDto(User userEntity) {
        return new UserDTO(userEntity.getUid(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoleSet().stream().map(Role::getRoleName).collect(Collectors.toSet()));
    }
}