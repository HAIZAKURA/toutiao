package run.nya.toutiao.model.bean;

public class Forum {

    // 分区ID
    private Integer fid;

    // 分区名
    private String fname;

    // 分区描述
    private String fdesc;

    // 分区删除标记
    private Integer fdel;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFdesc() {
        return fdesc;
    }

    public void setFdesc(String fdesc) {
        this.fdesc = fdesc;
    }

    public Integer getFdel() {
        return fdel;
    }

    public void setFdel(Integer fdel) {
        this.fdel = fdel;
    }

}
