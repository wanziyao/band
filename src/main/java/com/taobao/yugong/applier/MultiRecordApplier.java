package com.taobao.yugong.applier;

import java.util.ArrayList;
import java.util.List;

import com.taobao.yugong.common.model.record.Record;
import com.taobao.yugong.common.utils.YuGongUtils;
import com.taobao.yugong.exception.YuGongException;

/**
 * @author agapple 2016年9月18日 下午9:20:20
 * @since 1.0.4
 */
public class MultiRecordApplier extends AbstractRecordApplier {

    private List<RecordApplier> appliers = new ArrayList<RecordApplier>();

    public MultiRecordApplier(){
    }

    public void start() {
        for (RecordApplier applier : appliers) {
            applier.start();
        }

        super.start();
    }

    public void stop() {
        super.stop();
        for (RecordApplier applier : appliers) {
            applier.stop();
        }
    }

    @Override
    public void apply(List<Record> records) throws YuGongException {
        if (YuGongUtils.isEmpty(records)) {
            return;
        }

        for (RecordApplier applier : appliers) {
            applier.apply(records);
        }
    }

    public void addRecordApplier(RecordApplier applier) {
        this.appliers.add(applier);
    }
}
