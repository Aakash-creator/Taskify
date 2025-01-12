package com.tasify.serviceInterface;

import com.tasify.entity.User;

public interface IUserService 
{
    // CRUD operations
    User createUser(User user);
    User updateUserName(Long id, String username);
    String deleteUser(Long id);
    User getUserById(Long id);

    
    // User-specific operations
    User getUserByEmail(String email);
    User getUserByUsername(String username);
//    String changeUserRole(Long userId, User.Role newRole);
//    User.Role getUserRole(Long userId);
}
