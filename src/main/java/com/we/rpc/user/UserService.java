package com.we.rpc.user;

public interface UserService {

    /**
     * 根据用户id，查询用户
     * @param id:用戶id
     * @return
     */
    User selectUserById(Long id);
}