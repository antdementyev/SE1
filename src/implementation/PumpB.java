package implementation;

import boundaryclasses.IPump;

public class PumpB implements IPump {

	private boolean activated;
	
	private PumpB(){
		activated = false;
	}

	
	@Override
	public void sendActivate() {
		activated = true;

	}

	@Override
	public void sendDeactivate() {
		activated = false;

	}

	@Override
	public boolean receivedActivated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActivated() {
		
		return activated;
	}

}
