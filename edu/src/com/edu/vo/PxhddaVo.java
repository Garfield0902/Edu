package com.edu.vo;

import java.util.Date;

public class PxhddaVo {
	private String hdzt;
	private Integer hdnf;
	private String hdzzdw;
	private Integer hdxf;
	private Date hdsj;
	private String rzzgmcm;
	private String zgh;
	
	public String getHdzt() {
		return hdzt;
	}
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	public Integer getHdnf() {
		return hdnf;
	}
	public void setHdnf(Integer hdnf) {
		this.hdnf = hdnf;
	}
	public String getHdzzdw() {
		return hdzzdw;
	}
	public void setHdzzdw(String hdzzdw) {
		this.hdzzdw = hdzzdw;
	}
	public Integer getHdxf() {
		return hdxf;
	}
	public void setHdxf(Integer hdxf) {
		this.hdxf = hdxf;
	}
	public Date getHdsj() {
		return hdsj;
	}
	public void setHdsj(Date hdsj) {
		this.hdsj = hdsj;
	}
	public String getRzzgmcm() {
		return rzzgmcm;
	}
	public void setRzzgmcm(String rzzgmcm) {
		this.rzzgmcm = rzzgmcm;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	@Override
	public String toString() {
		return "PxhddaVo [hdzt=" + hdzt + ", hdnf=" + hdnf + ", hdzzdw="
				+ hdzzdw + ", hdxf=" + hdxf + ", rzzgmcm=" + rzzgmcm + ", zgh="
				+ zgh + "]";
	}
}
