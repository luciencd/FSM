package FSM;

import static org.junit.Assert.*;

import org.junit.Test;

public class test {

	@Test
	public void test() {
		fsm f = new fsm();
		
		
		
		f.addState("l",false);
		f.addState("u",true);
		f.setStart("l");
	

		
		f.addTransition("l", "u", "c");
		f.addTransition("l", "l", "p");
		
		f.addTransition("u", "l", "p");
		f.addTransition("u", "u", "c");
		
		System.out.println(f.accept(""));
	}

}
