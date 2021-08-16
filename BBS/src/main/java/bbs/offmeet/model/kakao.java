package bbs.offmeet.model;

public class kakao {
	
	private int no;
	private String juso;
	private String sangho;
	private int TEL;
	
	
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}
	
	public String getJuso() {
		return juso;
	}


	public void setJuso(String juso) {
		this.juso = juso;
	}


	public String getSangho() {
		return sangho;
	}


	public void setSangho(String sangho) {
		this.sangho = sangho;
	}


	public int getTEL() {
		return TEL;
	}


	public void setTEL(int tEL) {
		TEL = tEL;
	}


	public kakao(int no, String juso, String sangho, int TEL) {
		this.no = no;
		this.sangho = sangho;
		this.juso = juso;
		this.TEL = TEL;
	}


	
	

	
}
