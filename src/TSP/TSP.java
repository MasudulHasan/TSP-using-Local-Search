/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TSP 
{
	double x[], y[];
	int n;

	public TSP(double x[], double y[], int n) 
	{
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.n = n;

	}
	
	private double solve(int sigma) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Route route = new Route(this);
		route.print();
		

		FirstChoiceHillClimb fchc = new FirstChoiceHillClimb(this,sigma);
		SolutionInfo si = fchc.run();
		
		System.out.println(si.solution.getCost());
                System.out.println("COUNT");
                System.out.println(si.loopCount);
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)));
                    out.println(si.solution.getCost());
                    out.close();
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
		si.solution.print();
                return si.loopCount;
	}
	void print()
	{
		System.out.println("Dimension: "+n);
		for (int i = 0; i < n; i++) {
			System.out.println(x[i] + " " + y[i]);
		}
	}
        
        public void stat(String fileName, double tcount,int sigma) throws FileNotFoundException{
            Scanner in = new Scanner(new File("myfile.txt"));
            double tottal =0;
            double mini=1000000000;
            for (int i = 0; i <10; i++)
            {
                double temp=in.nextDouble();
                tottal+= temp;
                if(temp<mini)mini=temp;
            }
            PrintWriter writer = new PrintWriter("myfile.txt");
            writer.print("");
            writer.close();
            double avg=tottal/10;
            try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("solutionfile.txt", true)));
                    out.println(fileName + "   " + sigma+ "    "+ tcount+ "    "+avg+"    "+mini);
                    //out.println("\n\n\n");
                    out.close();
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
            }
            
        }
	
	/**
	 * Returns the distance between the node a and b
	 * @param a
	 * @param b
	 * @return
	 */
	public double edgeCost(int a, int b)
	{
            double x1= this.x[a];
            double y1=this.y[a];
            double x2= this.x[b];
            double y2=this.y[b];
            double X=(x1-x2)*(x1-x2);
            double Y=(y1-y2)*(y1-y2);
            double temp=X+Y;
            double dist=Math.sqrt(temp);
            return dist;
        }
	
	public static void main(String[] args)
	{
            int Test=0;
            while(Test<=4){
                String fileName="";
                try 
		{       Scanner in = null;
			//Scanner in = new Scanner(new File("test.tsp"));
			if(Test==0){
                            in = new Scanner(new File("att48.tsp"));
                            fileName="att48.tsp";
                        }
                        if(Test==1){
                            in = new Scanner(new File("burma14.tsp"));
                            fileName="burma14.tsp";
                        }
                        if(Test==2){
                             in = new Scanner(new File("st70.tsp"));
                             fileName="st70.tsp";
                        }
                        if(Test==3){
                            in = new Scanner(new File("ulysses16.tsp"));
                            fileName="ulysses16.tsp";
                        }
                        if(Test==4){
                            in = new Scanner(new File("ulysses22.tsp"));
                            fileName="ulysses22.tsp";
                        }
			String line = "";
			int n;
			
			//three comment lines
			in.nextLine();
			in.nextLine();
			in.nextLine();
			//get n
			line = in.nextLine();
			line = line.substring(11).trim();
			n = Integer.parseInt(line);
			
			//System.out.println("" +n);
			
			//two comment lines
			in.nextLine();
			in.nextLine();
			
			double x[] = new double[n];
			double y[] = new double[n];
			
			for (int i = 0; i < n; i++)
			{
				in.nextInt();
				x[i] = in.nextDouble();
				y[i] = in.nextDouble();
			}
			
			TSP tsp = new TSP (x,y,n);
			//tsp.print();
                        int sigma=10;
                        for(int i=0;i<4;i++){
                            sigma=sigma*10;
                            double tcount=0;
                            for(int j=0;j<10;j++){
                                double tempcount=tsp.solve(sigma);
                                tcount+=tempcount;
                            }
                            tsp.stat(fileName,tcount/10,sigma);
                        }
                        //tsp.solve(10000);
                        
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Test++;
            }
    }
}

