public class Garden
{
    //Data
    private int row, col;
    private Plant[][] gardenArray;

    //Methods

    /**
     * Constructor
     * @param row rows in garden
     * @param col columns in garden
     * @throws Exception fileNotFound
     */
    public Garden(int row, int col) throws Exception {
        this.row = row;
        this.col = col;
        if(row<1 || col<1)
        {

        }
        if(col>16)
        {
            System.out.println("Too many plot columns.");
            System.exit(0);
        }
        this.gardenArray = new Plant[this.row][this.col];
        this.initializeGarden();
    }

    /**
     * Getters
     */
    public Plant[][] getGardenArray(){return this.gardenArray;}
    public int getRow(){return this.row;}
    public int getCol(){return this.col;}

    /**
     * This method adds a plant to the garden
     * @param row row in which to add plant
     * @param col column in which to add plant
     * @param plant the plant object to be added
     */
    public void addPlant(int row, int col, Plant plant)
    {
        this.gardenArray[row][col] = plant;
    }

    /**
     * This method prints the entire garden
     */
    public void printGarden()
    {
        System.out.println("> PRINT");
        for(int i =0; i< this.row;i++)
        {
            for(int j = 0; j<5; j++)
            {
                for(Plant p:this.gardenArray[i])
                    p.printLine(j);
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * Private method to fill the garden with default plant objects
     */
    private void initializeGarden()
    {
        for(int i = 0;i<this.row;i++)
        {
            for(int j = 0;j<this.col;j++)
            {
                this.gardenArray[i][j] = new Plant();
            }
        }
    }
}
