package view.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDTO {
    private StringProperty username;
    private IntegerProperty id;
    private StringProperty stringRoles;

    public String getStringRoles() {
        return stringRolesProperty().get();
    }

    public StringProperty stringRolesProperty() {
        if(stringRoles==null)
        {
            stringRoles= new SimpleStringProperty(this,"stringRoles");
        }
        return stringRoles;
    }

    public void setStringRoles(String stringRoles) {
        this.stringRolesProperty().set(stringRoles);
    }

    public IntegerProperty idProperty(){
        if(id==null)
        {
            id= new SimpleIntegerProperty(this,"id");
        }
        return id;
    }
    public StringProperty usernameProperty(){
        if(username==null)
        {
            username= new SimpleStringProperty(this,"username");
        }
        return username;
    }
    public void setId(int id)
    {
        idProperty().set(id);
    }
    public void setUsername(String username)
    {
        usernameProperty().set(username);
    }

    public int getId()
    {
        return idProperty().get();
    }
    public String getUsername()
    {
        return usernameProperty().get();
    }

}
