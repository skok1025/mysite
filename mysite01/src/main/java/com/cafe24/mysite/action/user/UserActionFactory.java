package com.cafe24.mysite.action.user;

import com.cafe24.mysite.action.main.MainAction;
import com.cafe24.web.mvc.Action;
import com.cafe24.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {

		Action action = null;

		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if("login".equals(actionName)) {
			action = new LoginAction();
		}else if("logout".equals(actionName)) {
			action = new LogoutAction();
		}else if("editform".equals(actionName)) {
			action = new EditFormAction();
		}else if("edit".equals(actionName)) {
			action = new EditAction();
		}else if("editsuccess".equals(actionName)) {
			action = new EditSuccessAction();
		}else {
			action = new MainAction();
		}

		return action;
	}

}
