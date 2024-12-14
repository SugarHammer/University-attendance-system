package com.lxq.model.object;

public class Page {
    //每页条数
    private int ItemNum;
    //总页数
    private int pageCount;
    //当前页码
    private int displayPage;
    //下一页码
    private int nextPage;
    //上一页码
    private int prevPage;


    public Page() {
    }

    public Page(int itemNum, int pageCount, int displayPage, int nextPage, int prevPage) {
        ItemNum = itemNum;
        this.pageCount = pageCount;
        this.displayPage = displayPage;
        this.nextPage = nextPage;
        this.prevPage = prevPage;
    }

    public int getItemNum() {
        return ItemNum;
    }

    public void setItemNum(int itemNum) {
        ItemNum = itemNum;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getDisplayPage() {
        return displayPage;
    }

    public void setDisplayPage(int displayPage) {
        this.displayPage = displayPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "ItemNum=" + ItemNum +
                ", pageCount=" + pageCount +
                ", displayPage=" + displayPage +
                ", nextPage=" + nextPage +
                ", prevPage=" + prevPage +
                '}';
    }
}
