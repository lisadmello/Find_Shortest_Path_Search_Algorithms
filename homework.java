package assignment;
import java.io.*;
public class homework {	
public static void main(String args[]) throws IOException
{
	int i,n=0;
	String input_file="input.txt";       // read input.txt path
	String line;
	FileReader a =new FileReader(input_file);
	BufferedReader b = new BufferedReader(a);
	while(( line = b.readLine())!=null)      // till you do not count all the lines
	n=n+1;									 // count lines
	b.close();
	String[] livetraffic = new String[n];	 // create new array for line storage
	FileReader c =new FileReader(input_file);
	BufferedReader d = new BufferedReader(c);
	for (i=0;i<n;i++) 
	livetraffic[i] = d.readLine().trim();			 //place each line at individual indices of the array
	String algo = livetraffic[0];			 // name of the algorithm
	switch (algo) {
    case "BFS":								 //if the algorithm is BFS
        BFS temp = new BFS(livetraffic,n);
        temp.computeBFS();					 // compute the path using BFS
        break;
    case "DFS":
    	DFS temp7 = new DFS(livetraffic,n);
        temp7.computeDFS();					 // compute the path using DFS
        break;
    case "UCS":
    	UCS temp8 = new UCS(livetraffic,n);
        temp8.computeUCS();					// compute the path using UCS
        break;
    case "A*":
    	Astar temp9 = new Astar(livetraffic,n);
        temp9.computeAstar();				// compute the path using Astar
        break;
    default:
        System.out.println("Invalid algorithm choice");
	}
}
}
