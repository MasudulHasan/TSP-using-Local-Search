/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Two_Opt 
{

	/**
	 * improves the route by repeatedly applying the first better 2-opt move
	 * @param individual
	 * @param period
	 * @param vehicle
	 * @return false if cost is not decreased
 	 */
	public static Route apply(Route rt)
	{
		Route routeObject = new Route (rt);

		double best_distance = routeObject.getCost();

		ArrayList<Integer> route = routeObject.route;
		for (int i = 0; i < route.size(); i++) 
		{
			for (int k = i + 1; k < route.size(); k++) 
			{
				Route newRouteObject = twoOptSwap(routeObject, i, k);
				double new_distance = newRouteObject.getCost();
				//System.out.println(new_distance);
				if (new_distance < best_distance) 
				{
					return newRouteObject;
				}
			}
		}

		return routeObject;
	}
	
	public static Route twoOptSwap(Route routeobject, int i, int k) 
	{
		Route newRouteObject = new Route(routeobject.tsp);
		ArrayList<Integer> route = routeobject.route;
		
		ArrayList<Integer> new_route = newRouteObject.route;
		new_route.clear();


		for(int cur=0;cur <= i-1;cur++)
		{
			new_route.add(route.get(cur));
		}
		
		
		for(int cur=k;cur >= i;cur--)
		{
			new_route.add(route.get(cur));
		}
		
	
		for(int cur=k+1;cur < route.size();cur++)
		{
			new_route.add(route.get(cur));
		}
		
		newRouteObject.updateCost();
		return newRouteObject;
		
	    
	}
	
}

