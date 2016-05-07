package test;

import boundaryclasses.IHumidifier;

public class HumidifierStub implements IHumidifier {
    
    int spray;
    
	@Override
	public void sendSprayOn() {
	    spray = 1;
		System.out.println("Spray on");
	}

	@Override
	public void sendSprayOff() {
	    spray = 0;
        System.out.println("Spray off");
	}

    @Override
    public boolean isSprayOn() {
        return spray == 1;
    }

}
