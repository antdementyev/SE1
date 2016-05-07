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
	public void evaluate() {
		updateState();
		switch(state) {
		    case HUMIDITY_LOW:
		        humidifier.sendSprayOn();
	            signals.switchLampAOn();
	            break;
		    case HUMIDITY_OKAY:
		        if (humidifier.isSprayOn()) {
		            humidifier.sendSprayOff();
		            signals.switchLampAOff();
		        }
		        break;
		    case HUMIDITY_HIGH:
		        signals.switchLampBOn();
		        gate.sendCloseGate();
	            while (!gate.isClosed()) {
	                // warte auf Rückmeldung vom Tor
	            }
	            signals.switchLampBOff();
	            if (activatePumps()) {
	                // Pumpen wurden erfolgreich aktiviert
	                while (sensor.getHumidity() > upperBound) {
	                    // warte bis Feuchtigkeit sich stabilisiert
	                }
	                pumpA.sendDeactivate();
	                pumpB.sendDeactivate();
	                signals.switchLampBOn();
	                gate.sendOpenGate();
	                while (!gate.isOpen()) {  
	                    // warte auf Rückmeldung vom Tor
	                }
	                signals.switchLampBOff();
	                state = FSMState.HUMIDITY_OKAY;
	            } else {
	                // Probleme beim Aktivieren der Pumpen
	                pumpA.sendDeactivate();
	                pumpB.sendDeactivate();
	                gate.sendOpenGate();
	                state = FSMState.ERROR;
	            }      
	            break;
		    case ERROR:
                while (!operatorPanel.receivedAcknowledgement()) {    
                    // warte auf manuelle Bestätigung
                }
                state = FSMState.HUMIDITY_HIGH;
		}
	}
	
	@Override
	public FSMState getState() {
	    return state;
	}
	
	/**
	 * Updates the FSM state based on an actual humidity.
	 */
	private void updateState() {
	    if (state == FSMState.ERROR) {
	        // Status bleibt ohne Änderung
	    } else {
	        double humidity = sensor.getHumidity();
	        if (humidity < lowerBound) {
	            state = FSMState.HUMIDITY_LOW;
	        } else if (humidity >= lowerBound && humidity <= upperBound) {
	            state = FSMState.HUMIDITY_OKAY;
	        } else {
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
