package com.example.application.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.authentication.MySession;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.dependency.CssImport;

@Route(value = "login", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Login")
@CssImport("./views/login/login-view.css")
public class LoginView extends Div {

    public LoginView() {
        addClassName("login-view");

        LoginForm component = new LoginForm();
        component.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                navigateToStoryPointing();
            } else {
                component.setError(true);
            }
        });

        add(component);        
        
    }

	private void navigateToStoryPointing() {
		UI.getCurrent().navigate("story-points");
	}

	private boolean authenticate(LoginEvent e) {
		if ("password".equals(e.getPassword())) {
			MySession.login(e.getUsername());
			return true;
		} else {
			return false;
		}
	}

}
