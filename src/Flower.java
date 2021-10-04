public class Flower extends Plant
{
    public Flower(String name)
    {
        super(name);
        super.updatePlot(2,2,super.getSymbol());
    }

}
