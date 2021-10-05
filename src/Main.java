import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
	    //File input = null;
        //Scanner scanner = new Scanner(input);

        Plant defaultPlant = new Plant();
        Plant cedarTree = new Tree("Cedar");
        Plant tulip = new Flower("Tulip");
        Plant zucchini = new Vegetable("Zucchini");

        Garden garden = new Garden(2,2);

        garden.addPlant(0,0,defaultPlant);
        garden.addPlant(0,1,tulip);
        garden.addPlant(1,0,zucchini);
        garden.addPlant(1,1,cedarTree);

        garden.printGarden();
        System.out.println();

        cedarTree.grow(2);
        zucchini.grow(3);
        tulip.grow(3);

        garden.printGarden();

    }
}
