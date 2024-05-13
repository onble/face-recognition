package com.all.faceRecognition.util;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class QiniuUtils {

    // 设置好账号的ACCESS_KEY和SECRET_KEY
    public static final String ACCESS_KEY = "Re0n3W2dmszvtJYGh-ogq_-ZJ4VJQKdLjjd0kara";
    public static final String  SECRET_KEY = "BMzD0URUh4POYjXc-7vHWlwDEE_grRWAoxw_rGwL";

    // 要上传的空间
    public static final String  bucketname = "officesystem";

    // 测试域名，只有30天有效期
    public static final String  QINIU_IMAGE_DOMAIN = "rn4i9ueez.hb-bkt.clouddn.com/";

    // 不同的七云牛存储区域调用不同的zone
    public static Zone zone = Zone.autoZone(); // 这里不调会报错，我看着选了个auto就正常工作了，不管他

    public static String upload(MultipartFile file) throws IOException {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            //
            Configuration cfg = new Configuration(zone);

            // 密钥配置
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

            // ...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);

            // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
            Response res = uploadManager.put(file.getBytes(), fileName, auth.uploadToken(bucketname));
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        return null;

    }

}
