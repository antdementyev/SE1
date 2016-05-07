package test;

import boundaryclasses.IOpticalSignals;

public class OpticalSignalsStub implements IOpticalSignals {

    private int lampAOnMessageCounter;
    private int lampAOffMessageCounter;
    private int lampBOnMessageCounter;
    private int lampBOffMessageCounter;
    
    
	@Override
	public void switchLampAOn() {
	    System.out.println("Lamp A on");
	    lampAOnMessageCounter++;
	}

	@Override
	public void switchLampAOff() {
        System.out.println("Lamp A off");
        lampAOffMessageCounter++;
	}

	@Override
	public void switchLampBOn() {
	    System.out.println("Lamp B on");
	    lampBOnMessageCounter++;
	}

	@Override
	public void switchLampBOff() {
	    System.out.println("Lamp B off");
	    lampBOffMessageCounter++;
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
