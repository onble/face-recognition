package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.Admin;

/**
 * 管理员的业务层
 */
public interface AdminService {

    Admin login(String username, String password) throws Exception;

}
