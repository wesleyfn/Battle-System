package personagens;

import java.io.ObjectOutputStream.PutField;

import config.GameEngine;
import itens.Armor;
import itens.Backpack;
import itens.Potion;
import itens.Weapon;

public class Hero extends Character
{
    Weapon arma;
    Armor armadura;
    Potion potion;
    private int estagioAtual;
    private int xpMax, nivel, distancia;

    public Hero(String nome) {
        this(nome, 10, 10, 0, 1, 1);
    }

    public Hero(String nome, int pvAtual, int pvMax, int xp, int atk, int def) 
    {
        super(nome, pvAtual, pvMax, xp, atk, def);
        xpMax = 10;
        nivel = 1;
        distancia = 1;

        arma = new Weapon("Espada Quebrada", 2);
        armadura = new Armor("Farrapos", 1);
        potion = new Potion('P');

        this.atk += arma.getAtk();
        this.def += armadura.getDef();
        
        Backpack.add(arma, armadura, potion);
    }

    public int getPvAtual() {
        return pvAtual;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getXpAtual() {
        return xpAtual;
    }

    public int getXpMax() {
        return xpMax;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarXp(int add) {
        xpAtual += add;
        if (xpAtual >= xpMax) {
            xpAtual = 0;
            xpMax = Math.round(xpMax * 2.2f);
            pvMax = Math.round(xpMax * 1.5f);
            nivel++;
        }
    }

    public void movimentar() {
        distancia++;
        if (distancia == 10) distancia = 1;
    }

    public void mostrarMochila() {
        System.out.println("- "+ arma.toString());
        System.out.println("- "+ armadura.toString());
        System.out.println("- "+ potion.toString());
    }

    public void encherPotion() {
        potion.setQuantidade(3);
    }

    public void setEstagioAtual(int estagioAtual) {
        this.estagioAtual = estagioAtual;
    }

    public int getEstagioAtual() {
        return estagioAtual;
    }
/*
    public void equiparArma(Arma arma) {
        //atk + atk da arma
        this.atk += Inventario.getInventario().get(0).getAtributo();
    }

    public void equiparArmadura(Armadura armadura) {
        //def + def da armadura
        this.def += Inventario.getInventario().get(1).getAtributo();
    }
*/
    //mostra os PVs com a barra formatados
    public String mostrarPV() {
        String str = String.format(" %-24s PV:%3d/%3d", nome, pvAtual, pvMax);
        //str += String.format("%s\n", GameEngine.barraPVXP(pvAtual, pvMax)); 
        return str;
    }

    //mostra o XP com a barra formatados
    public String mostrarXP() {
        String str = String.format(" Nível: %-17d XP:%3d/%3d", nivel, xpAtual, xpMax);
        //str += String.format("%s\n", GameEngine.barraPVXP(xpAtual, xpMax));
        return str;
    }

    //mostra os atributos
    public String mostrarAtributos() {
        return " ATK: "+atk+" DEF: "+def;
    }

    public String mostrarStatus() {
        return mostrarPV() + mostrarXP() + mostrarAtributos();
    }
}
