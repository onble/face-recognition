package com.all.faceRecognition.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
    public static LocalDateTime getCurrentChinaTime() {
        ZonedDateTime chinaTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        return chinaTime.toLocalDateTime();
    }
}
