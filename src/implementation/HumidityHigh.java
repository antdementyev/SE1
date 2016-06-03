package implementation;

import fsm.HumidityState;
import implementation.FSMImplementation;


public class HumidityHigh implements HumidityState{

	private static HumidityHigh instance = null;
	
	
	private  HumidityHigh() {
		
	}

	public static HumidityHigh getInstance(){
		if(instance == null){
			instance = new HumidityHigh();
		}
		return instance;
	}
	@Override
	public void evaluate(FSMImplementation system) {
		system.getSignals().switchLampBOn();
		system.getGate().sendCloseGate();

        while (!system.getGate().isClosed()) {
            // warte auf Rückmeldung vom Tor
        }
        system.getSignals().switchLampBOff();
        if (system.activatePumps()) {
            // Pumpen wurden erfolgreich aktiviert
            while (system.getSensor().getHumidity() > system.getUpperBound()) {
                // warte bis Feuchtigkeit sich stabilisiert
            }
            system.getPumpA().sendDeactivate();
            system.getPumpB().sendDeactivate();
            system.getSignals().switchLampBOn();
            system.getGate().sendOpenGate();
            while (!system.getGate().isOpen()) {  
                // warte auf Rückmeldung vom Tor
            }
            system.getSignals().switchLampBOff();
            //state = HumidityOkay.getInstance();
            system.setState(HumidityOkay.getInstance());
        } else {
            // Probleme beim Aktivieren der Pumpen
        	system.getPumpA().sendDeactivate();
        	system.getPumpB().sendDeactivate();
        	system.getGate().sendOpenGate();
            //state = FSMState.ERROR;
            system.setState(ErrorState.getInstance());
        }      
		
	}

}
