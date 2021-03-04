package com.fengxin.service;

import com.fengxin.pojo.Users;

public interface UserService {

    /**
     *
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    public Users queryUserForLogin(String username, String password);

    public Users saveUser(Users user);
}
