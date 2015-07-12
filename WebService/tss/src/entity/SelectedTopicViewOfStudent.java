package entity;

public class SelectedTopicViewOfStudent {
	private String etitle;
	private String leadername;
	private String leaderphone;
	private String leaderqq;
	private int gid;
	private int eid;
	
	public int getGid() {
		return gid;
	}
	public int getEid() {
		return eid;
	}

	public void setGid(int gid) {
		this.gid = gid;
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
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public String getLeaderphone() {
		return leaderphone;
	}
	public void setLeaderphone(String leaderphone) {
		this.leaderphone = leaderphone;
	}
	public String getLeaderqq() {
		return leaderqq;
	}
	public void setLeaderqq(String leaderqq) {
		this.leaderqq = leaderqq;
	}
	
}
