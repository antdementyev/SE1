package test;

import static org.junit.Assert.*;
import fsm.IFSM;
import implementation.FSMImplementation;
import implementation.HumidityOkay;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Framework for testing the FSM from Practice 3
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
public class FSMImplementationTest {
	private PumpStub pumpA;
	private PumpStub pumpB;
	private GateStub gate;
	private OpticalSignalsStub signals;
	private HumiditySensorStub sensor;
	private HumidifierStub humidifier;
	private ManualControlStub operatorPanel;
	private IFSM uut;
	private TimerStub timer;

	@Before
	public void testSetup(){
		gate = new GateStub();
		signals = new OpticalSignalsStub();
		sensor = new HumiditySensorStub();
	    pumpA = new PumpStub(sensor);
	    pumpB = new PumpStub(sensor);
		humidifier = new HumidifierStub();
		operatorPanel = new ManualControlStub();
		timer = new TimerStub();
		uut = new FSMImplementation(pumpA,  pumpB,  gate,  signals,
				humidifier,  sensor,  operatorPanel, timer);
	}
	
	@Test
	public void testPath() {
	    uut.evaluate();
        assertEquals(1, gate.getClosingMessageCounter());
        assertEquals(1, gate.getOpeningMessageCounter());
        assertEquals(2, signals.getLampBOnMessageCounter());
        assertEquals(2, signals.getLampBOffMessageCounter());
        assertEquals(1, pumpA.getActivatingMessageCounter());
        assertEquals(1, pumpA.getDeactivatingMessageCounter());
        assertEquals(1, pumpB.getActivatingMessageCounter());
        assertEquals(1, pumpB.getDeactivatingMessageCounter());
        assertEquals(HumidityOkay.getInstance(), uut.getState());  
	}

}
