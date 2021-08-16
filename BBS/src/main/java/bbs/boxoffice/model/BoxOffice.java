package bbs.boxoffice.model;

import bbs.movie.model.BaseMovie;

public class BoxOffice extends BaseMovie {

	private String targetDt;		// 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력합니다.
	private int rnum; 				// 순번을 출력합니다.
	private int rank; 				// 해당일자의 박스오피스 순위를 출력합니다.
	private int rankInten; 			// 전일대비 순위의 증감분을 출력합니다.
	private String rankOldAndNew; 	// 랭킹에 신규진입여부를 출력합니다. “OLD” : 기존 , “NEW” : 신규
	private String openDt; 			// 영화의 개봉일을 출력합니다.
	private long salesAmt; 			// 해당일의 매출액을 출력합니다.
	private float salesShare;		// 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
	private int salesInten;			// 전일 대비 매출액 증감분을 출력합니다.
	private float salesChange;		// 전일 대비 매출액 증감 비율을 출력합니다.
	private long salesAcc;			// 누적매출액을 출력합니다.
	private int audiCnt;			// 해당일의 관객수를 출력합니다.
	private int audiInten;			// 전일 대비 관객수 증감분을 출력합니다.
	private float audiChange;		// 전일 대비 관객수 증감 비율을 출력합니다.
	private int audiAcc; 			// 누적관객수를 출력합니다.
	private int scrnCnt; 			// 해당일자에 상영한 스크린수를 출력합니다.
	private int showCnt; 			// 해당일자에 상영된 횟수를 출력합니다.
	
	public BoxOffice(String targetDt, int rnum, int rank, int rankInten, String rankOldAndNew, String movieCd, String movieNm, String openDt,
			long salesAmt, float salesShare, int salesInten, float salesChange, long salesAcc, int audiCnt,
			int audiInten, float audiChange, int audiAcc, int scrnCnt, int showCnt) {
		super(movieCd, movieNm);
		
		this.targetDt = targetDt;
		this.rnum = rnum;
		this.rank = rank;
		this.rankInten = rankInten;
		this.rankOldAndNew = rankOldAndNew;
		this.openDt = openDt;
		this.salesAmt = salesAmt;
		this.salesShare = salesShare;
		this.salesInten = salesInten;
		this.salesChange = salesChange;
		this.salesAcc = salesAcc;
		this.audiCnt = audiCnt;
		this.audiInten = audiInten;
		this.audiChange = audiChange;
		this.audiAcc = audiAcc;
		this.scrnCnt = scrnCnt;
		this.showCnt = showCnt;
	}

	public String getTargetDt() {
		return targetDt;
	}

	public void setTargetDt(String targetDt) {
		this.targetDt = targetDt;
	}
	
	public int getRnum() {
		return rnum;
	}

	public int getRank() {
		return rank;
	}

	public int getRankInten() {
		return rankInten;
	}

	public String getRankOldAndNew() {
		return rankOldAndNew;
	}

	public String getOpenDt() {
		return openDt;
	}

	public long getSalesAmt() {
		return salesAmt;
	}

	public float getSalesShare() {
		return salesShare;
	}

	public int getSalesInten() {
		return salesInten;
	}

	public float getSalesChange() {
		return salesChange;
	}

	public long getSalesAcc() {
		return salesAcc;
	}

	public int getAudiCnt() {
		return audiCnt;
	}

	public int getAudiInten() {
		return audiInten;
	}

	public float getAudiChange() {
		return audiChange;
	}

	public int getAudiAcc() {
		return audiAcc;
	}

	public int getScrnCnt() {
		return scrnCnt;
	}

	public int getShowCnt() {
		return showCnt;
	}
}

