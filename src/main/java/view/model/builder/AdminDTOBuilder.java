package view.model.builder;

import view.model.AdminDTO;

public class AdminDTOBuilder {
    private AdminDTO adminDTO;
    public AdminDTOBuilder()
    {
        this.adminDTO=new AdminDTO();
    }
    public AdminDTOBuilder setId(int id)
    {
        adminDTO.setId(id);
        return this;
    }
    public AdminDTOBuilder setUsername(String username)
    {
        adminDTO.setUsername(username);
        return this;
    }
    public AdminDTO build()
    {
        return adminDTO;
    }

}
