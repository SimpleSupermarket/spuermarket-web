package org.simplesupermarket.web.app.service;

import java.util.List;

public interface UserService {
     List getList(String name);
     Boolean repassword(String oldPassword,String newPassword,Long userId);
}
