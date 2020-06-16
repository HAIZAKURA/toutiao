package run.nya.toutiao.model.bean;

import java.util.Date;

public class Comm {

    // 评论ID
    private Integer cid;

    // 评论内容
    private String ccont;

    // 评论时间
    private Date ctime;

    // 评论删除标记
    private Integer cdel;

    // 主题ID
    private Integer tid;

    // 用户ID
    private Integer uid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCcont() {
        return ccont;
    }

    public void setCcont(String ccont) {
        this.ccont = ccont;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getCdel() {
        return cdel;
    }

    public void setCdel(Integer cdel) {
        this.cdel = cdel;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}
