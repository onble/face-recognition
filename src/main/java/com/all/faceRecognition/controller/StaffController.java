package com.all.faceRecognition.controller;

import com.all.faceRecognition.bean.Staff;
import com.all.faceRecognition.common.R;
import com.all.faceRecognition.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 职员的控制器
 */
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 职员登录控制
     *
     * @param account 账号
     * @param password 密码
     * @return R
     */
    @PostMapping("/login_staff")
    public R loginStaff(String account, String password) {
        try {
            Staff staff = staffService.loginStaff(account, password);
            return R.ok().setData("staff", staff);
        } catch (Exception e) {
            return R.error().setData("msg", e.getMessage());
        }
    }

    /**
     * 职员账号注册
     *
     * @param account 账号
     * @param password 密码
     * @param repassword 重复密码
     * @return R
     */
    @PostMapping("/register_staff")
    public R registerNewStaff(String account, String password, String repassword) {
        try {
            return staffService.register(account, password, repassword);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据页码获取获取通讯录
     * @param page 页码
     * @param items 数量
     * @return R
     */
    @PostMapping("/phone_list/getList")
    public R getPoneList(int page, int items) {
        try {
            return staffService.getAddressListByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据页码和名字模糊查询获取获取通讯录
     * @param page 页码
     * @param items 数量
     * @param name 名字
     * @return R
     */
    @PostMapping("/phone_list/getListWithName")
    public R getPoneListWithName(int page, int items,String name) {
        try {
            return staffService.getAddressListByPageByName(page, items,name);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据页码获取在线信息
     * @param page 页码
     * @param items 数量
     * @return R
     */
    @PostMapping("/online/getList")
    public R getOnlineInfByPage(int page, int items){
        try {
            return staffService.getOnlineResultByPage(page, items);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据页码和模糊查询名字获取在线信息
     * @param page 页码
     * @param items 数量
     * @param name 名字
     * @return R
     */
    @PostMapping("/online/getListWithName")
    public R getOnlineInfByPageWithName(int page, int items, String name){
        try {
            return staffService.getOnlineResultByPageByName(page, items,name);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
