package test;

import boundaryclasses.IGate;

public class GateStub implements IGate {

    private final int TO_CLOSE = -1;
    private final int TO_OPEN = 1;
    
    private int signal;
    
	@Override
	public void sendCloseGate() {
	    signal = TO_CLOSE;
	}

	@Override
	public void sendOpenGate() {
	    signal = TO_OPEN;
	}

	@Override
	public boolean receivedGateClosed() {
		return signal == TO_CLOSE;
	}

	@Override
	public boolean receivedGateOpen() {
		return signal == TO_OPEN;
	}

}
