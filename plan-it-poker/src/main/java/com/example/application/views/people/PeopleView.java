package com.example.application.views.people;



import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.util.List;

import com.example.application.authentication.MySession;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;

@Route(value = "people", layout = MainView.class)
@PageTitle("People")
@CssImport("./views/people/people-view.css")
public class PeopleView extends Div {
	
	private Label label = new Label("");

	private Button startButton = new Button("Start");
	private Button stopButton = new Button("Stop");
	private Button refresh = new Button ("Refresh");
	
	private Grid<Member> grid = new Grid<>(Member.class);

    public PeopleView() {
        addClassName("people-view");
        
        startButton.addClickListener(e -> {
        	MySession.startVoting();
        	Notification.show("Voting started.", 1500, Position.MIDDLE);
        	refreshScreen();
        });
        
        refresh.addClickListener(e -> {
        	refreshScreen();
        });
        
        stopButton.addClickListener(e -> {
        	MySession.stopVoting();
        	Notification.show("Voting ended.", 1500, Position.MIDDLE);

        	refreshScreen();
        });
        
        VerticalLayout vertical = new VerticalLayout();
	    vertical.add(startButton, label, refresh); 

	    grid.setItems(MySession.getMembers());
	    grid.setColumns("name", "status", "points");
	    
	    vertical.add(grid, stopButton);	    
	    
        add(vertical);
        
        refreshScreen();
    }
    
    private void refreshScreen() {
    	grid.setItems(MySession.getMembers());
    	if (MySession.isVotingInProgress()) {
    		grid.getColumnByKey("points").setVisible(false);
            label.setText("Voting is in progress");
    	} else {
    		grid.getColumnByKey("points").setVisible(true);
    		label.setText("Ready to start ");
    	}
    }

};
