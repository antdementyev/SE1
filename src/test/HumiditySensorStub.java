package test;

import boundaryclasses.IHumiditySensor;

public class HumiditySensorStub implements IHumiditySensor {
    
    private double humidity = 100;
    
	@Override
	public double getHumidity() {
        System.out.println("Humidity is " + humidity);
		return humidity;
	}

	public void reduceHumidity(double different) {
	    humidity -= different;
	}
	
}
