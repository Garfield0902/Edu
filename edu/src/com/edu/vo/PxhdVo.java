package com.edu.vo;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class PxhdVo  extends Pagination{
    private String hdid;

    private String hdzt;

    private String zjr;

    private Integer hdnf;

    private Date bmjzsj;

    private Date hdsj;

    private String hdzzdw;

    private String hddd;

    private Integer bmzt;

    private Integer zdcyrs;

    private Integer dqcyrs;

    private Integer hdpjrs;

    private Integer hdjb;

    private Integer hdxf;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String upateBy;

    private Integer recordStatus;

    private Integer recordVersion;

    private byte[] hdnr;
    
    private String nr;
    
    private Date startDate;
    
    private Date endDate;

    public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid == null ? null : hdid.trim();
    }

    public String getHdzt() {
        return hdzt;
    }

    public void setHdzt(String hdzt) {
        this.hdzt = hdzt == null ? null : hdzt.trim();
    }

    public String getZjr() {
        return zjr;
    }

    public void setZjr(String zjr) {
        this.zjr = zjr == null ? null : zjr.trim();
    }

    public Integer getHdnf() {
        return hdnf;
    }

    public void setHdnf(Integer hdnf) {
        this.hdnf = hdnf;
    }

    public Date getBmjzsj() {
        return bmjzsj;
    }

    public void setBmjzsj(Date bmjzsj) {
        this.bmjzsj = bmjzsj;
    }

    public Date getHdsj() {
        return hdsj;
    }

    public void setHdsj(Date hdsj) {
        this.hdsj = hdsj;
    }

    public String getHdzzdw() {
        return hdzzdw;
    }

    public void setHdzzdw(String hdzzdw) {
        this.hdzzdw = hdzzdw == null ? null : hdzzdw.trim();
    }

    public String getHddd() {
        return hddd;
    }

    public void setHddd(String hddd) {
        this.hddd = hddd == null ? null : hddd.trim();
    }

    public Integer getBmzt() {
        return bmzt;
    }

    public void setBmzt(Integer bmzt) {
        this.bmzt = bmzt;
    }

    public Integer getZdcyrs() {
        return zdcyrs;
    }

    public void setZdcyrs(Integer zdcyrs) {
        this.zdcyrs = zdcyrs;
    }

    public Integer getDqcyrs() {
        return dqcyrs;
    }

    public void setDqcyrs(Integer dqcyrs) {
        this.dqcyrs = dqcyrs;
    }

    public Integer getHdpjrs() {
        return hdpjrs;
    }

    public void setHdpjrs(Integer hdpjrs) {
        this.hdpjrs = hdpjrs;
    }

    public Integer getHdjb() {
        return hdjb;
    }

    public void setHdjb(Integer hdjb) {
        this.hdjb = hdjb;
    }

    public Integer getHdxf() {
        return hdxf;
    }

    public void setHdxf(Integer hdxf) {
        this.hdxf = hdxf;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpateBy() {
        return upateBy;
    }

    public void setUpateBy(String upateBy) {
        this.upateBy = upateBy == null ? null : upateBy.trim();
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

    public byte[] getHdnr() {
        return hdnr;
    }

    public void setHdnr(byte[] hdnr) {
        this.hdnr = hdnr;
        try {
			this.nr = new String(hdnr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
}