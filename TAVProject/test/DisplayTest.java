package test;

import main.Display;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DisplayTest {

    Display display;

    @Before
    public void setUp() {
        display = new Display();
    }

    /*
    * Test value conversion.
    *
    * preconditions: The received speed and angle.
    * postcondition: The received data is displayed on the screen.
    * */
    @Test
    public void setSpeedAngleTest() {

        double speed = -15;
        double angle = 15;
        String binary = "/10001111,00001111,1*";

        display.setSpeedAngle(speed, angle, binary);

        String expectedSpeed = "" + speed;
        String expectedAngle = "" + angle;
        String expectedBinary = "" + binary;
        
        String actualSpeed = display.getSpeed();
        String actualAngle = display.getAngle();
        String actualBinary = display.getReceiveBinary();

        Assert.assertEquals(expectedSpeed, actualSpeed);
        Assert.assertEquals(expectedAngle, actualAngle);
        Assert.assertEquals(expectedBinary, actualBinary);

    }

    /*
    * Test value conversion.
    *
    * preconditions: The fetched sensor data.
    * postcondition: The fetched data is displayed on the screen.
    * */
    @Test
    public void getSensorDataTest() {

        String torque = "-10.0";
        String ir = "10.0";
        String ultra = "10.0";
        String packet = "/10001010,00001010,00001010,1*";

        display.setTorque(torque);
        display.setIR(ir);
        display.setUltra(ultra);
        display.setSentBinary(packet);
        
        String actualTorque = "" + display.getTorque();
        String actualIr = "" + display.getIR();
        String actualUltra = "" + display.getUltra();
        String actualPacket = "" + display.getSentBinary();
        
        Assert.assertEquals(torque, actualTorque);
        Assert.assertEquals(ir, actualIr);
        Assert.assertEquals(ultra, actualUltra);
        Assert.assertEquals(packet, actualPacket);

    }

    @Test
    public void getSensorDataWrongInputTest() {

        String torque = "Bea2";
        String ir = "241a";
        String ultra = "g";
        String packet = "/10000001,10000001,10000001,0*";

        display.setTorque(torque);
        display.setIR(ir);
        display.setUltra(ultra);
        display.setSentBinary(packet);

        String actualTorque = "" + display.getTorque();
        String actualIr = "" + display.getIR();
        String actualUltra = "" + display.getUltra();
        String actualPacket = "" + display.getSentBinary();

        Assert.assertEquals("-1.0", actualTorque);
        Assert.assertEquals("-1.0", actualIr);
        Assert.assertEquals("-1.0", actualUltra);
        Assert.assertEquals(packet, actualPacket);


    }

    /* TESTS FOR HELPER METHOD stringIsDouble()*/
    @Test
    public void testIsDoubleTrue() {

        boolean expected = true;
        boolean actual = display.stringIsDouble("42.5");

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testIsDoubleFalse() {

        boolean expected = false;
        boolean actual = display.stringIsDouble("eaj");

        Assert.assertEquals(expected, actual);

    }
    
}
