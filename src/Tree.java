public class Tree extends Plant
{
    public Tree(String name)
    {
        super(name);
        super.updatePlot(4,2,super.getSymbol());
    }

    /**
     * This method is the tree version of the grow method that overrides
     * the superclass. This method recursively grows the plant and ensures
     * the plant cannot leave its plot.
     * @param num how many times to grow
     */
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
        super.updatePlot(5-super.getGrowthLevel(),2,super.getSymbol());
        this.grow(num);
    }


}
