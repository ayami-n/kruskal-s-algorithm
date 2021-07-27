import java.util.ArrayList;
import static java.lang.Math.round;


//purpose
//store imported data only
public class hotspot
{
    //global variable
    private int ID;
    private double X, Y;

    //constructor
    public hotspot() //not necessary to have
    {
        this.ID = -999;
        this.X = -999;
        this.Y = -999;
    }

    public hotspot(int ID, double x, double y)
    {
        this.ID = ID;
        this.X = x;
        this.Y = y;
    }

    //getter
    public int getID() { return this.ID; }

    public double getX()
    {
        return this.X;
    }

    public double getY()
    {
        return this.Y;
    }

}
