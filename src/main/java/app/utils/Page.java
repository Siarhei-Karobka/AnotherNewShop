package app.utils;

import java.util.List;

public class Page<T> {
    private PageRequest request;
    private long total;
    private List<T> content;

    public PageRequest getRequest() {
        return request;
    }

    public void setRequest(PageRequest request) {
        this.request = request;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
