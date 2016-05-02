package test;

import boundaryclasses.IHumiditySensor;

public class HumiditySensorStub implements IHumiditySensor {

    private double humidity;
    
	@Override
	public double getHumidity() {
		return humidity;
	}

}
