public class Vegetable extends Plant
{
    public Vegetable(String name)
    {
        super(name);
        super.updatePlot(0,2,super.getSymbol());
    }
}
