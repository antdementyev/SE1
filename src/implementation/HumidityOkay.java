package implementation;

import fsm.HumidityState;

public class HumidityOkay implements HumidityState {
	private static HumidityOkay instance = null;
	
	
	private  HumidityOkay() {
		
	}

	public static HumidityOkay getInstance(){
		if(instance == null){
			instance = new HumidityOkay();
		}
		return instance;
	}
	@Override
	public void evaluate(FSMImplementation system) {
            if(system.getHumidifier().isSprayOn()){
            	system.getHumidifier().sendSprayOff();
            	system.getSignals().switchLampAOff();
            }
		
	}

}
