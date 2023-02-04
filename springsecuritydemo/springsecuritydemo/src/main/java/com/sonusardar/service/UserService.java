package com.sonusardar.service;


import com.sonusardar.entity.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);

}
