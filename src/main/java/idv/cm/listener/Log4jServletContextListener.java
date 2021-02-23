package idv.cm.listener;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jServletContextListener implements ServletContextListener {
	
	public Logger logger = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//create instance
		logger = LogManager.getLogger();
		logger.info("---contextInitialized---");
		
		ServletContext ctx = sce.getServletContext();
		Enumeration<String> nu = ctx.getInitParameterNames();
		while(nu.hasMoreElements()) {
			logger.info("context-params - ");
			logger.info(nu.nextElement());
		}
		logger.info("log4j2 init");
		ctx.setAttribute("log4j2", logger);
//		String projectName = ctx.getInitParameter("log4jContextName");
//		logger.info(projectName);
//		ctx.setAttribute("projectName", projectName);
//		ctx.log("projectName set to "+projectName);
		//ServletContextListener.super.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(logger!=null) {
			logger.info("---contextDestroyed---");
			//destroy instance
			logger.info("log4j2 removed!");
			sce.getServletContext().removeAttribute("log4j2");
			logger=null;
		}
		
		//ServletContextListener.super.contextDestroyed(sce);
	}
}
