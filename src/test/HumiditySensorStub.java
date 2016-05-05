package test;

import boundaryclasses.IHumiditySensor;

public class HumiditySensorStub implements IHumiditySensor {

    private double humidity;
    
	@Override
	public double getHumidity() {
	    System.out.println("humidity: " + humidity);
		return humidity;
	}

}
