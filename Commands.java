import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.MatchResult;

//Responsible for all the commands in the prog
public class Commands 
{
	private Simulation sim;
	//the DS to hold the different commands(classes)
	private HashMap<String,Command> commandsMap;
	
	public Commands(Simulation sim) //C'tor
	{
		this.sim=sim;//Init the class member
		commandsMap=new HashMap<String,Command>();//Init
		//using add to Init the DS
		add(new InitCommand());
		add(new MoveCommand());
		add(new SimulateCommand());
		add(new GetCommand());
		add(new ListCommand());
		add(new FilesCommand());
		add(new LoadCommand());
		add(new Exit());
	 }
	
	//method to Init commandsMap
	private void add(Command c) { commandsMap.put(c.getKey(), c); }
	
	//The Base class, the interface
	private interface Command
	{
		//run the specific command
		public boolean execute(String cmd) throws Exception;
		//return the key, first word in command
		public String getKey();
	}
	
	//Responsible for "Files" command
	private class FilesCommand implements Command
	{
		public String getKey() { return ("Files"); }
		
		public boolean execute(String cmd)
		{
			if (cmd.equals("Files")) //Valid
			{
				File dir=new File(".");// Current Path
				String[] dir_list=dir.list();
				//Until the end of the list
				for (int i=0;i<dir_list.length;i++)
				{
					File f=new File(dir_list[i]);
					if (f.isFile())//check for file
						System.out.println("\t[f] "+dir_list[i]);
					if (f.isDirectory())//check for directory
						System.out.println("\t[d] "+dir_list[i]);
				}
				return true;
			}// Not Valid
			return false;
		}
	}
	
	//Responsible for "Load" command
	private class LoadCommand implements Command
	{
		String format="Load (\\S+)"; //the requested pattern
		
		public String getKey() { return ("Load"); }
		
		public boolean execute(String cmd) throws Exception
		{
			if (cmd.matches(format))//Valid
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);
				BufferedReader br;
				try {//try to create BufferedReader object
					br = new BufferedReader( new FileReader(scn.match().group(1)));
				} catch (FileNotFoundException e) {//File not exist
					throw new Exception("Error: file not found.");
				}
				String line;
				//until the end of the file
				while((line=br.readLine())!=null)
				{
					System.out.println(">> "+line);
					try{
						runCommand(line);
						}catch(Exception ex){//in case Exception thrown
							System.out.println(ex.getMessage());
						}
				}//end of loop
				br.close();//close the file
				return true;
			}//Not Valid
			return false;
		}
	}
	
	//Responsible for "Init" command
	private class InitCommand implements Command
	{
		//the desired pattern
		String format="Init type (\\w+) at (-?\\d+) (-?\\d+) named (\\w+)";
		
		public String getKey() { return("Init"); }
		
		public boolean execute(String cmd) throws Exception
		{
			if (cmd.matches(format))//Valid
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);
				MatchResult result=scn.match();
				String type=result.group(1);
				int x=Integer.parseInt(result.group(2));
				int y=Integer.parseInt(result.group(3));
				String name=result.group(4);
				RobotFactory rf=new RobotFactory();
				sim.addRobot(name, rf.createRobot(type));
				sim.getRobot(name).setStartingPosition(new Position(x,y));
				System.out.println("new "+type+" at "+x+","+y+" named "+name);
				return true;
			}//Not Valid
			return false;
		}
	}
	
	//Responsible for "Move" command
	private class MoveCommand implements Command
	{
		//the desired pattern
		String format="Move (\\w+) speed (-?\\d+) heading (-?\\d+)";
		
		public String getKey() { return ("Move"); }
		
		public boolean execute(String cmd) throws Exception
		{
			if (cmd.matches(format))//Valid
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);
				MatchResult result=scn.match();
				String name=result.group(1);
				int speed=Integer.parseInt(result.group(2));
				int heading=Integer.parseInt(result.group(3));
				sim.getRobot(name).move(speed, heading);				
				return true;
			}//Not Valid
			return false;
		}
	}
	
	//Responsible for "Simulate" command
	private class SimulateCommand implements Command
	{
		//the desired pattern
		String format="Simulate (\\w+) (\\d+) steps";
		
		public String getKey() { return("Simulate"); }
		
		public boolean execute(String cmd) throws Exception
		{
			if (cmd.matches(format))//Valid
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);
				MatchResult result=scn.match();
				String name=result.group(1);
				int k=Integer.parseInt(result.group(2));
				for (int i = 0; i<k ;i++)
				{  
					//act k times
			    	sim.getRobot(name).act();
			    }
				return true;
			}//Not Valid
			return false;
		}
	}
	
	//Responsible for "Get" command
	private class GetCommand implements Command
	{
		//the desired pattern
		String format="Get (\\w+) position";
		
		public String getKey() { return("Get"); }
		
		public boolean execute(String cmd) throws Exception
		{
			if (cmd.matches(format))//Valid
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);
				MatchResult result=scn.match();
				String name=result.group(1);
				Position p=sim.getRobot(name).getCurrentPosition();
				System.out.println("robot "+name+" is at "+p.x+","+p.y);
				return true;
			}//Not Valid
			return false;
		}
	}
	
	//Responsible for "List" command
	private class ListCommand implements Command
	{
		//the desired command
		String format="List robots by (distance|name)";

		public String getKey() { return ("List"); }

		public boolean execute(String cmd) throws Exception 
		{
			if (cmd.matches(format))//Valid
			{
				Scanner scn=new Scanner(cmd);
				for (int i=0; i<3 ; i++) scn.next();
				String option=scn.next(); // the final Word
				if (option.equals("distance"))
				{
					//print by Distance
					sim.printDistance();
				}
				if (option.equals("name"))
				{
					//Print by name
					sim.printName();
				}
				return true;
			}//Not Valid
			return false;
		}	
	}
	
	//Responsible for "Exit" command
	private class Exit implements Command
	{
		public String getKey() { return ("Exit"); }
		
		public boolean execute(String cmd) throws Exception 
		{
			if (cmd.equals("Exit"))//Valid
				//throw Exception to the Main
				throw new Exception("bye.");
			return false;//Not Valid
		}
	}
	
	//called by the main to run the different commands
	public boolean runCommand(String cmd) throws Exception
	{
		//gets the first word
		String fw=firstWord(cmd);
		
		if (commandsMap.containsKey(fw))//Valid
		{
			/*
			*as long as the commands are valid return false to Main
			*indicates flag for exit, runs until true..
			*if the commands not valid throw Exception to the Main
			*/
			if (commandsMap.get(fw).execute(cmd))
				return false;
		}//Not Valid, throw Exception to the Main
		throw new Exception("Error: command not found.");
	}

	//return the first word in a string
	private String firstWord(String cmd)
	{
		Scanner scn=new Scanner(cmd);
		
		return (scn.next());
	}
}
