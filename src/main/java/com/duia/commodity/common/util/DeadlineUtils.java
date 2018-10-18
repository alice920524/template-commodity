package com.duia.commodity.common.util;

import com.duia.enums.QaMode;

import java.util.Date;

public class DeadlineUtils {

    /**
     * 质保期转日期
     * */
    public static Date convertToDate(Integer model,Integer validity) {
        if (null == model) {
            return null;
        }

        if (null == validity) {
            validity = 0;
        }

        // 日
        if (model.equals(QaMode.DAY.getState())) {
            return DateUtils.nextDay(null, validity);
        }
        // 月
        if (model.equals(QaMode.MONTH.getState())) {
            return DateUtils.nextMonth(null, validity);
        }
        return null;
    }
}
