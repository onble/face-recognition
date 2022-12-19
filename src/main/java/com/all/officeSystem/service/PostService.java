package com.all.officeSystem.service;

import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

/**
 *职务的业务层
 */
@Service
public interface PostService {
    // 根据页码获取职务列表
    R getPostInfByPage(int page, int items) throws Exception;
}
