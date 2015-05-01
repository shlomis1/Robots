import java.util.HashMap;

//Our Factory
public class RobotFactory 
{
	//our DS to hold the factories
	private HashMap<String, RobotFac> robotFactory;
	
	public RobotFactory()//Default C'tor
	{
		robotFactory=new HashMap<String, RobotFac>();
		//Init the different types of factories
		add(new RV1Fac());
		add(new RV2Fac());
		add(new AiboFac());
	}
	
	//Help to Init the DS
	private void add(RobotFac rf)
	{
		robotFactory.put(rf.getType(), rf);
	}
	
	//Our base interface
	private interface RobotFac
	{
		public Robot create();//create new robot
		public String getType();//return robot type
	}

	//RV1 type
	private class RV1Fac implements RobotFac
	{
		public Robot create(){ return new RV1();}
		public String getType() { return "RV1"; }
	}

	//RV2 type
	private class RV2Fac implements RobotFac
	{
		public Robot create(){ return new RV2();}
		public String getType() { return "RV2"; }
	}

	//Aibo type
	private class AiboFac implements RobotFac
	{
		public Robot create(){ return new Aibo();}
		public String getType() { return "Aibo"; }
	}
	
	//Responsible for create the different robots
	public Robot createRobot(String type) throws Exception
	{
		if (robotFactory.containsKey(type))//Valid
			return (robotFactory.get(type)).create();
		else//Not Valid robot's type
			throw new Exception("Error: no such robot type.");
	}	  
}
