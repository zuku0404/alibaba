package mapper;

import model.dao.User;
import model.dto.UserDto;

public class UserMapper {
    public UserDto mapToDto (User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .created(user.getCreated())
                .age(user.getAge())
                .build();
    }
}
