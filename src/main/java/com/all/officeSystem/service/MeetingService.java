package com.all.officeSystem.service;

import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

/**
 * 会议的业务层
 */
@Service
public interface MeetingService {
    // 根据页码获取会议列表
    R getMeetingInfByPage(int page, int items) throws Exception;
}
