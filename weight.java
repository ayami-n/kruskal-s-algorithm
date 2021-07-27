//purpose
//calculate weight and store the data to the row

import java.util.ArrayList;
import static java.lang.Math.round;

public class weight
{
    //global variables
    private ArrayList<hotspot> myHotSpot = new ArrayList<>();
    private ArrayList<edge> myEdge = new ArrayList<>();

    //constructor
    public weight() { } //nothing to create atm

    //setter
    public void setMyHotSpot(ArrayList<hotspot> new_Hot)
    {
        this.myHotSpot = new_Hot;
    }

    public void setMyEdge(ArrayList<edge> new_Edge){this.myEdge = new_Edge; }

    //function
    public void matrix(int size)
    {
        ArrayList<edge> display = new ArrayList<>(); // for displaying data
        double A, B, C;
        for (int i = 0; i < myHotSpot.size(); i++)
        {
            for (int j = 0; j < myHotSpot.size(); j++)
            {
                A = A(myHotSpot.get(i).getY(), myHotSpot.get(j).getY());
                B = B(myHotSpot.get(i).getX(), myHotSpot.get(j).getX());

                C = Math.sqrt(B + A);
                C = round(C * 100.0) / 100.0;

                if(j >= i+1)
                {
                    myEdge.add(new edge( i, j, C)); //this is using for kruskal algo
                }

                /* this is I want because it can prevent duplications like 1-2, 2-1 with myEdge
                    0 1 2 3 4
                * 0 W W W W W
                * 1   w w w W
                * 2     w w W
                * 3       w W
                * 4         W
                *
                * */

                //this parts are for displaying
                display.add(new edge(i, j, C));
            }
        }

        printEdge(display, size); //displaying temporary results
    }

    public double A(double base, double height)
    {
        double A = -999;

        if (base > height)
        {
            double temp = base;
            base = height;
            height = temp;
        }
        A = Math.pow(height - base, 2);

        return A;
    }

    public double B(double base, double width)
    {
        double B = -999;

        if (base > width)
        {
            double temp = base;
            base = width;
            width = temp;
        }

        B = Math.pow(width - base, 2);
        return B;
    }

    public void printEdge(ArrayList<edge> tempEdge, int size)
    {
        for(int i=0; i < tempEdge.size(); i++)
        {
           tempEdge.get(i).print(size);
        }
    }

}
