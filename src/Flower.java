public class Flower extends Plant
{
    public Flower(String name)
    {
        super(name);
        super.updatePlot(2,2,super.getSymbol());
    }

    @Override
    public void grow(int num)
    {
        //Immediately exit if arg is invalid or finished
        if(num<=0)
            return;
        //If grow arg would cause a plant to leave plot, set it to max arg
        if(num>2)
            num=2;
        //This one is hardcoded
        //If the flower is growthLevel 1
        if(super.getGrowthLevel()==1)
        {
            //Growth to full
            if(num==2)
            {
                super.incrementGrowLevel();
                super.updatePlot(2,1,super.getSymbol());
                super.updatePlot(2,3,super.getSymbol());
                super.updatePlot(1,2,super.getSymbol());
                super.updatePlot(3,2,super.getSymbol());
                super.incrementGrowLevel();
                super.updatePlot(2,0,super.getSymbol());
                super.updatePlot(2,4,super.getSymbol());
                super.updatePlot(0,2,super.getSymbol());
                super.updatePlot(4,2,super.getSymbol());
            }
            else if(num==1) //Growth to mid
            {
                super.incrementGrowLevel();
                super.updatePlot(2,1,super.getSymbol());
                super.updatePlot(2,3,super.getSymbol());
                super.updatePlot(1,2,super.getSymbol());
                super.updatePlot(3,2,super.getSymbol());
            }
        }
        else if(super.getGrowthLevel()==2) //Growth to full
        {
            super.incrementGrowLevel();
            super.updatePlot(2,0,super.getSymbol());
            super.updatePlot(2,4,super.getSymbol());
            super.updatePlot(0,2,super.getSymbol());
            super.updatePlot(4,2,super.getSymbol());
        }
    }

}
