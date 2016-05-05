package test;

import boundaryclasses.IGate;

public class GateStub implements IGate {

    private final int TO_CLOSE = -1;
    private final int TO_OPEN = 1;
    private int signal;
    private int closingMessageCounter;
    private int openingMessageCounter;
    
	@Override
	public void sendCloseGate() {
	    signal = TO_CLOSE;
	    System.out.println("close gate");
	    closingMessageCounter++;
	}

	@Override
	public void sendOpenGate() {
	    signal = TO_OPEN;
	    System.out.println("open gate");
	    openingMessageCounter++;
	}

	@Override
	public boolean receivedGateClosed() {
		return signal == TO_CLOSE;
	}

	@Override
	public boolean receivedGateOpen() {
		return signal == TO_OPEN;
	}

    @Override
    public boolean isClosed() {
        return receivedGateClosed();
    }

    @Override
    public boolean isOpen() {
        return receivedGateOpen();
    }

    public int getOpeningMessageCounter() {
        return openingMessageCounter;
    }

    public int getClosingMessageCounter() {
        return closingMessageCounter;
    }
}
