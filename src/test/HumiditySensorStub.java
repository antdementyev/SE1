package test;

import boundaryclasses.IHumiditySensor;

public class HumiditySensorStub implements IHumiditySensor {
    
    private double humidity = 100;
    
	@Override
	public double getHumidity() {
	    System.out.println("Humidity " + humidity);
	    double hum = humidity;
	    humidity -= 50;
		return hum;
	}

}
