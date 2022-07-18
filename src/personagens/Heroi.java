package personagens;

import java.util.ArrayList;
import java.util.List;

import itens.Armadura;
import itens.Itens;
import itens.Pocao;
import itens.Arma;

public class Heroi extends Character
{
    private int estagioAtual;
    private int xpMax, nivel, distancia;
    private List<Itens> inventario = new ArrayList<Itens>();

    public Heroi(String nome) {
        this(nome, 15, 3, 2);
    }

    private Heroi(String nome, int pv, int atk, int def) 
    {
        super(nome, pv, atk, def);
        this.xp = 0;
        xpMax = 10;
        nivel = 1;
        distancia = 1;
        estagioAtual = 1;

        inventario.add(new Arma("Adaga", 1));
        inventario.add(new Armadura("Couraça", 1));
        inventario.add(new Pocao());
    }

    public int getXpAtual() { return xp; }
    public int getXpMax() { return xpMax; }
    public int getDistancia() { return distancia; }
    public int getEstagioAtual() { return estagioAtual; }
    public List<Itens> getInventario() { return inventario; }

    public void addArma (String nome, int atributo){
        ((Arma) inventario.get(0)).setArma(nome, atributo);
    }

    public void addArmadura (String nome, int atributo){
        ((Armadura) inventario.get(1)).setArmadura(nome, atributo);
    }

    public void addPocao (char tamanho){
        ((Pocao) inventario.get(2)).setPocao(tamanho);
    }

    public int ataque() {
        return atk + ((Arma) inventario.get(0)).getAtk();
    }

    public int defesa() {
        return def + ((Armadura) inventario.get(1)).getDef();
    }

    public void setEstagioAtual(int estagioAtual) {
        this.estagioAtual = estagioAtual;
    }

    public void reiniciarDistancia() {
        this.distancia = 0;
    }

    public void adicionarXp(int add) {
        xp += add;
        if (xp >= xpMax) {
            xp = xp - xpMax;
            xpMax = Math.round(xpMax * 2.1f);
            pvMax = Math.round(pvMax * 1.4f);
            atk = Math.round(atk * 1.6f);
            def = Math.round(def * 1.6f);
            nivel++;
            System.out.println(" \"Parabéns Você Ficou Mais forte!");
            System.out.println(" Seu nível agora é: "+nivel+"\"\n");
        }
    }

    public void movimentar() {
        distancia++;
    }

    public void encherPotion() {
        ((Pocao) inventario.get(2)).setQuantidade(3);
    }

    public String toStringInventario() {
        String str = ((Arma) inventario.get(0)).toString();
        str += ", "+ ((Armadura) inventario.get(1)).toString();
        str += ", "+ ((Pocao) inventario.get(2)).toString();
        return str;
    }

    //mostra os PVs com a barra formatados
    public String toStringPV() {
        String str = String.format(" %-24s PV:%3d/%3d", nome, pvAtual, pvMax);
        return str;
    }

    //mostra o XP com a barra formatados
    public String toStringXP() {
        String str = String.format(" Nível: %-17d XP:%3d/%3d", nivel, xp, xpMax);
        return str;
    }

    //mostra os atributos
    public String toStringAtributos() {
        return " ATK: "+ataque()+" DEF: "+defesa();
    }


}
