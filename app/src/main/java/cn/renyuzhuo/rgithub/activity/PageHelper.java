package cn.renyuzhuo.rgithub.activity;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public class PageHelper {
    private int page = 1;
    private boolean hasMore = true;

    private int pageContentNum = 30;
    private boolean toastFlag = true;

    public PageHelper(int size) {
        if (size == 0) {
            hasMore = true;
            page = 0;
            return;
        }
        if (size % pageContentNum == 0) {
            hasMore = true;
            page = size / 30;
        } else {
            hasMore = false;
            page = size / 30 + 1;
        }
    }

    public PageHelper() {
        hasMore = true;
        page = 0;
    }

    public int nextPage() {
        return ++page;
    }

    public void hasMoreOrNot(int size) {
        if (size == pageContentNum) {
            hasMore = true;
        } else {
            hasMore = false;
        }
    }

    public boolean hasMore() {
        return hasMore;
    }

    public boolean showToast() {
        if (toastFlag) {
            toastFlag = false;
            return true;
        }
        return toastFlag;
    }
}
