
/*
*our Base interface, including functions shared by all the robots
*in order to sort by name extends from Comparable
*/
public interface Robot extends Comparable<Robot>
{	
	public String getName();//Gets robot name
	public void setName(String name);//Sets robot name
	public void setStartingPosition(Position p);//Init the robot's position
	public void move(int speed,int heading);//Move the robot
	public void stop();//makes him stop
	public Position getCurrentPosition();//gets current position
	public void act();//make an action according to speed and heading
	public int getDistance();//Gets the distance between start and current position
	public int compareTo(Robot r);//To sort by name
}
