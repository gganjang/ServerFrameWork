package jun.ServerFramework;

import server.Server;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //initiate server class
    	Thread t = new Thread(new Server("4321"));
    	t.start();
    }
}
