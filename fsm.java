package FSM;

import java.util.HashMap;

public class fsm {
	private HashMap<String,State> states = new HashMap<String,State>();
	
	public String start; 
	public State startState;

	public fsm() {
	}
	
	public void setStart(String s){
		start = s;
		startState = getState(s);
	}
	
	public void addState(String source,boolean accept){
		State s = new State(source,accept);
		states.put(source,s);
	}
	
	public void addTransition(String source, String target, String condition){
		State s = getState(source);
		State t = getState(target);
		
		Transition tran = new Transition(s,t,condition);
		s.addTransition(condition, tran);
	}
	
	public State getState(String s) throws RuntimeException{
		
		if(!states.containsKey(s)){
			throw new RuntimeException("no state with val "+s);
		}else{
			return states.get(s);
		}
	}
	
	public boolean accept(String input){
		
		int i = 0;
		State next = startState;
		//next = startState.getTarget(input.substring(0,1));
		while(i < input.length()){
			 next = next.getTarget(input.substring(i,i+1));
			 i++;
		}
		//System.out.println(startState.accept());
		return next.accept();
	}
	
	private class State {
		HashMap<String,Transition> transitions;
		private final String value;
		private boolean accept;
		
		public State(String val,boolean acc){
			value = val;
			accept = acc;
			transitions = new HashMap<String,Transition>();
		}
		public String getValue(){
			return new String(value);
		}
		public boolean accept(){
			return accept;
		}
		
		public void addTransition(String s, Transition t){
			
			transitions.put(s,t);
		}
		public State getTarget(String s) throws RuntimeException{
			
			try{
				return transitions.get(s).getTarget();
			} catch (Exception e){
				throw new RuntimeException(getValue()+" no transition of "+s);
			}
		}	
	}
	
	private class Transition {
		private final State source;
		private final State target;
		private final String transition;
		
		public Transition(State s, State t, String transition_){
			source = s;
			target = t;
			transition = transition_;
		}
		
		public String value(){
			return new String(transition);	
		}
		
		public State getTarget(){
			return target;
		}	
	}
}
