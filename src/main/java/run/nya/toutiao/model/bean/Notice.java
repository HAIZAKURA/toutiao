package run.nya.toutiao.model.bean;

import java.util.Date;

public class Notice {

    // 通知ID
    private Integer nid;

    // 通知类型
    private Integer ntype;

    // 通知状态
    private Integer nstat;

    // 通知 主题ID/评论ID
    private Integer ntcid;

    // 通知内容
    private String ncont;

    // 通知时间
    private Date ntime;

    // 用户ID
    private Integer uid;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getNtype() {
        return ntype;
    }

    public void setNtype(Integer ntype) {
        this.ntype = ntype;
    }

    public Integer getNstat() {
        return nstat;
    }

    public void setNstat(Integer nstat) {
        this.nstat = nstat;
    }

    public Integer getNtcid() {
        return ntcid;
    }

    public void setNtcid(Integer ntcid) {
        this.ntcid = ntcid;
    }

    public String getNcont() {
        return ncont;
    }

    public void setNcont(String ncont) {
        this.ncont = ncont;
    }

    public Date getNtime() {
        return ntime;
    }

    public void setNtime(Date ntime) {
        this.ntime = ntime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}
