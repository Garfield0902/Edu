package com.edu.domain;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

public class Tzgg {
    private String tzggh;

    private String tzggbt;

    private Date tzggsj;

    private Integer tzggbz;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    private Integer recordStatus;

    private Integer recordVersion;

    private byte[] tzggnr;
    
    private String nr;

    public String getTzggh() {
        return tzggh;
    }

    public void setTzggh(String tzggh) {
        this.tzggh = tzggh == null ? null : tzggh.trim();
    }

    public String getTzggbt() {
        return tzggbt;
    }

    public void setTzggbt(String tzggbt) {
        this.tzggbt = tzggbt == null ? null : tzggbt.trim();
    }

    public Date getTzggsj() {
        return tzggsj;
    }

    public void setTzggsj(Date tzggsj) {
        this.tzggsj = tzggsj;
    }

    public Integer getTzggbz() {
        return tzggbz;
    }

    public void setTzggbz(Integer tzggbz) {
        this.tzggbz = tzggbz;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
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

    public byte[] getTzggnr() {
        return tzggnr;
    }

    public void setTzggnr(byte[] tzggnr) {
        this.tzggnr = tzggnr;
        try {
			this.nr = new String(tzggnr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	@Override
	public String toString() {
		return "Tzgg [tzggh=" + tzggh + ", tzggbt=" + tzggbt + ", tzggsj="
				+ tzggsj + ", tzggbz=" + tzggbz + ", createAt=" + createAt
				+ ", createBy=" + createBy + ", updateAt=" + updateAt
				+ ", updateBy=" + updateBy + ", recordStatus=" + recordStatus
				+ ", recordVersion=" + recordVersion + ", tzggnr="
				+ Arrays.toString(tzggnr) + ", nr=" + nr + "]";
	}
}