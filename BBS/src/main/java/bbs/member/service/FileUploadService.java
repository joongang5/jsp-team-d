package bbs.member.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUploadService {
	public MultipartRequest fileUpload(HttpServletRequest req) { //20210808 ��
	
	int sizeLimit = 10*1024*1024;
	String savePath = req.getServletContext().getRealPath("upload");
	
	
	MultipartRequest mr = null;
	
	//System.out.println("savePath:" + savePath);
	
	
	try {
		mr = new MultipartRequest(req, savePath, sizeLimit,"utf-8", new DefaultFileRenamePolicy());
		return mr;
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("�̹��� ���ε� ����");
		return null;
	}
	
	
	}
}
