
import java.io.IOException;

/*******************************************************************
 * The Program support difference commands and manage different types
 * of robots, each has its own position and commands
 *****************************************************************/
public class Main 
{
	//Input streams from the user, char by char
	private static String readLine() throws IOException
	{
		StringBuffer buffer=new StringBuffer();
		int temp;
		char c = 0;
		
		temp=System.in.read();
		c=(char)temp;
		while(c!='\r')//until end of line
		{	
			if (c!='\n')//as long as it's not enter
				buffer.append(c);
			temp=System.in.read();
			c=(char)temp;
		}
		return (buffer.toString());
	}
	
	//The main Function
	public static void main(String[] args) 
	{
		Simulation sim=new Simulation();//our simulation of robots
		Commands cmd=new Commands(sim);//Out command Object
		boolean exit=false;//flag represent when to quit the prog

		while (exit==false)//as long as exit==false
		{
			try
			{
				//run the runCommand from our command
				exit=cmd.runCommand(readLine());
			}catch(Exception ex)//in case of Exception thrown
			{
				System.out.println(ex.getMessage());
				if (ex.getMessage().equals("bye."))
					exit=true;//only in case of "Exit" command 
			}
		}
	}
}

