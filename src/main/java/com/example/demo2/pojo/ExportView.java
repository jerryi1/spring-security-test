package com.example.demo2.pojo;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

/**
 * @author lihuaqing
 * @create 2019-03-06 13:34
 **/
@Data
@NoArgsConstructor
public class ExportView {
    private ExportParams exportParams;
    private List<?> dataList;
    private Class<?> cls;

    public ExportView(Builder builder) {
        this.exportParams = builder.exportParams;
        this.dataList = builder.dataList;
        this.cls = builder.cls;
    }

    public static class Builder {
        private ExportParams exportParams=null;
        private List<?> dataList=null;
        private Class<?> cls=null;
        public Builder() {
        }
        public Builder exportParams(ExportParams exportParams) {
            this.exportParams = exportParams;
            return this;
        }
        public Builder dataList(List<?> dataList) {
            this.dataList = dataList;
            return this;
        }
        public Builder cls(Class<?> cls) {
            this.cls = cls;
            return this;
        }
        public ExportView create() {
            return new ExportView(this);
        }
    }
}