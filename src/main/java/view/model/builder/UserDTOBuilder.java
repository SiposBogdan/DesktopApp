package view.model.builder;

import view.model.UserDTO;

public class UserDTOBuilder {
    private UserDTO userDTO;
    public UserDTOBuilder()
    {
        this.userDTO=new UserDTO();
    }
    public UserDTOBuilder setId(int id)
    {
        userDTO.setId(id);
        return this;
    }
    public UserDTOBuilder setUsername(String username)
    {
        userDTO.setUsername(username);
        return this;
    }
    public UserDTOBuilder setStringRoles(String stringRoles) {
        userDTO.setStringRoles(stringRoles);
        return this;
    }
    public UserDTO build()
    {
        return userDTO;
    }

}
