package com.all.officeSystem.service;

import com.all.officeSystem.common.R;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    // TODO: GJY 参考MeetingService
    R getDepartmentInfByPage(int page, int items) throws Exception;
}
