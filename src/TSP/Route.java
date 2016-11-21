/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Route 
{
	ArrayList<Integer> route;
	int n;
	double cost;
	TSP tsp;
	
	/**
	 * Initialize a new route and update its cost
	 * @param tsp
	 */
	public Route(TSP tsp) {
		// TODO Auto-generated constructor stub
		this.n = tsp.n;
		this.tsp = tsp;
		route = new ArrayList<Integer>();
		
		initialize();
		updateCost();
	}
	
	/**
	 * Initialize a route with another route, makes a copy.
	 * @param src
	 */
	public Route(Route src)
	{
		this.n = src.n;
		this.tsp = src.tsp;
		
		route = new ArrayList<Integer>();
		
		for (Iterator iterator = src.route.iterator(); iterator.hasNext();) {
			route.add((Integer)iterator.next());
		}
		//this.cost = updateCost();
                this.cost = src.cost;
	}
	
	
	/**
	 * Initializes a solution/route with a random permutation
	 */
	public void initialize() {
		// TODO Auto-generated method stub
		for (int i = 0; i < n; i++) {
			route.add(i);
		}
		
		Collections.shuffle(route);
	}
	
	/**
	 * Prints the route
	 */
	
	public void print() 
	{       System.out.println("Route Printing");
                int Count=0;
		for (Iterator iterator = route.iterator(); iterator.hasNext();) {
			System.out.print(iterator.next() + " ");
                        Count++;
		}
                System.out.println();
		System.out.println("Count "+Count+"\n");
	}
	
	/**
	 * Updates the member variable cost with the route length and also returns it. 
	 * @return
	 */
	public double updateCost()
	{
            int Size=this.route.size();
            double tottalCost=0;
            for(int i=0;i<Size-1;i++){
                double  tempCost=this.tsp.edgeCost(this.route.get(i),this.route.get(i+1));
                tottalCost+=tempCost;
            }
            double  tempCost=this.tsp.edgeCost(this.route.get(0),this.route.get(this.route.size()-1));
            tottalCost+=tempCost;
            this.cost=tottalCost;
            return tottalCost;
	}

	public double getCost() {
            return cost;
	}

}

