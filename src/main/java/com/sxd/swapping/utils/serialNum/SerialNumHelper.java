package com.sxd.swapping.utils.serialNum;

import com.sxd.swapping.utils.dateTime.DateTimeHelper;
import com.xiaoleilu.hutool.date.DateUtil;

import java.util.Date;

public class SerialNumHelper {

    public synchronized static String generateRecordId() {
        String daystr = DateUtil.format(new Date(), DateTimeHelper.PATTERN_2);
        //TODO  redis自增
        return daystr;
    }
}
