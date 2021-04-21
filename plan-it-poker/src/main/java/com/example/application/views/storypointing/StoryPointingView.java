package com.example.application.views.storypointing;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.util.List;

import com.example.application.authentication.MySession;
import com.example.application.views.main.MainView;
import com.example.application.views.people.Member;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;

@Route(value = "story-points", layout = MainView.class)
@PageTitle("Story Pointing")
@CssImport("./views/storypointing/story-pointing-view.css")
public class StoryPointingView extends HorizontalLayout {

    private Button sp1 = new Button("1");
    private Button sp2 = new Button("2");
    private Button sp3 = new Button("3");
    private Button sp5 = new Button("5");
    private Button sp8 = new Button("8");
    private Button sp13 = new Button("13");

    private Label label = new Label("");
    

    public StoryPointingView() {
        addClassName("story-pointing-view");
        
        add(sp1, sp2, sp3, sp5, sp8, sp13);
        setVerticalComponentAlignment(Alignment.END, sp1, sp2, sp3, sp5, sp8, sp13);
        sp1.addClickListener(e -> {
        	vote(1);
        });
        sp2.addClickListener(e -> {
        	vote(2);
        });
        sp3.addClickListener(e -> {
        	vote(3);
        });
        sp5.addClickListener(e -> {
        	vote(5);
        });
        sp8.addClickListener(e -> {
        	vote(8);
        });
        sp13.addClickListener(e -> {
        	vote(13);
        });
        
        if (MySession.isVotingInProgress() && MySession.getMyPoints() != null) { 
        	label.setText(getLabel(MySession.getMyPoints()));
        }       
        add(label);

    }
    
    private void vote(int points) {
    	if (MySession.isVotingInProgress()) {
	    	MySession.vote(points);
	    	label.setText(getLabel(MySession.getMyPoints()));
	    	Notification.show(getLabel(points), 1500, Position.MIDDLE);
    	} else {
    		Notification.show("Voting hasn't started yet or already ended.", 1500, Position.MIDDLE);
    	}
    }
    
    private String getLabel(int points) {
    	return "You voted " + points + " points.";
    }

}
