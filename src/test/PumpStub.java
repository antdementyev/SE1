package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {

    HumiditySensorStub sensor;
    private final int TO_ACTIVATE = -1;
    private final int TO_DEACTIVATE = 1;
    private int signal;
    private int activatingMessageCounter;
    private int deactivatingMessageCounter;
    private static char pumpTyp = 'A';
    private char id ;
    
    
    public PumpStub(HumiditySensorStub sensor) {
        this.sensor = sensor;
        id = pumpTyp;
        pumpTyp++;
    }
    
	@Override
	public void sendActivate() {
	    signal = TO_ACTIVATE;
	    System.out.println("activate pump " + id);
	    sensor.reduceHumidity(25);;   //simuliere Wirkung auf Feuchtigkeit
	    activatingMessageCounter++;
	}

	@Override
	public void sendDeactivate() {
        signal = TO_DEACTIVATE;
        System.out.println("deactivate pump " + id);
        deactivatingMessageCounter++;
	}

	@Override
	public boolean receivedActivated() {
		return signal == TO_ACTIVATE;
	}

    @Override
    public boolean isActivated() {
        return signal == TO_ACTIVATE;
    }

    public int getActivatingMessageCounter() {
        return activatingMessageCounter;
    }

    public int getDeactivatingMessageCounter() {
        return deactivatingMessageCounter;
    }    
    
}
