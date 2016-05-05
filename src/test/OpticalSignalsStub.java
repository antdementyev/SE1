package test;

import boundaryclasses.IOpticalSignals;

public class OpticalSignalsStub implements IOpticalSignals {

    private final int ON = 1;
    private final int OFF = 0;
    private int lampA;
    private int lampB;
    private int lampAOnMessageCounter;
    private int lampAOffMessageCounter;
    private int lampBOnMessageCounter;
    private int lampBOffMessageCounter;
    
    
	@Override
	public void switchLampAOn() {
	    lampA = ON;
	    System.out.println("Lamp A on");
	    lampAOnMessageCounter++;
	}

	@Override
	public void switchLampAOff() {
	    lampA = OFF;
        System.out.println("Lamp A off");
        lampAOffMessageCounter++;
	}

	@Override
	public void switchLampBOn() {
	    lampB = ON;
	    System.out.println("Lamp B on");
	    lampBOnMessageCounter++;
	}

	@Override
	public void switchLampBOff() {
	    lampB = OFF;
	    System.out.println("Lamp B off");
	    lampBOffMessageCounter++;
	}

    @Override
    public boolean isLampAOn() {
        return lampA == ON;
    }

    @Override
    public boolean isLampBOn() {
        return lampB == ON;
    }

    public int getLampAOnMessageCounter() {
        return lampAOnMessageCounter;
    }

    public int getLampAOffMessageCounter() {
        return lampAOffMessageCounter;
    }

    public int getLampBOnMessageCounter() {
        return lampBOnMessageCounter;
    }

    public int getLampBOffMessageCounter() {
        return lampBOffMessageCounter;
    }


}
