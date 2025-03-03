package mapper;

import model.User;
import model.builder.UserBuilder;
import view.model.AdminDTO;
import view.model.builder.AdminDTOBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminMapper {
    public static AdminDTO convertUsertoAdminDTO(User user)
    {
        return new AdminDTOBuilder().setUsername(user.getUsername()).build();
    }
    public static User convertAdminDTOtoUSER(AdminDTO adminDTO)
    {
        return new UserBuilder().setUsername(adminDTO.getUsername()).build();
    }
    public static List<AdminDTO> convertUserListToAdminDTOLIST(List<User> users)
    {
        if(users==null)
        {
            return new ArrayList<>();
        }
        return users.parallelStream().map(AdminMapper::convertUsertoAdminDTO).collect(Collectors.toList());
    }

}
