package itens;

import java.util.ArrayList;
import java.util.List;

public class Backpack 
{
    private static List<Itens> mochila = new ArrayList<>();
    
    public static List<Itens> getInventario() {
        return mochila;
    }

    public static void setInventario(List<Itens> list) {
        Backpack.mochila = list;
    }

    public static void add(Itens...item) {
        for (Itens itens : item) {
            mochila.add(itens);
        }
    }

    public static void remove(int indice) {
        mochila.remove(indice);
    }

    public static String mostrarMochila() {
        int i = 0;
        String str = "";
        for (Itens itens : mochila) {
            str += " - "+itens.toString()+"\n";
            i++;
        }
        return str;
    }
}
