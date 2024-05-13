package com.all.faceRecognition.service;

import com.all.faceRecognition.bean.Staff;
import com.all.faceRecognition.bean.StaffInf;
import com.all.faceRecognition.common.R;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * 职员的业务层
 */
@Service
public interface StaffService {
    // 职员登录
    Staff loginStaff(String account, String password) throws Exception;

    // 职员注册
    R register(String account, String password, String repassword) throws Exception;

    // 根据职员id获取职员信息
    // 按页数获取职员信息
    PageInfo<StaffInf> getStaffInfListByPage(int page, int items) throws Exception;

    // 按页数和名字获取职员信息
    PageInfo<StaffInf> getStaffInfListByPageByName(int page, int items, String name) throws Exception;

    // 获取通讯录信息
    R getAddressListByPage(int page, int items) throws Exception;

    // 根据名字获取通讯录信息
    R getAddressListByPageByName(int page, int items, String name) throws Exception;

    // 按页数获取在线信息
    R getOnlineResultByPage(int page, int items) throws Exception;

    // 根据名字获取在线信息
    R getOnlineResultByPageByName(int page, int items, String name) throws Exception;
}
