package run.nya.toutiao.model.bean;

import java.util.Date;

public class Users {

    // 用户ID
    private Integer uid;

    // 用户名
    private String uname;

    // 密码
    private String upass;

    // 邮箱
    private String umail;

    // 注册时间
    private Date utime;

    // 用户状态
    private Integer ustat;

    // 用户简介
    private String udesc;

    // 权限ID
    private Integer aid;

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

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Integer getUstat() {
        return ustat;
    }

    public void setUstat(Integer ustat) {
        this.ustat = ustat;
    }

    public String getUdesc() {
        return udesc;
    }

    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

}
