package com.alibaba.extractor;

import java.util.List;

import com.alibaba.common.lifecycle.YuGongLifeCycle;
import com.alibaba.common.model.ExtractStatus;
import com.alibaba.common.model.position.Position;
import com.alibaba.common.model.record.Record;
import com.alibaba.exception.YuGongException;

/**
 * 数据获取
 * 
 * @author agapple 2013-9-3 下午2:36:56
 * @since 3.0.0
 */
public interface RecordExtractor extends YuGongLifeCycle {

    /**
     * 获取增量数据
     * 
     * @return
     * @throws YuGongException
     */
    public List<Record> extract() throws YuGongException;

    /**
     * @return 当前extractor的状态,{@linkplain ExtractStatus}
     */
    public ExtractStatus status();

    /**
     * 反馈数据处理成功
     * 
     * @throws YuGongException
     */
    public Position ack(List<Record> records) throws YuGongException;
}
