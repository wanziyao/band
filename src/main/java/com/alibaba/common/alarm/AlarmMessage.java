package com.alibaba.common.alarm;

import java.io.Serializable;

import com.alibaba.common.utils.YuGongToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public class AlarmMessage implements Serializable {

    private static final long serialVersionUID = 6110474591366995515L;
    private String            message;
    private String            receiveKey;

    public AlarmMessage(){

    }

    public AlarmMessage(String message, String receiveKey){
        this.message = message;
        this.receiveKey = receiveKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiveKey() {
        return receiveKey;
    }

    public void setReceiveKey(String receiveKey) {
        this.receiveKey = receiveKey;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, YuGongToStringStyle.DEFAULT_STYLE);
    }

}
