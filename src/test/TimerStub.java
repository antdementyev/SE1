package test;

import boundaryclasses.ITimer;

public class TimerStub implements ITimer {

    private double duration;
    private long startTime;
    
	@Override
	public void startTime(double seconds) {
	    duration = seconds;
	    startTime = System.nanoTime();
	}

	@Override
	public boolean isTimerExpired() {
	    return (System.nanoTime() - startTime)/1E9 >= duration;
	}

}
