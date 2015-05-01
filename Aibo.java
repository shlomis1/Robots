//Another type of robot, doesn't belong to RV series
public class Aibo extends GeneralRobot
{
	public Aibo() // Default C'tor
	{
		speedLimit=50;//0..50 forward speed, -50..0 backward speed
	}
	
	//without heading limit
	protected int limitHeading(int hd) { return hd; }

	//return a valid speed value according to the limit
	protected int limitSpeed(int sp)
	{
		if (Math.abs(sp)>speedLimit) // Not a valid argument
		{
			if (sp<0) return (speedLimit*(-1));
			return speedLimit;
		}else //the received speed is Valid
			return sp;
	}
}
