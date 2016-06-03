package implementation;

import fsm.HumidityState;

public class ErrorState implements HumidityState {

private static ErrorState instance = null;
	
	
	private  ErrorState() {
		
	}

	public static ErrorState getInstance(){
		if(instance == null){
			instance = new ErrorState();
		}
		return instance;
	}
	@Override
	public void evaluate(FSMImplementation system) {
		while (!system.getOperatorPanel().receivedAcknowledgement()) {    
            // warte auf manuelle Bestätigung
        }
        //state = FSMState.HUMIDITY_HIGH;
        system.setState(HumidityHigh.getInstance());
		
	}
	
}
