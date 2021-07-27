//purpose
//store all data for Kruskal's Algo

public class edge implements Comparable <edge>
{
    private int start, end;
    private double weight;

    //constructor
    public edge(int row, int column, double weight)
    {
        this.start = row;
        this.end = column;
        this.weight = weight;
    }

    //getter

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public double getWeight()
    {
        return weight;
    }


    //function
    public void print(int size)
    {
        if(this.end % size == 0) //stored all data in one array. mod 0 is change the line space
        {
            System.out.print("\n");
        }
        System.out.print(String.format("%-6s", this.weight));

    }

    @Override
    public int compareTo(edge compareEdge)
    {
        if(this.getWeight() > compareEdge.getWeight())
        {
            return 1;
        }
        else if(this.getWeight() < compareEdge.getWeight())
        {
            return -1;
        }
        return 0;
    }
}

