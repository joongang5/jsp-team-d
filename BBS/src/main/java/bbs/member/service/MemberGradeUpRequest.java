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
		// 2.2 �о�� ���� ����Ʈ ���� maxPoint�� �Ѿ�� ������
		if (myPoint >= MAX_POINT) {
			// 2.2.1 �������� ��� grade - 1, point = 0
			grade--;
			// �����ϸ� ����Ʈ + 150 �ϸ� �������ϰ� 50����Ʈ ���׿�
			// �̷����ϸ� �׽�Ʈ �ι�?���� �����ҰŰ����� �Ф� �غ��Կ�!
			//myPoint = myPoint - MAX_POINT;
		} else {
			// 2.2.2.1 grade�� �״��, point + 5
			myPoint += 5;
		}
	}
}
