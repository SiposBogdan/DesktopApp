package repository.admin;

import model.Role;
import model.User;
import model.validation.Notification;

import java.util.List;

public interface AdminRepository {
    boolean save(User user);
    boolean delete(User user);
    boolean generateReport();
    List<User> findAll();
    User findByUsername(String username);
    Notification<Role> findRoleId(String role);
    boolean saveRole(Long userId, Long roleId);
}
