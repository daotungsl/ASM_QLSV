package entityGet;

public class StudentMeta {
    private int totalItem;
    private int totalPage;
    private int litmit;
    private int page;

    public StudentMeta() {
    }

    public StudentMeta(int totalItem, int totalPage, int litmit, int page) {
        this.totalItem = totalItem;
        this.totalPage = totalPage;
        this.litmit = litmit;
        this.page = page;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLitmit() {
        return litmit;
    }

    public void setLitmit(int litmit) {
        this.litmit = litmit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
