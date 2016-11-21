/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.util.Random;


public class FirstChoiceHillClimb 
{
	TSP tsp;
	int sigma;
	
	public FirstChoiceHillClimb(TSP tsp, int sigma) 
	{
		// TODO Auto-generated constructor stub
		this.tsp = tsp;
		this.sigma = sigma;
	}
	
	/**
	 * Main loop of your local search algorithm. 
	 * After the search is complete, create a SolutionInfo Object 
	 * with related information and return to the caller to generate aggregated results
	 * @return
	 */
	public SolutionInfo run() 
	{
		//Main loop of your local search algorithm. 
		
                Route current=new Route(this.tsp);
                int count = 0;
                Route neighbor = new Route(current);
                int i=0;
                while (true){
                    for(i =0; i < this.sigma; i++){
                        //System.out.println("IN Loop "+i);
                        double ran; 
                        Random randomno = new Random();
                        ran = randomno.nextDouble();
                        //System.out.println("Random "+ran);
                        if (ran <0.5){
                            neighbor=Two_Opt.apply(neighbor);
                                    
                        }
                            
                        else{
                            neighbor=ZeroOneExchange.apply(neighbor);
                        }
                        //count ++;
                        if (neighbor.cost<current.cost)break;
                    }
                    if(i ==this.sigma) {
                        SolutionInfo si = new SolutionInfo(current,count);
                        return si;
                     }
                    current = neighbor;
                    count ++;
                    //SolutionInfo si = new SolutionInfo(neighbor,count);
                    //return si;
                }
        }
        
}
