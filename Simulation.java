import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

//Our simulation for all the robots
public class Simulation 
{
	//our chosen DS for Holding the Robots by name
	private HashMap<String,Robot> robotHM;
	
	public Simulation()//Default C'tor
	{
		robotHM=new HashMap<String,Robot>();//Init
	}
	
	public Robot getRobot(String name) throws Exception
	{
		if (checkName(name))//Valid name
			return (robotHM.get(name));
		//Not Valid, throw Exception
		throw new Exception("Error: no such robot with this name.");
	}
	
	//Check if the given name is appears in the DS
	public boolean checkName(String name)
	{
		return (robotHM.containsKey(name));
	}
	
	//add another robot to the DS
	public int addRobot(String name,Robot rv)
	{
		robotHM.put(name, rv);//adding
		rv.setName(name);//sets its name
		return (robotHM.size());//return the size of the DS so far
	}
	
	//Print by sorted distances
	public void printDistance()
	{
		ArrayList<Robot> ar=new ArrayList<Robot>();
		Iterator<Robot> it;
		int i=1;//started index
		
		ar.addAll(robotHM.values());//adding all
		Collections.sort(ar, new DistanceCompare());//sort by distance
		it=ar.iterator();
		while (it.hasNext())
		{
			Robot r=it.next();
			System.out.println(i+". "+r.getName()+" distance "+r.getDistance());
			i++;
		}
	}
	
	//print by sorted names
	public void printName()
	{
		ArrayList<Robot> ar=new ArrayList<Robot>();
		Iterator<Robot> it;
		int i=1;//started index
		
		ar.addAll(robotHM.values());//adding all
		Collections.sort(ar);//sort by names
		it=ar.iterator();
		while (it.hasNext())
		{
			System.out.println(i+". "+it.next().getName());
			i++;
		}
	}
	
	//sort robots by their distances from start to current position
	private class DistanceCompare implements Comparator<Robot>
	{
		public int compare(Robot r1, Robot r2)
		{
			return Integer.valueOf(r1.getDistance()).compareTo(r2.getDistance());
		}
	}
}
