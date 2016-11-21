/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

/**
 *
 * @author masud
 */
import java.util.Random;


public class ZeroOneExchange 
{
	private static Random random = new Random(System.currentTimeMillis());
	public static Route apply(Route rt) 
	{
		
		Route route = new Route (rt);
		
		int n = route.n;
		int rand = random.nextInt(n);
		int c = route.route.remove(rand);

		//rt.print();		
		//route.print();

		int rand2 = rand;
		while(rand2==rand)rand2=random.nextInt(n);
		
		route.route.add(rand2,c);
		
		//rt.print();
		//route.print();

		route.updateCost();
		return route;
	}
}

