package run.nya.toutiao.model.bean;

import java.util.Date;

public class Notice {

    // 通知ID
    private Integer nid;

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
