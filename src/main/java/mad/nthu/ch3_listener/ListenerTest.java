package mad.nthu.ch3_listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.buf.UriUtil;

public class ListenerTest implements HttpSessionListener, ServletContextListener, ServletRequestListener {

	Log log=LogFactory.getLog(getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		log.info("先建立一個session，ID為: "+session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		log.info("銷毀一個session，ID為: "+session.getId());
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext=sce.getServletContext();
		log.info("即將啟動 "+servletContext.getContextPath());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext=sce.getServletContext();
		log.info("即將關閉 "+servletContext.getContextPath());
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
		String uri=request.getRequestURI();
		uri=request.getQueryString()==null?uri:(uri+"?"+request.getQueryString());
		request.setAttribute("dateCreated", System.currentTimeMillis());
		log.info("IP "+request.getRemoteAddr());
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
		long time=System.currentTimeMillis()-(Long)request.getAttribute("dateCreated");
		log.info(request.getRemoteAddr()+"請求處理結束，用時"+time+"毫秒.");
	}

	

	

	

	

}
