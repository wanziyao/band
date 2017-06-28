package com.alibaba.translator;

import java.util.List;

import com.google.common.collect.Lists;
import com.alibaba.common.model.record.Record;

/**
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public class AbstractDataTranslator implements DataTranslator {

    protected TableTranslators.TableTranslator translator;

    /**
     * 转换schemaName,如果返回null,则以每条数据Record的转义为准
     */
    public String translatorSchema() {
        return null;
    }

    /**
     * 转换tableName,如果返回null,则以每条数据Record的转义为准
     */
    public String translatorTable() {
        return null;
    }

    public boolean translator(Record record) {
        return true;
    }

    public List<Record> translator(List<Record> records) {
        List<Record> result = Lists.newArrayList();
        for (Record record : records) {
            String schema = translatorSchema();
            if (schema != null) {
                record.setSchemaName(schema);
            }

            String table = translatorTable();
            if (table != null) {
                record.setTableName(table);
            }

            if (translator != null) {
                record = translator.translator(record);
            }

            if (record != null && translator(record)) {
                result.add(record);
            }
        }

        return result;
    }

    public TableTranslators.TableTranslator getTranslator() {
        return translator;
    }

    public void setTranslator(TableTranslators.TableTranslator translator) {
        this.translator = translator;
    }

}
