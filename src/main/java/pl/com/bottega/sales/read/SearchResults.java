package pl.com.bottega.sales.read;

import java.util.List;

public class SearchResults<T> {

    private List<T> page;

    private int pagesCount, currentPage, pageSize;

    private long totalCount;

    public SearchResults() {}

    public SearchResults(List<T> page, int pagesCount, int currentPage, int pageSize, long totalCount) {
        this.page = page;
        this.pagesCount = pagesCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public List<T> getPage() {
        return page;
    }

    public void setPage(List<T> page) {
        this.page = page;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
