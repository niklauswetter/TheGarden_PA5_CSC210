import java.awt.*;

public class Garden
{
    //Data
    int row, col;
    private Plant[][] gardenArray;

    //Methods
    public Garden(int row, int col) throws Exception {
        this.row = row;
        this.col = col;
        if(row<1 || col<1 || col>16)
            throw new Exception("Garden dimensions must be positive, no more than " +
                    "16 columns wide.");
        this.gardenArray = new Plant[this.row][this.col];
        this.initializeGarden();
    }

    public void addPlant(int row, int col, Plant plant)
    {
        this.gardenArray[row][col] = plant;
    }

    /**
     * This method will be called whenever a plant is removed for some reason
     * @param row the row of the plant to be removed
     * @param col the column of the plant to be removed
     */
    public void removePlant(int row, int col)
    {
        this.gardenArray[row][col]= new Plant();
    }

    /**
     * This method prints the entire garden
     */
    public void printGarden()
    {
        for(int i =0; i< this.row;i++)
        {
            for(int j = 0; j<5; j++)
            {
                for(Plant p:this.gardenArray[i])
                    p.printLine(j);
                System.out.println();
            }
        }
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
