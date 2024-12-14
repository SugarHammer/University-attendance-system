package com.lxq.Tools;

import com.lxq.model.object.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageTools {

    private Page page;
    private int itemNum = 10;


    public List PageHelper(List totalList, String pageNumber, ModelAndView mv) {
        //分页后列表
        List pageList = new ArrayList<>();
        page = new Page();
        int itemCount = totalList.size();
        page.setItemNum(itemNum);
        page.setDisplayPage(Integer.parseInt(pageNumber));
        page.setPageCount(itemCount % page.getItemNum() != 0 ? itemCount / page.getItemNum() + 1 : itemCount / page.getItemNum());

        int firstItem, lastItem = 0;
        firstItem = (page.getDisplayPage() - 1) * page.getItemNum() + 1;
        lastItem = firstItem + page.getItemNum() - 1;
        if (lastItem > itemCount) {
            lastItem = itemCount;
        }

        for (int i = firstItem; i <= lastItem; i++) {
            pageList.add(totalList.get(i - 1));
        }

        //计算前一页和后一页页码
        if (page.getPageCount() > 1) {
            if (page.getDisplayPage() < page.getPageCount() && page.getDisplayPage() > 1) {
                page.setPrevPage(page.getDisplayPage() - 1);
                page.setNextPage(page.getDisplayPage() + 1);
            }
            if (page.getDisplayPage() == page.getPageCount()) {
                page.setPrevPage(page.getDisplayPage() - 1);
                page.setNextPage(-1);
            }
            if (page.getDisplayPage() == 1) {
                page.setPrevPage(-1);
                page.setNextPage(page.getDisplayPage() + 1);
            }
        } else {
            page.setPrevPage(-1);
            page.setNextPage(-1);
        }

        System.out.println(page.toString());
        mv.addObject("pageInfo", page);

        return pageList;
    }


}
