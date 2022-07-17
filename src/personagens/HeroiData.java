package personagens;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HeroiData 
{
    private static String filename = "save.dat";

    public static boolean salvar(Heroi heroi) {
        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filename))){
            file.writeObject(heroi);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Heroi carregar() 
    {
        Heroi heroi;
        try (ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename))){
            heroi = (Heroi) file.readObject();
            return heroi;
        } 
        catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }
}
