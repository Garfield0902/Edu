package com.edu.domain;

import java.util.Date;

public class Bmpjxx {
    private String id;

    private String hdid;

    private String xydm;

    private String xymc;

    private String zgh;

    private String xm;

    private Integer bmbz;

    private Date bmsj;

    private Integer pjbz;

    private Integer pjnf;

    private Date pjsj;

    private Integer pjfs;

    private String lrr;

    private Integer recordStatus;

    private Integer recordVersion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid == null ? null : hdid.trim();
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm == null ? null : xydm.trim();
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc == null ? null : xymc.trim();
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh == null ? null : zgh.trim();
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    public Integer getBmbz() {
        return bmbz;
    }

    public void setBmbz(Integer bmbz) {
        this.bmbz = bmbz;
    }

    public Date getBmsj() {
        return bmsj;
    }

    public void setBmsj(Date bmsj) {
        this.bmsj = bmsj;
    }

    public Integer getPjbz() {
        return pjbz;
    }

    public void setPjbz(Integer pjbz) {
        this.pjbz = pjbz;
    }

    public Integer getPjnf() {
        return pjnf;
    }

    public void setPjnf(Integer pjnf) {
        this.pjnf = pjnf;
    }

    public Date getPjsj() {
        return pjsj;
    }

    public void setPjsj(Date pjsj) {
        this.pjsj = pjsj;
    }

    public Integer getPjfs() {
        return pjfs;
    }

    public void setPjfs(Integer pjfs) {
        this.pjfs = pjfs;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr == null ? null : lrr.trim();
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Integer getRecordVersion() {
        return recordVersion;
    }

    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }

	@Override
	public String toString() {
		return "Bmpjxx [id=" + id + ", hdid=" + hdid + ", xydm=" + xydm
				+ ", xymc=" + xymc + ", zgh=" + zgh + ", xm=" + xm + ", bmbz="
				+ bmbz + ", bmsj=" + bmsj + ", pjbz=" + pjbz + ", pjnf=" + pjnf
				+ ", pjsj=" + pjsj + ", pjfs=" + pjfs + ", lrr=" + lrr
				+ ", recordStatus=" + recordStatus + ", recordVersion="
				+ recordVersion + "]";
	}
}