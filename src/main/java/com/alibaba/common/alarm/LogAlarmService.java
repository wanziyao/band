package com.alibaba.common.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.common.lifecycle.AbstractYuGongLifeCycle;

/**
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public class LogAlarmService extends AbstractYuGongLifeCycle implements AlarmService {

    private static final Logger logger = LoggerFactory.getLogger(LogAlarmService.class);

    public void sendAlarm(AlarmMessage data) {
        logger.error("Alarm:{} , Receiver:{}", new Object[] { data.getMessage(), data.getReceiveKey() });
    }

}