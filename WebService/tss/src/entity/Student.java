package entity;

public class Student {
	private String sid;
	private String sname;
	private String sclass;
	private String sphone;
	private String sqq;
	private boolean sisleader;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSqq() {
		return sqq;
	}
	public void setSqq(String sqq) {
		this.sqq = sqq;
	}
	public boolean isSisleader() {
		return sisleader;
	}
	public void setSisleader(boolean sisleader) {
		this.sisleader = sisleader;
	}
}
