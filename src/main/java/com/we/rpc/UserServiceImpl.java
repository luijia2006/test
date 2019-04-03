package com.we.rpc;

public class UserServiceImpl implements UserService {
    public User selectUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Jack Tang");
        user.setAge(20);
        return user;
    }
}