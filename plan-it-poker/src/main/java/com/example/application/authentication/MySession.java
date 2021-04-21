package com.example.application.authentication;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.example.application.views.people.Member;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinSession;

public class MySession {
	public static void login(String username) {
		VaadinSession session = VaadinSession.getCurrent();
		session.setAttribute("username", username);
		addMember();
	}
	
	public static void logout() {
		VaadinSession session = VaadinSession.getCurrent();
		removeMember();
		session.setAttribute("username", null);
	}
	
	public static boolean isUserLoggedIn() {
		VaadinSession session = VaadinSession.getCurrent();
		if (session.getAttribute("username") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getCurrentUsername() {
		VaadinSession session = VaadinSession.getCurrent();
		if (isUserLoggedIn()) {
			return session.getAttribute("username").toString();
		} else {
			return null;
		}
	}
		
	public static void goToLogin() {
		UI.getCurrent().navigate("login");
	}
	
	
	public static void vote(int points) {
    	ServletContext sc = VaadinServlet.getCurrent().getServletContext();
        
        Member currentMember = getCurrentMember();
        if (currentMember != null) {
        	currentMember.setPoints(points);
        	currentMember.setStatus("Voted");
        } else {
        	Member m = new Member();
            m.setName(getCurrentUsername());
        	m.setPoints(points);
        	m.setStatus("Voted");
        	List<Member> members = getMembers();
        	members.add(m);
        	sc.setAttribute("members", members);
        }
	}
	
	public static void addMember() {
		if (getCurrentMember() == null) {
			Member m = new Member();
            m.setName(getCurrentUsername());
        	m.setPoints(null);
        	m.setStatus("logged in");
        	List<Member> members = getMembers();
        	members.add(m);
        	ServletContext sc = VaadinServlet.getCurrent().getServletContext();
        	sc.setAttribute("members", members);
		}
	}
	
	public static void removeMember() {
		Member me = getCurrentMember();
		if (me != null) {
			List<Member> members = getMembers();
			members.remove(me);
			ServletContext sc = VaadinServlet.getCurrent().getServletContext();
        	sc.setAttribute("members", members);
		}
	}
	
	public static void resetVotes() {
		List<Member> members = getMembers();
		for (Member m : members) {
			m.setPoints(null);
			m.setStatus("logged in");
		}
	}
	
	public static Member getCurrentMember() {
		List<Member> members = getMembers();
		for (Member m : members) {
			if (m.getName().equals(getCurrentUsername())) {
				return m;
			}
		}
		return null;
	}
	
	public static List<Member> getMembers() {
    	ServletContext sc = VaadinServlet.getCurrent().getServletContext();
        List<Member> members = (List<Member>)sc.getAttribute("members");
        return members == null? new ArrayList<Member>() : members;
	}
	
	public static Integer getMyPoints() {
		Member currentMember = getCurrentMember();
		if (currentMember != null) {
			return currentMember.getPoints();
		} else {
			return null;
		}
	}
	
	public static void startVoting() {
		ServletContext sc = VaadinServlet.getCurrent().getServletContext();
		sc.setAttribute("started", "True");
		resetVotes(); 
	}
	
	public static void stopVoting() {
		ServletContext sc = VaadinServlet.getCurrent().getServletContext();
		sc.setAttribute("started", null);
	}
	
	public static boolean isVotingInProgress() {
		ServletContext sc = VaadinServlet.getCurrent().getServletContext();
		return sc.getAttribute("started") != null;
	}
}
