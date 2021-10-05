

public class Plant
{
    //Instance Variables
    private String name;
    private char symbol;
    private int growthLevel;
    private char[][] plot = new char[5][5];

    //Methods

    /**
     * This constructor will be used to instantiate the subclass objects
     * @param name
     */
    public Plant(String name)
    {
        this.intializePlot();
        this.name=name.toLowerCase();
        this.symbol=this.name.charAt(0);
        this.growthLevel = 1;
        //Subclasses will use updatePlot here
    }

    /**
     * This constructor will be used to instantiate the
     * DEFAULT plant class, which has an empty plot
     */
    public Plant()
    {
        this.intializePlot();
        this.name="DEFAULT";
        this.symbol='.';
        this.growthLevel = 1;
    }

    /**
     * Basic getter methods
     */
    public String getName(){return this.name;}
    public char getSymbol(){return this.symbol;}
    public int getGrowthLevel(){return this.growthLevel;}
    public char[][] getPlot(){return this.plot;}

    public void grow(int num)
    {
        //Subclasses will implement a specific growth patten on their own
    }

    public void incrementGrowLevel(){this.growthLevel++;}

    /**
     * This method updates the plant plot, since all subclass objects will
     * 'grow' differently, they will use this method in their methods
     * @param row row of plot to update
     * @param col column of plot to update
     * @param input character to be placed at this location in plot
     */
    public void updatePlot(int row, int col, char input)
    {
        this.plot[row][col]=input;
    }

    /**
     * This method prints a single row of the plot. Will be called
     * to print the entire garden
     * @param row is the row to print
     */
    public void printLine(int row)
    {
        for(char c:this.plot[row])
            System.out.print(c);
    }


    private void intializePlot()
    {
        for(int i  = 0; i<this.plot.length;i++)
        {
            for(int j =  0; j<this.plot[i].length;j++)
            {
                this.plot[i][j] = '.';
            }
        }
    }

    /**
     * This method will only be used for testing. Separate methods will
     * be written for printing the plant objects in the garden
     * @return String version of the plant
     */
    @Override
    public String toString()
    {
        String value ="";
        for(char[] c:this.plot)
        {
            for(char cc:c)
            {
                value+=cc+" ";
            }
            value+="\n";
        }
        return value;
    }

}
