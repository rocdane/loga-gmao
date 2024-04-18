package tech.loga.user;

import java.util.List;

public interface UserManagement {
    String registerUser(UserRegisterRequest registerRequest);
    String getUserByName(String username);
    List<UserDTO> getAllUser();
    void editUser(UserUpdateRequest updateRequest, Long id);
    void deleteUser(Long id);
}
