package com.edu.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 添加学分
 * @author 11016
 *
 */
public class TjxfVo {
	private String xm;
	private String zgh;
	private String xy;
	private String hdid;
    private String hdzt;
    private String zjr;
    private String bmjzsj;
    private Date hdsj;
    private String hdzzdw;
    private String hddd;
    private Integer pjfs;
    private byte[] hdnr;

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getHdid() {
		return hdid;
	}

	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	public String getHdzt() {
		return hdzt;
	}

	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}

	public String getZjr() {
		return zjr;
	}

	public void setZjr(String zjr) {
		this.zjr = zjr;
	}

	public String getBmjzsj() {
		return bmjzsj;
	}

	public void setBmjzsj(String bmjzsj) {
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
		this.hdzzdw = hdzzdw;
	}

	public String getHddd() {
		return hddd;
	}

	public void setHddd(String hddd) {
		this.hddd = hddd;
	}

	

	public Integer getPjfs() {
		return pjfs;
	}

	public void setPjfs(Integer pjfs) {
		this.pjfs = pjfs;
	}

	public byte[] getHdnr() {
		return hdnr;
	}

	public void setHdnr(byte[] hdnr) {
		this.hdnr = hdnr;
	}

	@Override
	public String toString() {
		return "TjxfVo [xm=" + xm + ", zgh=" + zgh + ", xy=" + xy + ", hdid="
				+ hdid + ", hdzt=" + hdzt + ", zjr=" + zjr + ", bmjzsj="
				+ bmjzsj + ", hdsj=" + hdsj + ", hdzzdw=" + hdzzdw + ", hddd="
				+ hddd + ", pjfs=" + pjfs + ", hdnr=" + hdnr + "]";
	}
}
