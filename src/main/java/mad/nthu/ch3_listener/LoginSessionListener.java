package mad.nthu.ch3_listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginSessionListener implements HttpSessionAttributeListener {

	Log log=LogFactory.getLog(this.getClass());
	Map<String, HttpSession>map=new HashMap<String,HttpSession>();
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name=event.getName();
		
		if(name.equals("personInfo")) {
			PersonInfo personInfo=(PersonInfo)event.getValue();
			
			if(map.get(personInfo.getAccount())!=null) {
				HttpSession session=map.get(personInfo.getAccount());
				PersonInfo oldPersonInfo=(PersonInfo)session.getAttribute("personInfo");
				
				log.info("帳號: "+oldPersonInfo.getAccount()+" 在 "+oldPersonInfo.getIp()+" 已經登入，該登入將被迫下線。");
				
				session.removeAttribute("personInfo");
				session.setAttribute("msg", "您的帳號已經在其他機器上登入，您被迫下線。");
			}
			map.put(personInfo.getAccount(), event.getSession());
			log.info("帳號: "+personInfo.getAccount()+" 在 "+personInfo.getIp()+" 登入。");
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		String name=event.getName();
		if(name.equals("personInfo")) {
			PersonInfo personInfo=(PersonInfo)event.getValue();
			map.remove(personInfo.getAccount());
			log.info("帳號: "+personInfo.getAccount()+" 註銷。");
		}
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String name=event.getName();
		if(name.equals("personInfo")) {
			PersonInfo oldPersonInfo=(PersonInfo)event.getValue();
			map.remove(oldPersonInfo.getAccount());
			
			PersonInfo personInfo=(PersonInfo)event.getSession().getAttribute("personInfo");
			
			if(map.get(personInfo.getAccount())!=null) {
				HttpSession session=map.get(personInfo.getAccount());
				session.removeAttribute("personInfo");
				session.setAttribute("msg", "您的帳號已經在其他機器上登入，您被迫下線。");
			}
			map.put(personInfo.getAccount(), event.getSession());
			log.info("attributeReplaced: Called。");
		}
	}
	
	
}
