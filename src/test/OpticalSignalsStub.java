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
	    System.out.println("Lamp A on");
	}

	@Override
	public void switchLampAOff() {
	    lampA = OFF;
        System.out.println("Lamp A off");
	}

	@Override
	public void switchLampBOn() {
	    lampB = ON;
	    System.out.println("Lamp B on");
	}

	@Override
	public void switchLampBOff() {
	    lampB = OFF;
	    System.out.println("Lamp B off");
	}

}
