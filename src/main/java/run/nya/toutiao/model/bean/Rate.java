package run.nya.toutiao.model.bean;

public class Rate {

    // 评分ID
    private Integer rid;

    // 评分 赞
    private Integer rgood;

    // 评分 踩
    private Integer rbad;

    // 评分 评论数
    private Integer rcnum;

    // 主题ID
    private Integer tid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRgood() {
        return rgood;
    }

    public void setRgood(Integer rgood) {
        this.rgood = rgood;
    }

    public Integer getRbad() {
        return rbad;
    }

    public void setRbad(Integer rbad) {
        this.rbad = rbad;
    }

    public Integer getRcum() {
        return rcnum;
    }

    public void setRcum(Integer rcnum) {
        this.rcnum = rcnum;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

}
