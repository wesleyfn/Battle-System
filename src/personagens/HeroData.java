package personagens;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import itens.*;

public class HeroData 
{
    private static String filename = "save.dat";

    public static boolean salvar(Hero heroi) {
        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filename))){
            file.writeObject(heroi);
            file.writeObject(Backpack.getInventario());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static Hero carregar() 
    {
        Hero heroi;
        try (ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename))){
            heroi = (Hero) file.readObject();
            Backpack.setInventario((List<Itens>) file.readObject());
            return heroi;
        } 
        catch (ClassNotFoundException | IOException e) {
            return null;
        }

    }

}
