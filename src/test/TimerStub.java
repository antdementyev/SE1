package test;

import boundaryclasses.ITimer;

public class TimerStub implements ITimer {

    private double duration;
    private long startTime;
    
	@Override
	public void startTime(double seconds) {
	    duration = seconds;
	    startTime = System.nanoTime();
	    System.out.println("start timer for " + seconds + " seconds");
	}

	@Override
	public boolean isTimerExpired() {
	    System.out.println("timer: " + (System.nanoTime() - startTime)/1E9);
	    return (System.nanoTime() - startTime)/1E9 >= duration;
	}

}
