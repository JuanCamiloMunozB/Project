package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

	private static final int MAX_PROYECTS = 10;
	private int projectCounter;
	private Project[] projects;

	public Controller() {

		projects = new Project[MAX_PROYECTS];
	
	}
	
	public boolean isRegistrableProyect(){
		if(projectCounter == MAX_PROYECTS){
			return false;
		}else{
			return true;
		}
	}


	public boolean registerProject(String projectName, String clientName, String type, Calendar initialDate, Calendar finalDate, double budget){
		
		Project project = new Project(projectName, clientName, type, initialDate, finalDate, budget);
		int pos = getFirstValidPosition();
		if(pos != -1){
			projects[pos]= project;
		}
		projectCounter++;

		return false;
	}

	public int getFirstValidPosition(){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < MAX_PROYECTS && !isFound; i++){
			if(projects[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
	}

	
	public String searchProjectsAfterDate(Calendar date) {

		String msg = ("There is no proyect that ends before: "+date.getTime());

		for(int i = 0; i<MAX_PROYECTS; i++){
			if(projects[i] != null){
				if(date.compareTo(projects[i].getFinalDate())>0){
					msg += ("Proyect's name: "+projects[i].getName());
				}
			}
		}

		return msg;

	}
	
	
	public String searchProjectsBeforeDate(Calendar date) {

		String msg = "There is no proyect that starts after: "+date.getTime();

		for(int i = 0; i<MAX_PROYECTS; i++){
			if(projects[i] != null){
				if(date.compareTo(projects[i].getFinalDate())<0){
					msg += ("Proyect's name: "+projects[i].getName());
				}
			}
		}

		return msg;

	}
}
