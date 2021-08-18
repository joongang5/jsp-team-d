package bbs.member.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import bbs.auth.model.User;
import bbs.member.model.Member;
import bbs.member.service.FileUploadService;
import bbs.member.service.ModifyRequest;
import bbs.member.service.SaveImgDBService;
import bbs.mvc.command.CommandHandler;

public class ImageUploadHandler extends CommandHandler { //현

	FileUploadService fileUpload = new FileUploadService();
	
	
	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/profilUploadSuccess.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		
		req.setCharacterEncoding("utf-8");
		MultipartRequest mr = fileUpload.fileUpload(req);
		
		String profileImage =  mr.getFilesystemName("inputFile");
		System.out.println(profileImage);

		req.setAttribute("profileImage", profileImage);
		
		ModifyRequest modiReq = new ModifyRequest(userId);
     	modiReq.setImgName(profileImage);
		
     	try {
     		 SaveImgDBService.modify(modiReq);
     		 System.out.println("프로필 사진 수정 성공");
     		 return getFormViewName();
     	 }catch(Exception e){
     		 System.out.println("수정 실패");
     	 }
     	
       
     	
     	
		return "/WEB-INF/view/changeEmailFail.jsp";
	}

}
