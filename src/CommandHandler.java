/**
 * This class will receive the command data from the input file, and provide the
 * code to interpret the file commands. This will ensure main() does not contain
 * too much code, and the command code is more readable.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandHandler
{
    //Fields
    private File inputFile;
    private Scanner scanner;
    private Garden garden;
    private List<String> commandList = new ArrayList<String>();
    private int gardenRow, gardenCol;
    private Plant[][] gardenArrayReference;
    private String[] trees = new String[]{"oak","willow","banana","coconut","pine"};
    private String[] vegetables = new String[]{"garlic","zucchini","tomato","yam","lettuce"};
    private String[] flowers = new String[]{"iris","lily","rose","daisy","tulip","sunflower"};

    //Methods

    /**
     * Constructor
     * @param file the command file to be loaded
     * @throws FileNotFoundException
     */
    public CommandHandler(File file) throws FileNotFoundException
    {
        this.inputFile = file;
        this.scanner = new Scanner(this.inputFile);
        this.loadCommands();
        this.getRowsAndCols();
    }

    /**
     * This method sets the garden field, as well as related fields used by
     * the methods within this class.
     * @param garden the garden object to be loaded
     */
    public void setGarden(Garden garden)
    {
        this.garden=garden;
        this.gardenRow=this.garden.getRow()-1;
        this.gardenCol=this.garden.getCol()-1;
        gardenArrayReference = this.garden.getGardenArray();
    }

    /**
     * Getters
     */
    public int getGardenRow(){return this.gardenRow;}
    public int getGardenCol(){return this.gardenCol;}

    /**
     * This method uses a switch statement to read through all the commands
     * from the file and execute them one by one.
     */
    public void execute()
    {
        for(int i = 3; i<this.commandList.size();i++)
        {
            String[] temp = this.commandList.get(i).split("\\s+");
            switch (temp[0])
            {
                case "plant":
                    this.plant(temp);
                    break;
                case "grow":
                    this.grow(temp);
                    break;
                case "harvest":
                    this.harvest(temp);
                    break;
                case "pick":
                    this.pick(temp);
                    break;
                case "cut":
                    this.cut(temp);
                    break;
                case "print":
                    this.printGarden();
                    break;
            }
        }
    }

    //Private methods

    /**
     * This method loads all the commands from the input file to the command list
     */
    private void loadCommands()
    {
        while(this.scanner.hasNext())
        {
            commandList.add(scanner.nextLine().toLowerCase());
        }
    }

    /**
     * This method will be used to get the row and column arguments from the file
     * they will be used to create the garden object, then reset by the setGarden method
     * to the proper length
     */
    private void getRowsAndCols()
    {
        String temp = this.commandList.get(0)+" "+this.commandList.get(1);
        String[] tempArr = temp.split("\\s+");
        this.gardenRow = Integer.parseInt(tempArr[1]);
        this.gardenCol = Integer.parseInt(tempArr[3]);
    }

    /**
     * This method prints the entire garden
     */
    private void printGarden()
    {
        this.garden.printGarden();
    }

    /**
     * This method executes the plant command
     * @param command the string array of the input line
     */
    private void plant(String[] command)
    {

        int tempR = Character.getNumericValue(command[1].charAt(1));
        int tempC = Character.getNumericValue(command[1].charAt(3));
        String inputName = command[2];
        for(String s:this.trees)
        {
            if(inputName.equals(s))
            {
                this.garden.addPlant(tempR,tempC,new Tree(inputName));
                return;
            }
        }
        for(String s:this.flowers)
        {
            if(inputName.equals(s))
            {
                this.garden.addPlant(tempR,tempC,new Flower(inputName));
                return;
            }
        }
        for(String s:this.vegetables)
        {
            if(inputName.equals(s))
            {
                this.garden.addPlant(tempR,tempC,new Vegetable(inputName));
                return;
            }
        }
    }

    /**
     * This method executes the grow command
     * @param command the string array of the input line
     */
    private void grow(String[] command)
    {
        int growNum = Integer.parseInt(command[1]);
        //This option runs if the grow command is run to grow all plants
        if(command.length == 2)
        {
            System.out.println("> GROW "+growNum);
            System.out.println();
            for(Plant[] p:this.gardenArrayReference)
            {
                for(Plant pp:p)
                    pp.grow(growNum);
            }
        }
        else if(command.length == 3)
        {
            String growthArg = command[2];
            //This will only run for growth at a specific point
            if(growthArg.charAt(0) == '(')
            {
                System.out.println("> GROW "+growNum+" "+growthArg);
                System.out.println();
                int tempR = Character.getNumericValue(growthArg.charAt(1));
                int tempC = Character.getNumericValue(growthArg.charAt(3));
                boolean isOutOfBounds = tempR>this.gardenRow || tempR<0 || tempC>this.gardenCol || tempC<0;
                if(isOutOfBounds)
                {
                    System.out.println("Can't grow there.");
                    System.out.println();
                    return;
                }
                boolean isASubClass = gardenArrayReference[tempR][tempC].getClass().equals(Tree.class) ||
                        gardenArrayReference[tempR][tempC].getClass().equals(Vegetable.class) ||
                        gardenArrayReference[tempR][tempC].getClass().equals(Flower.class);

                if(!isOutOfBounds && isASubClass)
                    this.gardenArrayReference[tempR][tempC].grow(growNum);
                else
                {
                    System.out.println("Can't grow there.");
                    System.out.println();
                }
            }
            else if(growthArg.equals("flower"))
            {
                System.out.println("> GROW "+growNum+" flower");
                System.out.println();
                for(Plant[] p:this.gardenArrayReference)
                {
                    for(Plant pp:p)
                    {
                        if(pp.getClass().equals(Flower.class))
                        {
                            pp.grow(growNum);
                        }
                    }
                }
            }
            else if(growthArg.equals("tree"))
            {
                System.out.println("> GROW "+growNum+" tree");
                System.out.println();
                for(Plant[] p:this.gardenArrayReference)
                {
                    for(Plant pp:p)
                    {
                        if(pp.getClass().equals(Tree.class))
                        {
                            pp.grow(growNum);
                        }
                    }
                }
            }
            else if(growthArg.equals("vegetable"))
            {
                System.out.println("> GROW "+growNum+" vegetable");
                System.out.println();
                for(Plant[] p:this.gardenArrayReference)
                {
                    for(Plant pp:p)
                    {
                        if(pp.getClass().equals(Vegetable.class))
                        {
                            pp.grow(growNum);
                        }
                    }
                }
            }
            else
            {
                System.out.println("> GROW "+growNum+" "+growthArg);
                System.out.println();
                for(Plant[] p:this.gardenArrayReference)
                {
                    for(Plant pp:p)
                    {
                        if(pp.getName().equals(growthArg))
                        {
                            pp.grow(growNum);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method executes the harvest command
     * @param command the string array of the input line
     */
    private void harvest(String[] command)
    {
        if(command.length==1)
        {
            System.out.println("> HARVEST");
            System.out.println();
            for(int i = 0; i<this.gardenArrayReference.length;i++)
            {
                for(int j = 0; j<this.gardenArrayReference[i].length;j++)
                {
                    //Replaces all vegetables with the default plot
                    if(this.gardenArrayReference[i][j].getClass().equals(Vegetable.class))
                        this.garden.addPlant(i,j,new Plant());
                }
            }
        }
        else if(command.length==2)
        {
            String growthArg = command[1];
            if(growthArg.charAt(0) == '(')
            {
                System.out.println("> HARVEST "+growthArg);
                System.out.println();
                int tempR = Character.getNumericValue(growthArg.charAt(1));
                int tempC = Character.getNumericValue(growthArg.charAt(3));
                boolean isOutOfBounds = tempR>this.gardenRow || tempR<0 || tempC>this.gardenCol || tempC<0;
                if(isOutOfBounds)
                {
                    System.out.println("Can't harvest there.");
                    System.out.println();
                    return;
                }
                if(!isOutOfBounds && this.gardenArrayReference[tempR][tempC].getClass().equals(Vegetable.class))
                    this.gardenArrayReference[tempR][tempC] = new Plant();
                else
                {
                    System.out.println("Can't harvest there.");
                    System.out.println();
                }
            }
            else
            {
                System.out.println("> HARVEST "+growthArg);
                System.out.println();
                replacePlantsByName(growthArg);
            }
        }
    }

    /**
     * This method executes the pick command
     * @param command the string array of the input line
     */
    private void pick(String[] command)
    {
        if(command.length==1)
        {
            System.out.println("> PICK");
            System.out.println();
            for(int i = 0; i<this.gardenArrayReference.length;i++)
            {
                for(int j = 0; j<this.gardenArrayReference[i].length;j++)
                {
                    //Replaces all flowers with the default plot
                    if(this.gardenArrayReference[i][j].getClass().equals(Flower.class))
                        this.garden.addPlant(i,j,new Plant());
                }
            }
        }
        else if(command.length==2)
        {
            String growthArg = command[1];
            if(growthArg.charAt(0) == '(')
            {
                System.out.println("> PICK "+growthArg);
                System.out.println();
                int tempR = Character.getNumericValue(growthArg.charAt(1));
                int tempC = Character.getNumericValue(growthArg.charAt(3));
                boolean isOutOfBounds = tempR>this.gardenRow || tempR<0 || tempC>this.gardenCol || tempC<0;
                if(isOutOfBounds)
                {
                    System.out.println("Can't pick there.");
                    System.out.println();
                    return;
                }
                if(!isOutOfBounds && this.gardenArrayReference[tempR][tempC].getClass().equals(Flower.class))
                    this.gardenArrayReference[tempR][tempC] = new Plant();
                else
                {
                    System.out.println("Can't pick there.");
                    System.out.println();
                }
            }
            else
            {
                System.out.println("> PICK "+growthArg);
                System.out.println();
                replacePlantsByName(growthArg);
            }
        }
    }

    /**
     * This method executes the cut command
     * @param command the string array of the input line
     */
    private void cut(String[] command)
    {
        if(command.length==1)
        {
            System.out.println("> CUT");
            System.out.println();
            for(int i = 0; i<this.gardenArrayReference.length;i++)
            {
                for(int j = 0; j<this.gardenArrayReference[i].length;j++)
                {
                    //Replaces all vegetables with the default plot
                    if(this.gardenArrayReference[i][j].getClass().equals(Tree.class))
                        this.garden.addPlant(i,j,new Plant());
                }
            }
        }
        else if(command.length==2)
        {
            String growthArg = command[1];
            if(growthArg.charAt(0) == '(')
            {
                System.out.println("> CUT "+growthArg);
                System.out.println();
                int tempR = Character.getNumericValue(growthArg.charAt(1));
                int tempC = Character.getNumericValue(growthArg.charAt(3));
                boolean isOutOfBounds = tempR>this.gardenRow || tempR<0 || tempC>this.gardenCol || tempC<0;
                if(isOutOfBounds)
                {
                    System.out.println("Can't cut there.");
                    System.out.println();
                    return;
                }
                if(!isOutOfBounds && this.gardenArrayReference[tempR][tempC].getClass().equals(Tree.class))
                    this.gardenArrayReference[tempR][tempC] = new Plant();
                else
                {
                    System.out.println("Can't cut there.");
                    System.out.println();
                }
            }
            else
            {
                System.out.println("> CUT "+growthArg);
                System.out.println();
                replacePlantsByName(growthArg);
            }
        }
    }

    /**
     * This method replaces plants with the same name with default Plant obejcts
     * @param growthArg the name of which plants to replace
     */
    private void replacePlantsByName(String growthArg) {
        for(int i = 0; i<this.gardenArrayReference.length;i++)
        {
            for(int j = 0; j<this.gardenArrayReference[i].length;j++)
            {
                //Removes all plants by this name
                if(growthArg.equals(this.gardenArrayReference[i][j].getName()))
                    this.garden.addPlant(i,j,new Plant());
            }
        }
    }

}
