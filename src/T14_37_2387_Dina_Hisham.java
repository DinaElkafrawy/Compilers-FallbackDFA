import java.util.Scanner;
//Previous Format: Refactor the file name to FDFA and make sure it changes in the main method

class DFA {

	static String[] states;
	static String[] acceptedStates;
	
	public DFA(String description) {
		String[] values = description.split("#");
		
		states = values[0].split(";");
		
		String goal = values[1];
		acceptedStates = goal.split(",");
		
	}
	
	public Boolean run(String string) {
		char[] characters = string.toCharArray();
		
		String[] startState = states[0].split(",");
		String current = startState[0];
		
		for(int i = 0; i < characters.length; i ++) {
			for(int ii =0; ii < states.length; ii++) {
				String[] currentState = states[ii].split(",");

				if(currentState[0].equals(current) && currentState[1].equals(current) && currentState[2].equals(current) ) {
					current = "dead";
					break;
				}
				
				if(currentState[0].equals(current)) {
					if(characters[i] == '0' ) {
						current = currentState[1];
						break;
					}
					else {
						current = currentState[2];
						break;
					}
				}
			}	
		}
		
		if(current.equals("dead"))
			return false;
		
		char[] finish = current.toCharArray();

		for(int j = 0; j < acceptedStates.length; j++) {
			for(int jj = 0; jj < finish.length; jj++) {
				if(finish[jj] == acceptedStates[j].charAt(0))
					return true;
			}
		}
		
		return false;
		
	}
	
	public String[] getState() {
		return states;
	}
	
	public String[] getAccepted() {
		return acceptedStates;
	}
	
}


public class T14_37_2387_Dina_Hisham {
	DFA dfa;
	
	String[] states;
	String[] acceptedStates;
	
	public T14_37_2387_Dina_Hisham(String representation) {
		dfa = new DFA(representation);
		states = dfa.getState();
		acceptedStates = dfa.getAccepted();
	}
	
	public void run(String input) {
		if(input.equals(""))
			return;
		
		char[] characters = input.toCharArray();
		
		String[] startState = states[0].split(",");
		String current = startState[0];
		
		int lastAcceptedIndex = 0;
		String lastAcceptedState = "";
		String lastAction = "";
		
		for(int i = 0; i < characters.length; i ++) {
			for(int j =0; j < states.length; j++) {
				String[] currentState = states[j].split(",");

				if(currentState[0].equals(current) && currentState[1].equals(current) && currentState[2].equals(current) ) {
					current = "dead";
					break;
				}
				
				if(currentState[0].equals(current)) {
					if(characters[i] == '0' ) {
						current = currentState[1];
						
						if(!accepted(current) && i == characters.length - 1 && lastAcceptedState.equals(""))
							lastAction = getAction(current);
						
						else if(accepted(current)) {
							lastAcceptedIndex = i;
							lastAcceptedState = current;
							lastAction = getAction(current);
						}
						
						break;
					}
					else {
						current = currentState[2];
						
						if(!accepted(current) && i == characters.length - 1 && lastAcceptedState.equals(""))
							lastAction = getAction(current);
						
						if(accepted(current)) {
							lastAcceptedIndex = i;
							lastAcceptedState = current;
							lastAction = getAction(current);
						}
						
						break;
					}
				}
			}	
		}
		
		System.out.print(lastAction);
		
		if(!lastAcceptedState.equals(""))
			run(input.substring(lastAcceptedIndex + 1));
	}
	
	public Boolean accepted(String state) {

		char[] finish = state.toCharArray();
	
		if(state.equals("dead"))
			return false;
		
		for(int j = 0; j < acceptedStates.length; j++) {
			for(int jj = 0; jj < finish.length; jj++) {
				if(finish[jj] == acceptedStates[j].charAt(0))
					return true;
			}
		}
		
		return false;
	}
	
	public String getAction(String state) {
		for(int x =0; x < states.length; x++) {
			String[] currentState = states[x].split(",");
			
			if(currentState[0].equals(state))
				return currentState[3];
		}
		
		return "error";
	}
	
	public static void main (String[] args) {
		//FDFA fdfa = new FDFA("0,1,0,00;1,1,2,01;2,1,3,10;3,1,0,11#3");
		//FDFA fdfa = new FDFA("0,0,1,00;1,0,1,11#1");
		T14_37_2387_Dina_Hisham fdfa = new T14_37_2387_Dina_Hisham("0,1,0,00;1,0,2,01;2,0,2,11#1");


		fdfa.run("1011011");
	}
	
}
