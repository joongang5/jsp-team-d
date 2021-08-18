package bbs.member.service;

public class MemberGradeUpRequest {

	private String id;
	private int grade;
	private int myPoint;

	private final int MAX_POINT = 100;

	public MemberGradeUpRequest(String id, int grade, int myPoint) {
		this.id = id;
		this.grade = grade;
		this.myPoint = myPoint;
	}

	public String getId() {
		return id;
	}
	
	public int getGrade() {
		return grade;
	}

	public int getMyPoint() {
		return myPoint;
	}

	public void gradeUp() {
		// 2.2 읽어온 현재 포인트 값이 maxPoint를 넘어서면 레벨업
		if (myPoint >= MAX_POINT) {
			// 2.2.1 레벨업한 경우 grade - 1, point = 0
			grade--;
			
			// 레벨업 테스트 할 때
			//myPoint = myPoint - MAX_POINT;
			if(grade <= 2 ) { //레벨 2 도달하면 더 이상 레벨업x  
				grade = 2;
			}
		} else {
			// 2.2.2.1 grade은 그대로, point + 5
			myPoint += 5;
		}
	}
}