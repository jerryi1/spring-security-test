package com.example.demo2.pojo;

import com.google.common.collect.Lists;
import java.util.*;
/**
 * @author lihuaqing
 * @create 2019-03-06 13:40
 **/
public class ExportMoreView {
    private List<ExportView> moreViewList= Lists.newArrayList();
    public List<ExportView> getMoreViewList() {
        return moreViewList;
    }
    public void setMoreViewList(List<ExportView> moreViewList) {
        this.moreViewList = moreViewList;
    }
}