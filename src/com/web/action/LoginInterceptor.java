package com.web.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.web.po.NoteUser;

public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -5770220038295898015L;
	final Logger logger = Logger.getLogger(LoginInterceptor.class);
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		NoteUser noteUser = (NoteUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(noteUser==null) {
			logger.info("没有登录，跳转到登录页面");
			ActionSupport actionSupport =  (ActionSupport) invocation.getAction();
            actionSupport.addActionError("您还没有登录，没有访问权限！");
			return "login";
		}else {
			return invocation.invoke();
		}
	}

}
