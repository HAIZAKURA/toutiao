package run.nya.toutiao.model.bean;

import java.util.Date;

public class Post {

    // 投稿ID
    private Integer pid;

    // 投稿名
    private String pname;

    // 投稿内容
    private String pcont;

    // 投稿时间
    private Date ptime;

    // 投稿状态
    private Integer pstat;

    // 投稿主题ID
    private Integer ptid;

    // 主题ID
    private Integer fid;

    // 用户ID
    private Integer uid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcont() {
        return pcont;
    }

    public void setPcont(String pcont) {
        this.pcont = pcont;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Integer getPstat() {
        return pstat;
    }

    public void setPstat(Integer pstat) {
        this.pstat = pstat;
    }

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}
