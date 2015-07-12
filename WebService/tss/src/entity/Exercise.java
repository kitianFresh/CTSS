package entity;

import javax.activation.DataHandler;

public class Exercise {
	private int eid;
	private String etitle;
	private String eteacher;
	private String elevel;
	private int esum;
	private int eleftcount;
	private boolean eispass;
	private String esummary;
	private String efilepath;
	

	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEtitle() {
		return etitle;
	}
	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}
	public String getEteacher() {
		return eteacher;
	}
	public void setEteacher(String eteacher) {
		this.eteacher = eteacher;
	}
	public String getElevel() {
		return elevel;
	}
	public void setElevel(String elevel) {
		this.elevel = elevel;
	}
	public int getEsum() {
		return esum;
	}
	public void setEsum(int esum) {
		this.esum = esum;
	}
	public int getEleftcount() {
		return eleftcount;
	}
	public void setEleftcount(int eleftcount) {
		this.eleftcount = eleftcount;
	}
	public boolean isEispass() {
		return eispass;
	}
	public void setEispass(boolean eispass) {
		this.eispass = eispass;
	}
	public String getEsummary() {
		return esummary;
	}
	public void setEsummary(String esummary) {
		this.esummary = esummary;
	}
	public String getEfilepath() {
		return efilepath;
	}
	public void setEfilepath(String efilepath) {
		this.efilepath = efilepath;
	}
	
}
