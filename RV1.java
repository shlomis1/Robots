
//RV1 robot's type
public class RV1 extends GeneralRobot
{
	public RV1()//Default C'tor
	{
		speedLimit=10;// 0..10 forward speed, -10..0 backward speed
		headingLimit=20;//0..20 degrees to the right, -20..0 to the left
	}
	
	//return a valid heading value according to the limit
	protected int limitHeading(int hd)
	{
		if (Math.abs(hd)>headingLimit)// Not a valid argument
		{
			if (hd<0) return (headingLimit*(-1));
			return headingLimit;
		}else //the received heading is Valid
			return hd;
	}

	//return a valid speed value according to the limit
	protected int limitSpeed(int sp)
	{
		if (Math.abs(sp)>speedLimit)// Not a valid argument
		{
			if (sp<0) return (speedLimit*(-1));
			return speedLimit;
		}else //the received speed is Valid
			return sp;
	}
}
