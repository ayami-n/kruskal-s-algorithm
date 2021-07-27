//purpose
//crating objects for kruskal's Algorithm and manage the objects between the classes

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static java.util.Collections.sort;

public class kcluster
{
    //global variable
    private static KruskalAlgo kruskal = new KruskalAlgo();
    private static weight matrix = new weight();
    private static ArrayList <hotspot> myHotSpot = new ArrayList<>();
    private static ArrayList<edge> myEdge = new ArrayList<>();
    private static int size;
    private static int cluster;

    private static void importFile(String fileName) //given file name comes here
    {
        String importName = null;
        Scanner importStream = null;
        int ID = -999;
        double X = -999;
        double Y = -999;

        try //this try is try to access the file
        {
            System.out.println("...Importing...");
            importName = fileName;
            importStream = new Scanner(new File(importName));

            while(importStream.hasNextLine())
            {
                try //this try is try to scan the contexts
                {
                    String line = importStream.nextLine(); //reading lines
                    if(line.equals("")) //to preventing to stop run when the beginning of the line is nothing
                    {
                        continue;
                    }

                    String[] parts = line.split(","); //sprit the lines by space

                    /* assume this is import format looks like
                        ID  X   Y
                        1,1.0,1.0
                        2,2.0,2.0
                        3,3.0,5.0
                        4,7.0,8.0
                        5,8.0,7.0
                     */

                    try
                    {
                        ID = Integer.parseInt(parts[0]); // for change int array no.0

                        if (ID < 0) // exit negative number
                        {
                            System.err.println("Negative ID");
                            System.exit(0);
                        }
                    }
                    catch (NumberFormatException e) // if imported value is 'a,b..' reject
                    {
                        System.err.println("Invalid value");
                        System.exit(0);
                    }

                    try
                    {
                         X = Double.parseDouble(parts[1]); // for change double array no.1

                        if (X < 0) // exit negative number
                        {
                            System.err.println("Test Negative value");
                            System.exit(0);
                        }
                    }
                    catch (NumberFormatException e) // if imported value is 'a,b..' reject
                    {
                        System.err.println("Invalid value");
                        System.exit(0);
                    }

                    try
                    {
                         Y = Double.parseDouble(parts[2]);

                        if (Y < 0) // exit negative number
                        {
                            System.err.println("Negative value");
                            System.exit(0);
                        }
                    }
                    catch (NumberFormatException e) // if imported value is 'a,b..' reject
                    {
                        System.err.println("Invalid value");
                        System.exit(0);
                    }

                    myHotSpot.add(new hotspot(ID, X, Y));

                    size = myHotSpot.size();
                } //end of try to read

                catch(ArrayIndexOutOfBoundsException a)
                {
                    System.err.println("Invalid Line Format: Not Enough Information");
                    System.exit(0);
                }
                catch(NoSuchElementException | NullPointerException n)
                {
                    System.err.println(n.getMessage());
                    System.exit(0);
                }
            } // end of while loop

            matrix.setMyEdge(myEdge); //allow to use this edge array to weight class
            matrix.setMyHotSpot(myHotSpot); //allow to use this hotspot to weight class

        } // end of try to access the files

        catch(FileNotFoundException e) //catch for access files' errors
        {
            System.err.println("Error Opening The File " + importName);
            System.exit(0);
        }
        finally //finally done to store the values from the text file
        {
            if(importStream !=null)
            {
                importStream.close(); //closing import stream for the next
            }
        }
    }

   private static boolean check() //check the condition as much as can atm
   {
       ArrayList<Integer> myID = new ArrayList<Integer>();

       for (int i = 0; i < myHotSpot.size(); i++)
       {
           myID.add(myHotSpot.get(i).getID());
       }

       sort(myID);// before sort 1,3,1 ---> after sort 1,1,3

       for (int i = 0; i < myID.size()-1; i++) // myID.size() is over size so need -1
       {
           if (myID.get(i) == myID.get(i + 1))  //like 1,1,3
           {
               return false;
           }
       }

       if (cluster > size) //cluster must less than size
       {
           return false;
       }

       return true;
   }


    private void run(String fileName, int cluster)  //here is actual main runs for security reasons
    {
        importFile(fileName); //given name from Java cmd goes to importing function

        if(check()) // cannot calculate cluster amounts are bigger than hotspots
        {
            System.out.println("Hello and welcome to Kruskal's Clustering! ");
            System.out.println("The weighted graph of hotspots:");
            matrix.matrix(size); //store date as one array, the size uses for break the line

            System.out.println("\n\nThere are " + size + " hotspots.");
            System.out.println("You have requested " + cluster + " temporary fire stations.");

            kruskal.setMyEdge(myEdge);
            kruskal.setMyHotSpot(myHotSpot);

            kruskal.disjoint(size, cluster);

            System.out.print("\nThank you for using Kruskal's Clustering. Bye.");
        }
        else // return false not allow to further steps
        {
            System.err.println("Invalid Data");
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        if(args.length == 2) //2 arguments required: the first is file name, the second is number of cluster
        {
            kcluster sim = new kcluster();
            String fileName = args[0]; //this is for java cmd and any file name can take and user can gives any file from the cmd

            try
            {
                cluster = Integer.parseInt(args[1]); // for change int array no.0
                if (cluster <= 0) // exit negative number
                {
                    System.err.println("No Cluster");
                    System.exit(0);
                }
            }
            catch (NumberFormatException e) // if imported value is 'a,b..' reject
            {
                System.err.println("Invalid value");
                System.exit(0);
            }

            sim.run(fileName, cluster); //passing the file name and cluster to static run

        }
        else
        {
            System.err.println("Invalid Input");
            System.exit(0);
        }
    }

}
