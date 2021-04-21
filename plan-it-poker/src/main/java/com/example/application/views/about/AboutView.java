package com.example.application.views.about;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.authentication.MySession;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
@CssImport("./views/about/about-view.css")
public class AboutView extends Div {
	
    public AboutView() {
        addClassName("about-view");
        
        Anchor dontClick = new Anchor("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "More info ...");
        
        VerticalLayout vertical = new VerticalLayout();
	    vertical.add(new Text("This site was created to help story point Chimera's jira stories. "), dontClick); 
	    
        add(vertical);
    }

}
