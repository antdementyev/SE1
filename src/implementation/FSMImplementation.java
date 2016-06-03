 package implementation;
import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import boundaryclasses.ITimer;
import fsm.HumidityState;
import fsm.IFSM;


public class FSMImplementation implements IFSM {
	private HumidityState state;
	private IPump pumpA;
	private IPump pumpB;
	private IGate gate;
	private IOpticalSignals signals;
	private IHumiditySensor sensor;
	private IHumidifier humidifier;
	
	public IPump getPumpA() {
		return pumpA;
	}


	public IPump getPumpB() {
		return pumpB;
	}


	public IGate getGate() {
		return gate;
	}


	public IOpticalSignals getSignals() {
		return signals;
	}


	public IHumiditySensor getSensor() {
		return sensor;
	}


	public IHumidifier getHumidifier() {
		return humidifier;
	}


	public IManualControl getOperatorPanel() {
		return operatorPanel;
	}


	public ITimer getTimer() {
		return timer;
	}


	public double getUpperBound() {
		return upperBound;
	}


	public double getLowerBound() {
		return lowerBound;
	}

	private IManualControl operatorPanel;
	private ITimer timer;
	private final double upperBound;
	private final double lowerBound;

	public FSMImplementation( IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals,
			IHumidifier humidifier, IHumiditySensor sensor, IManualControl operatorPanel, ITimer timer) {
		this.state = HumidityOkay.getInstance();
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
		state.evaluate(this);

	}
	
	@Override
	public HumidityState getState() {
	    return state;
	}
	
	public void setState(HumidityState state) {
		this.state = state;
	}


	/**
	 * Updates the FSM state based on an actual humidity.
	 */
	private void updateState() {
	    if (state == ErrorState.getInstance()) {
	        // Status bleibt ohne Änderung
	    } else {
	        double humidity = sensor.getHumidity();
	        if (humidity < lowerBound) {
	            state = HumidityLow.getInstance();
	        } else if (humidity >= lowerBound && humidity <= upperBound) {
	            state = HumidityOkay.getInstance();
	        } else {
	            state = HumidityHigh.getInstance();
	        }
	    }
	}
	
	/**
	 * Aktiviert Zu- und Abluftpumpen 
	 * @return true, wenn Aktivierung war erfolgreich während 5 Sekunden
	 */
	protected boolean activatePumps() {
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
