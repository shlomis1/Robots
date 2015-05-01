
//RV2 robot's type
public class RV2 extends RV1
{
	public RV2()//default C'tor
	{
		speedLimit=20;// 0..20 forward speed, -20..0 backward speed
		headingLimit=40;// 0..40 degrees to the right, -40..0 to the left
	}	
}
