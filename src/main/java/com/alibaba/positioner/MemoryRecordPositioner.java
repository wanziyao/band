package com.alibaba.positioner;

import com.alibaba.common.lifecycle.AbstractYuGongLifeCycle;
import com.alibaba.common.model.position.Position;
import com.alibaba.exception.YuGongException;

/**
 * 简单的内存记录
 * 
 * @author agapple 2013-9-22 下午3:35:32
 */
public class MemoryRecordPositioner extends AbstractYuGongLifeCycle implements RecordPositioner {

    protected volatile Position position;

    public Position getLatest() {
        return position;
    }

    public void persist(Position position) throws YuGongException {
        this.position = position;
    }

}
