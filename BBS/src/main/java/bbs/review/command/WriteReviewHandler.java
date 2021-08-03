package bbs.review.command;

import java.util.HashMap;
import java.util.Map;

//�� ��û�� ó���� handler�� ������ ����

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.review.model.Writer;
import bbs.review.service.WriteRequest;
import bbs.review.service.WriteReviewService;



public class WriteReviewHandler extends CommandHandler {

	private WriteReviewService writeService = new WriteReviewService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/newReviewForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		//���ǿ��� �α����� ����� ������ ���Ѵ�.
		User user = (User)req.getSession(false).getAttribute("authUser");
		//user�� httpservletrequest�� �̿��ؼ� writerequest ��ü�� �����Ѵ�.
		WriteRequest writeReq = createWriteRequest(user, req);
		//writereq ��ü�� ��ȿ���� �˻��Ѵ�.
		writeReq.validate(errors);
		
		
		//������ �����ϸ� ���� �ٽ� �����ش�.
		if(errors.isEmpty() == false) {
			return getFormViewName();
		}
		
		//writereviewservice�� �̿��ؼ� �Խñ��� ����ϰ�, ��ϵ� �Խñ��� id�� ���Ϲ޴´�.
		int newReviewNo = writeService.write(writeReq);
		//�� ���� id�� request�� newreviewid �Ӽ��� �����Ѵ�. ó�� ����� ������ jsp�� �� �Ӽ����� ����ؼ� ��ũ�� �����Ѵ�.
		req.setAttribute("newReviewNo", newReviewNo);
		
		return"/WEB-INF/view/newReviewSuccess.jsp";
		
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
	}
}
