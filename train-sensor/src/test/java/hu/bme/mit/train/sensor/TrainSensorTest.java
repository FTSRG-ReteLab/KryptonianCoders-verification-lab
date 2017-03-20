package hu.bme.mit.train.sensor;

import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainUser mockTU;
	TrainController stubTC;
	TrainSensorImpl sensor;
	
    @Before
    public void before() {
        mockTU = mock(TrainUser.class);
        stubTC = mock(TrainController.class);
        sensor = new TrainSensorImpl(stubTC, mockTU);
    }

    
    @Test
    public void SetNormalSpeedLimit_Sensor_NoAlarm() {
    	when(stubTC.getReferenceSpeed()).thenReturn(250);
    	
    	sensor.overrideSpeedLimit(250);
    	
    	verify(mockTU, times(0)).setAlarmState(true);
    }
    
    @Test
    public void SetHighSpeedLimit_Sensor_DoAlarm() {
    	when(stubTC.getReferenceSpeed()).thenReturn(450);
    	
    	sensor.overrideSpeedLimit(550);
    	
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    
    @Test
    public void SetLargeRelativeMargin_Sensor_DoAlarm() {
    	when(stubTC.getReferenceSpeed()).thenReturn(150);
    	
    	sensor.overrideSpeedLimit(50);
    	
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void SetSmallRelativeMargin_Sensor_NoAlarm() {
    	when(stubTC.getReferenceSpeed()).thenReturn(150);
    	
    	sensor.overrideSpeedLimit(100);
    	
    	verify(mockTU, times(0)).setAlarmState(true);
    }
}
