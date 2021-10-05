public class Flower extends Plant
{
    //Data
    //This is basically a macro for the symbol of the flower instance
    char c = super.getSymbol();
    //These patterns are used to set the growth stage because I couldn't figure out a looping way to do it
    private char[][] grow2 = new char[][]{{'.', '.','.','.','.'},{'.', '.',c,'.','.'},{'.',c,c,c,'.'},{'.', '.',c,'.','.'},{'.', '.','.','.','.'}};
    private char[][] grow3 = new char[][]{{'.','.',c,'.','.'},{'.', c,c,c,'.'},{c,c,c,c,c},{'.', c,c,c,'.'},{'.', '.',c,'.','.'}};
    private char[][] grow4 = new char[][]{{'.',c,c,c,'.'},{c, c,c,c,c},{c,c,c,c,c},{c, c,c,c,c},{'.', c,c,c,'.'}};
    private char[][] grow5 = new char[][]{{c,c,c,c,c},{c,c,c,c,c},{c,c,c,c,c},{c,c,c,c,c},{c,c,c,c,c}};

    //Methods

    /**
     * Constructor
     * @param name name of the flower object to be created
     */
    public Flower(String name)
    {
        super(name);
        super.updatePlot(2,2,super.getSymbol());
    }

    /**
     * This is the flower implementation of the grow method inherited from the Plant class
     * @param num the number of times to grow
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
        //This one is hardcoded
        //If the flower is growthLevel 1
        if(super.getGrowthLevel()==1)
        {
            if(num==1)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow2);
            }
            else if(num==2)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow3);
            }
            else if(num==3)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow4);
            }
            else if(num==4)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow5);
            }
        }
        else if(super.getGrowthLevel()==2)
        {
            if(num==1)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow3);
            }
            else if(num==2)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow4);
            }
            else if(num==3)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow5);
            }
        }
        else if(super.getGrowthLevel()==3)
        {
            if(num==1)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow4);
            }
            else if(num==2)
            {
                super.incrementGrowLevel();
                super.updatePlot(grow5);
            }
        }
        else if(super.getGrowthLevel()==4)
        {
            super.incrementGrowLevel();
            super.updatePlot(grow5);
        }
    }
}
