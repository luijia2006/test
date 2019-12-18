package com.we.rpc.user;

public class UserServiceImpl implements UserService {
    public User selectUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Jack Tang");
        user.setAge(20);
        return user;
    }
}