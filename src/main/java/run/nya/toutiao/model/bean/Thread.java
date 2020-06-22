package run.nya.toutiao.model.bean;

import java.util.Date;

public class Thread {

    // 主题ID
    private Integer tid;

    // 主题名
    private String tname;

    // 主题内容
    private String tcont;

    // 主题时间
    private Date ttime;

    // 主题置顶标记
    private Integer ttop;

    // 主题删除标记
    private Integer tdel;

    // 分区ID
    private Integer fid;

    // 分区名
    private String fname;

    // 用户ID
    private Integer uid;

    // 用户名
    private String uname;

    // 用户简介
    private String udesc;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTcont() {
        return tcont;
    }

    public void setTcont(String tcont) {
        this.tcont = tcont;
    }

    public Date getTtime() {
        return ttime;
    }

    public void setTtime(Date ttime) {
        this.ttime = ttime;
    }

    public Integer getTtop() {
        return ttop;
    }

    public void setTtop(Integer ttop) {
        this.ttop = ttop;
    }

    public Integer getTdel() {
        return tdel;
    }

    public void setTdel(Integer tdel) {
        this.tdel = tdel;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUdesc() {
        return udesc;
    }

    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

}
