package com.alibaba.applier;

import java.util.List;

import com.alibaba.common.lifecycle.YuGongLifeCycle;
import com.alibaba.common.model.record.Record;
import com.alibaba.exception.YuGongException;

/**
 * 数据提交
 * 
 * @author agapple 2013-9-9 下午5:57:19
 * @since 3.0.0
 */
public interface RecordApplier extends YuGongLifeCycle {

    public void apply(List<Record> records) throws YuGongException;

}
