package implementation;

import fsm.HumidityState;
import implementation.FSMImplementation;

public class HumidityLow implements HumidityState{

	private static HumidityLow instance = null;
	
	
	private  HumidityLow() {
		
	}

	public static HumidityLow getInstance(){
		if(instance == null){
			instance = new HumidityLow();
		}
		return instance;
	}
	@Override
	public void evaluate(FSMImplementation system) {
		system.getHumidifier().sendSprayOn();
		system.getSignals().switchLampAOn();
		
	}
	
	
}
