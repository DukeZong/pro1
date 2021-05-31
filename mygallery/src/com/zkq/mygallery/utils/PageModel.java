package com.zkq.mygallery.utils;

import com.zkq.mygallery.entity.Painting;

import java.util.List;
/*
* 分页模型对象
* */
public class PageModel {
    private int page;
    private int totalPages;
    private int rows;
    private int totalRows;
    private int pageStartRow;
    private int pageEndRow;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private List pageData;

    public PageModel() {
    }

    public PageModel(List data, int page, int rows){
        this.page = page;
        this.rows = rows;
        this.totalRows = data.size();
        //java 中两个整数相除只会得到整数,小数部分会丢失,需要转为浮点在运算
        this.totalPages =new Double(Math.ceil(totalRows/(rows*1f))).intValue();
        this.pageStartRow = (page-1)*rows;
        this.pageEndRow = page*rows;
        if(pageEndRow>totalRows){
            pageEndRow=totalRows;
        }
        //此处存在下标越界问题
        pageData = data.subList(pageStartRow,pageEndRow);
        if(page>1){
            this.hasPreviousPage = true;
        }else {
            this.hasPreviousPage = false;
        }
        if(page<totalPages){
            this.hasNextPage = true;
        }else {
            this.hasNextPage = false;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public List getPageData() {
        return pageData;
    }

    public void setPageData(List pageData) {
        this.pageData = pageData;
    }
}
