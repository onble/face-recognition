package com.all.officeSystem.service;

import com.all.officeSystem.bean.Admin;

/**
 * 管理员的业务层
 */
public interface AdminService {

    Admin login(String username, String password) throws Exception;

}
