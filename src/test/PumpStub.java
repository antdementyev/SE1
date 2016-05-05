package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {

    private final int TO_ACTIVATE = -1;
    private final int TO_DEACTIVATE = 1;
    
    private int signal;
    
	@Override
	public void sendActivate() {
	    signal = TO_ACTIVATE;
	    System.out.println("activate pump");
	}

	@Override
	public void sendDeactivate() {
        signal = TO_DEACTIVATE;
        System.out.println("deactivate pump");
	}

	@Override
	public boolean receivedActivated() {
		return signal == TO_ACTIVATE;
	}

}
