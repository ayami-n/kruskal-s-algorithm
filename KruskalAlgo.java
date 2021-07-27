//purpose
//calculating Kruskal’s Algo

import java.util.ArrayList;
import static java.util.Collections.sort;

public class KruskalAlgo
{
    //global variables
    private ArrayList<hotspot> myHotSpot = new ArrayList<>(); //these values go to the kruskal
    private ArrayList<edge> myEdge = new ArrayList<>(); //provide min edge for kruskal
    private ArrayList<hotspot>[] kruskalGraph; //store find set values

    private int[] parent; //store parent of edge
    private int cluster;
    private int edge; //if need to handle multiple kruskal's change to private static int edge for sharing
    private double interDistance; //min inter distance between any two items belonging to different cluster

    //constructor
    public KruskalAlgo() { } //nothing to create atm

    //setter
    public void setMyEdge(ArrayList<edge> myEdge) { this.myEdge = myEdge; }

    public void setMyHotSpot(ArrayList<hotspot> myHotSpot) { this.myHotSpot = myHotSpot; }

    //disjoint set = make set + find set + merge

    public void disjoint(int new_vertex, int stop)
    {
        double minEdge;
        int start, end;

        cluster = myHotSpot.size();

        int stoper = stop; //in the main called cluster

        //this is for printing out the results
        kruskalGraph = new ArrayList[new_vertex];
        for(int i=0; i < new_vertex; i++)
        {
            kruskalGraph[i] = new ArrayList<>();
        }

        //make set itself
        parent = new int[new_vertex];

        for(int i=0; i < new_vertex; i++)
        {
            parent[i] = i;
        }

        sort(myEdge); //sort by weight

        if(myEdge.get(myEdge.size()-1).getWeight() == 0) //the last point Zero is, no weight difference between the IDs and cannot calculate
        {
            System.err.println("\nAll Weights Are 0.00: Invalid Data");
            System.exit(0);
        }

        if(stop == 1) //special case
        {
            for(int i=0; i < new_vertex; i++)
            {
                parent[i] = 0;
            }

            for(int i=0; i < new_vertex; i++)
            {
                kruskalGraph[0].add(new hotspot(myHotSpot.get(i).getID(), myHotSpot.get(i).getX(), myHotSpot.get(i).getY()));
            }

            double x =0;
            double y =0;
            StringBuffer name = new StringBuffer();

            for(int i = 0; i < kruskalGraph[0].size(); i++)
            {
                name.append(kruskalGraph[0].get(i).getID());
                if(i != kruskalGraph[0].size()-1)
                {
                    name.append(",");
                }
                x += kruskalGraph[0].get(i).getX();
                y += kruskalGraph[0].get(i).getY();
            }

            x = x/ kruskalGraph[0].size();
            y = y/ kruskalGraph[0].size();

            System.out.println("\nStation " +  1 +":");
            System.out.println(String.format("Coordinates (%-1.2f, %-1.2f)", x, y));
            System.out.println("HotSpots {"+ name +"}");
            System.out.println("\nInter-Clustering Distance: " + 0.00); //end of the kruskal's Algo
        }

        else
        {
            while(cluster > stoper) // clusters are stopper of the this methods
            {
                // ex)      1.41 <--- min edge value
                // start --------- end point

                minEdge = myEdge.get(edge).getWeight(); //always come first values Not necessary have this step
                start =  myEdge.get(edge).getStart(); //start value
                end = myEdge.get(edge).getEnd(); //get end value

                if(findSet(start) != findSet(end)) //avoiding cycle
                {
                    merge(findSet(start), findSet(end)); //find set always return true parent values
                }

                edge++; //for iteration
            }

            interDistance = myEdge.get(edge).getWeight(); //set up default value

            while (findSet(myEdge.get(edge).getStart()) == findSet(myEdge.get(edge).getEnd())) //if this is cycle, update the value
            {
                edge++; //edge++ checks the next edge value
                interDistance = myEdge.get(edge).getWeight(); //find the inter cluster distance
            }

            //adding values to kruskal arraylist array base on parent index
            //following the sample format and 2 clusters
            //get parent will be like
            //parent[] = 0, 0, 0, 3, 3

            //store the data based on the parent into the arraylist of array

            for(int i=0; i < new_vertex; i++) //todo 0 0 1 3 3  --> 3 cluster 2
            {
                kruskalGraph[findSet(i)].add(new hotspot(myHotSpot.get(i).getID(), myHotSpot.get(i).getX(), myHotSpot.get(i).getY()));
            }

            //section for printing out

            double x =0;
            double y =0;
            StringBuffer name;
            int number = 1;
            for(int i=0; i < new_vertex; i++)
            {
                name = new StringBuffer();
                x =0;
                y =0;
                if(!kruskalGraph[i].isEmpty())
                {
                    for(int j = 0; j < kruskalGraph[i].size(); j++)
                    {
                        name.append(kruskalGraph[i].get(j).getID());
                        if(j != kruskalGraph[i].size()-1)
                        {
                            name.append(",");
                        }
                        x += kruskalGraph[i].get(j).getX();
                        y += kruskalGraph[i].get(j).getY();
                    }

                    x = x/ kruskalGraph[i].size();
                    y = y/ kruskalGraph[i].size();

                    System.out.println("\nStation " +  number +":");
                    System.out.println(String.format("Coordinates (%-1.2f, %-1.2f)", x, y));
                    System.out.println("HotSpots {"+ name +"}");
                    number++;
                }
            }

            System.out.println("\nInter-Clustering Distance: " + interDistance); //end of the kruskal's Algo
        }

    }

    public int findSet(int i) //return original make set value
    {
        while(i != parent[i])
        {
            i = parent[i];
        }
        return i;
    }

    public void merge(int start, int end) //merge the parent array version 2
    {
        parent[end] = start;
        cluster--;
    }

}
