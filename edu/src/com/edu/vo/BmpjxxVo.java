package com.edu.vo;

import java.util.Date;

public class BmpjxxVo  extends Pagination{
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
    
    private Integer hdnf;

	private Date pjsj;

    private Integer pjfs;

    private String lrr;

    private Integer recordStatus;

    private Integer recordVersion;
    
    //��������
    private String searchType;
    
    private String time_start;
    private String time_end;
    
    //��ְ�ʸ������
    private String rzzgmcm;
    
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

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getRzzgmcm() {
		return rzzgmcm;
	}

	public void setRzzgmcm(String rzzgmcm) {
		this.rzzgmcm = rzzgmcm;
	}

    public Integer getHdnf() {
		return hdnf;
	}

	public void setHdnf(Integer hdnf) {
		this.hdnf = hdnf;
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
