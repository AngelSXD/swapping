package com.sxd.swapping.utils.page;

import com.sxd.swapping.utils.listSplit.ListSplitUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;


public class PageHelperUtil {

    /**
     * 辅助全量的集合分页
     * @param list
     * @param page
     * @return
     */
    public static <T> List<T> toPage(List<T> list, MyPage page){
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        List<List<T>> lists = ListSplitUtil.splitListBycapacity(list, page.getPageSize());

        page.setTotalCount(list.size());
        page.setPageCount(lists.size());

        if(page.getCurrentPage() > lists.size()){
            return Collections.emptyList();
        }

        List<T> resultList = lists.get(page.getCurrentPage() - 1);

        return resultList;
    }

}
