package test;

import boundaryclasses.IOpticalSignals;

public class OpticalSignalsStub implements IOpticalSignals {

    private final int ON = 1;
    private final int OFF = 0;
    
    private int lampA;
    private int lampB;
    
	@Override
	public void switchLampAOn() {
	    lampA = ON;
	}

	@Override
	public void switchLampAOff() {
	    lampA = OFF;
	}

	@Override
	public void switchLampBOn() {
	    lampB = ON;
	}

	@Override
	public void switchLampBOff() {
	    lampB = OFF;
	}

}
