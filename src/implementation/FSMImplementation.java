 package implementation;
import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import boundaryclasses.ITimer;
import fsm.IFSM;


public class FSMImplementation implements IFSM {
	private FSMState state;
	private IPump pumpA;
	private IPump pumpB;
	private IGate gate;
	private IOpticalSignals signals;
	private IHumiditySensor sensor;
	private IHumidifier humidifier;
	private IManualControl operatorPanel;
	private ITimer timer;
	private final double upperBound;
	private final double lowerBound;

	public FSMImplementation( IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals,
			IHumidifier humidifier, IHumiditySensor sensor, IManualControl operatorPanel, ITimer timer) {
		this.state = FSMState.HUMIDITY_OKAY;
		this.pumpA = pumpA;
		this.pumpB = pumpB;
		this.gate = gate;
		this.signals = signals;
		this.sensor = sensor;
		this.humidifier = humidifier;
		this.operatorPanel = operatorPanel;
		this.timer = timer;
		upperBound = 60;
		lowerBound = 20;
	}
	
	@Override
	public FSMState getState() {
	    return state;
	}

	@Override
	public void evaluate() {
		double humidity = sensor.getHumidity();
		if (humidity < lowerBound) {
		    // Zu trocken
		    state = FSMState.HUMIDITY_LOW;
		    humidifier.sendSprayOn();
		    signals.switchLampAOn();
		} else if (humidity >= lowerBound && humidity <= upperBound
		            && state == FSMState.HUMIDITY_LOW) {
		    // wieder ok
		    state = FSMState.HUMIDITY_OKAY;
		    humidifier.sendSprayOff();
            signals.switchLampAOff();
		} else if (humidity > upperBound) {
		    // zu feucht
		    state = FSMState.HUMIDITY_HIGH;
		    gate.sendCloseGate();
		    signals.switchLampBOn();
		    while (!gate.isClosed()) {
		        // warte auf Rückmeldung vom Tor
		    }
		    signals.switchLampBOff();
		    if (activatePumps()) {
		        // Pumpen sind erfolgreich aktiviert
		        while (sensor.getHumidity() > upperBound) {
		            // warte bis Feuchtigkeit sich stabilisiert
		        }
		        pumpA.sendDeactivate();
		        pumpB.sendDeactivate();
		        gate.sendOpenGate();
		        signals.switchLampBOn();
		        while (!gate.isOpen()) {  // warte auf Rückmeldung vom Tor
	            }
		        signals.switchLampBOff();
		        state = FSMState.HUMIDITY_OKAY;
		    } else {
		        // Probleme beim Aktivieren der Pumpen
		        pumpA.sendDeactivate();
                pumpB.sendDeactivate();
                gate.sendOpenGate();
		        state = FSMState.ERROR;
		        while (!operatorPanel.receivedAcknowledgement()) {    
		            // warte auf manuelle Bestätigung
		        }
		        state = FSMState.HUMIDITY_HIGH;
		    }		    
		}
	}
	
	/**
	 * Aktiviert Zu- und Abluftpumpen 
	 * @return true, wenn Aktivierung war erfolgreich während 5 Sekunden
	 */
	private boolean activatePumps() {
	    timer.startTime(5);
	    pumpA.sendActivate();
	    pumpB.sendActivate();
	    while(!timer.isTimerExpired()) {
	        if (pumpA.isActivated() && pumpB.isActivated()) {
	            return true;
	        }
	    }
	    return false;
	}
}
