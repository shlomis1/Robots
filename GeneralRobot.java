//This class is kind of a link from the interface Robot to the other robots 
public abstract class GeneralRobot implements Robot 
{
	protected Position start,current; // starting and current positions
	protected int speed,heading; //speed as radius, heading as alpha
	protected int speedLimit;//Limit of speed
	protected int headingLimit;//Limit of heading
	protected String name;//The name of the robot

	public GeneralRobot() //Default C'tor
	{
		start=new Position(0,0);
		current=new Position(0,0);
		speed=0;
		heading=0;
	}
	
	//Responsible for correct values for heading and speed
	protected abstract int limitHeading(int hd);
	protected abstract int limitSpeed(int sp);
	
	public String getName() { return name; }
	
	public void setName(String name){ this.name=name; }
	
	//Init the Robot at start
	public void setStartingPosition(Position p) 
	{
		start.x=p.x;
		start.y=p.y;
		current.x=p.x;
		current.y=p.y;
	 }
	
	//Move the Robot according to the arguments and limits
	public void move(int speed, int heading) 
	{
		this.speed=limitSpeed(speed);//there isn't acceleration
		//check how much can the robot proceed from his current heading 
		this.heading+=limitHeading(heading-this.heading);
	 }
	
	public void stop() { speed=0; }
	
	public Position getCurrentPosition() { return current; }
	
	//actually move the Robot to other position in the space
	public void act()
	{
		double temp=fixHeading(heading);//heading in Radians
		current.x=(int)Math.round(current.x+(speed*Math.cos(temp)));
		current.y=(int)Math.round(current.y+(speed*Math.sin(temp)));
	}
	
	//return the Radian's value of the heading
	private double fixHeading(int hd) { return (Math.toRadians(90-hd)); }
	
	//return the distance from start to current position
	public int getDistance()
	{
		return (int)Math.round(Math.sqrt((Math.pow(start.x-current.x, 2))+(Math.pow(start.y-current.y, 2))));
	}
	
	//implements of the Comparable, according to names
	public int compareTo(Robot r)
	{
		return (this.getName().compareTo(r.getName()));
	}
}
	
