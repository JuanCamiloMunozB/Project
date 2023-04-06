package ui;

import java.util.Scanner;
import model.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import java.text.ParseException;

public class Main{

	private Scanner reader;
	private Controller controller;
	private SimpleDateFormat format;

	public Main() {

		reader = new Scanner(System.in);
		controller = new Controller();
		format = new SimpleDateFormat("dd/M/yy");
	}

	public static void main(String[] args) {

		Main exe = new Main();
		int answer; 

		do{
			exe.menu();
			answer = exe.validateIntegerInput();
			exe.executeOption(answer);
		}while(answer != 0);
	}

	// Incomplete
	public void menu() {
		System.out.println("\n---Menu---");
		System.out.println("0. Exit");
		System.out.println("1. Register new Project");
		System.out.println("2. Consult Projects that end before a certain date");
		System.out.println("3. Consult projects that start after a date");
	}

	public void executeOption(int ans){
		switch(ans){
			case 0:
				System.out.println("Thanks for using our app!");
			break;

			case 1:
				registerProject();
			break;

			case 2:
				searchProjectsAfterDate();
			break;

			case 3:
				searchProjectsBeforeDate();
			break;

			default:
			System.out.println("Invalid option. Please, try again. ");
			break;
		}
	}

	//Incomplete
	public void registerProject(){
		String projectName;
		String clientName;
		String type;
		Calendar initialDate;
		Calendar finalDate;
		double budget;

		boolean isProyectAvailable = controller.isRegistrableProyect();

		if(isProyectAvailable == false){
			System.out.println("The maximun amount of proyects posible has alredy been reached");
		}else{
			System.out.print("Type the project's name: ");
			projectName = reader.next();

			System.out.print("Type the client's name: ");
			clientName = reader.next();

			type = validateProjectsType("\nType the project's type ");

			initialDate = readDate("\nType the start date of the proyect");
			finalDate = readDate("\nType the end date of the proyect");
			
			System.out.print("\nType the project's budget: ");
			budget = reader.nextDouble();

			boolean isProjecRegistered = controller.registerProject(projectName, clientName, type, initialDate, finalDate, budget);

			if(isProjecRegistered == true){
				System.out.println("The project was registered succesfully");
			}
		}
	}


	public void searchProjectsAfterDate() {

		Calendar deadLine = readDate("Enter the deadline");
		System.out.println(controller.searchProjectsAfterDate(deadLine));		
	}
	
	
	public void searchProjectsBeforeDate() {
		Calendar foreCast = readDate("Enter the Forecasts");

		System.out.println(controller.searchProjectsBeforeDate(foreCast));
	}


	public void typeMenu(){
		System.out.println("-valid options:");
		System.out.println("1. Development");
		System.out.println("2. maintenance");
		System.out.println("3. Deployment");
	}

	public String validateProjectsType(String msg){

		String type = "";
		int answ = 0;

		do{
			System.out.println(msg);
			typeMenu();
			System.out.print(":");
			answ = validateIntegerInput();

			if(answ<1 || answ>3){
				System.out.println("Invalid Option. Please, try again");
			}else{
				switch(answ){
					case 1:
					type = "Development";
					break;

					case 2:
					type = "Maintenance";
					break;

					case 3:
					type = "Deployment";
				}
			}
		}while(type == "");
		return type;
	}

	public Calendar readDate(String msg){
		
		Calendar calendarTime = Calendar.getInstance();
		String date = "";
		boolean isDateSet;

		do{
			isDateSet = false;
			System.out.println(msg); 
			System.out.println("The date must follow the format: dd/M/yyyy");
			System.out.print("Type the date: ");
			date = reader.next();
			
			try {
				calendarTime.setTime(format.parse(date));
				isDateSet= true;
			} catch (ParseException error) {
				System.out.println("Invalid option. Please, try again");
			}
		}while(isDateSet == false);
		
		return calendarTime;
	}

	public int validateIntegerInput(){
        int option; 
        if(reader.hasNextInt()){
            option = reader.nextInt();
        }
        else{
            reader.nextLine();
			option = -1;
            System.out.print("Enter an intenger value. "); 
        }
        return option; 
    }
	
}
