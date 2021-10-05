public class Vegetable extends Plant
{
    public Vegetable(String name)
    {
        super(name);
        super.updatePlot(0,2,super.getSymbol());
    }

    @Override
    public void grow(int num)
    {
        //Immediately exit if arg is invalid or finished
        if(num<=0)
            return;
        //If grow arg would cause a plant to leave plot, set it to max arg
        if(num>4)
            num=4;
        //Recursive implementation
        num--;
        super.incrementGrowLevel();
        super.updatePlot(super.getGrowthLevel()-1,2,super.getSymbol());
        this.grow(num);
    }
}
