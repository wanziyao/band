package com.alibaba.translator.core;

import java.util.List;

import com.google.common.collect.Lists;
import com.alibaba.common.model.record.OracleIncrementRecord;
import com.alibaba.common.model.record.OracleIncrementRecord.DiscardType;
import com.alibaba.common.model.record.Record;
import com.alibaba.translator.AbstractDataTranslator;

/**
 * 要忽略oracle基于mlog增量的几种类型
 * 
 * @author agapple 2013-9-27 下午10:57:49
 */
public class OracleIncreamentDataTranslator extends AbstractDataTranslator {

    public List<Record> translator(List<Record> records) {
        List<Record> result = Lists.newArrayList();
        for (Record record : records) {
            if (translator(record)) {
                result.add(record);
            }
        }

        return result;
    }

    public boolean translator(Record record) {
        if (record instanceof OracleIncrementRecord) {
            DiscardType discardType = ((OracleIncrementRecord) record).getDiscardType();
            return discardType.isNotDiscard();// 返回true代表需要同步
        } else {
            return super.translator(record);
        }
    }
}
