import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
	//File input = new File(args[0]);
    //Scanner scanner = new Scanner(input);
    //Logic here to read file commands

        Plant defaultPlant = new Plant();
        Plant cedarTree = new Tree("Cedar");
        Plant tulip = new Flower("Tulip");
        Plant zucchini = new Vegetable("Zucchini");

        Garden garden = new Garden(3,16);

        garden.addPlant(0,1,cedarTree);
        garden.addPlant(1,0,tulip);
        garden.addPlant(1,1,zucchini);
        garden.addPlant(2,0,cedarTree);

        garden.printGarden();

    }
}
