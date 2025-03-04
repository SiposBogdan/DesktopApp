package mapper;

import model.User;
import model.builder.UserBuilder;
import view.model.UserDTO;
import view.model.builder.UserDTOBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO convertUsertoUserDTO(User user)
    {
        return new UserDTOBuilder().setUsername(user.getUsername()).setStringRoles(user.getStringRoles()).build();
    }
    public static User convertUserDTOtoUser(UserDTO userDTO)
    {
        return new UserBuilder().setUsername(userDTO.getUsername()).setStringRoles(userDTO.getStringRoles()).build();
    }
    public static List<UserDTO> convertUserListToUserDTOLIST(List<User> users)
    {
        if(users==null)
        {
            return new ArrayList<>();
        }
        return users.parallelStream().map(UserMapper::convertUsertoUserDTO).collect(Collectors.toList());
    }

}
