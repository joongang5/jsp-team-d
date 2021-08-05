package bbs.member.service;

public class YesOrNoService {
	YesOrNoRequest yesOrNo = new YesOrNoRequest();
	String adminKey;
	String userKey;
	
	public String IsSame(String adminKey, String userKey) {
		
		if(!adminKey.equals(userKey)) {
			return "no";
		}else {
			return "yes";
		}
		
	
	}
	


         

    	 
    
    	
     }            

