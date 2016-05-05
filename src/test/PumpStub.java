package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {

    private final int TO_ACTIVATE = -1;
    private final int TO_DEACTIVATE = 1;
    private int signal;
    private int activatingMessageCounter;
    private int deactivatingMessageCounter;
    
	@Override
	public void sendActivate() {
	    signal = TO_ACTIVATE;
	    System.out.println("activate pump");
	    activatingMessageCounter++;
	}

	@Override
	public void sendDeactivate() {
        signal = TO_DEACTIVATE;
        System.out.println("deactivate pump");
        deactivatingMessageCounter++;
	}

	@Override
	public boolean receivedActivated() {
		return signal == TO_ACTIVATE;
	}

    @Override
    public boolean isActivated() {
        return receivedActivated();
    }

    public int getActivatingMessageCounter() {
        return activatingMessageCounter;
    }

    public int getDeactivatingMessageCounter() {
        return deactivatingMessageCounter;
    }    
    
}
