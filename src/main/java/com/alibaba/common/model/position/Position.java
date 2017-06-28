package com.alibaba.common.model.position;

import java.io.Serializable;

import com.alibaba.common.utils.YuGongToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 事件唯一标示
 * 
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public abstract class Position implements Serializable {

    private static final long serialVersionUID = 2332798099928474975L;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, YuGongToStringStyle.DEFAULT_STYLE);
    }

    public Position clone() {
        return null;
    }

}
