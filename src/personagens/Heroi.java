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
        super(nome, 15, 3, 2, 0);
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

    public void setEstagioAtual(int estagioAtual) {
        this.estagioAtual = estagioAtual;
    }

    public void novaArma(String nome, int atributo){
        ((Arma) inventario.get(0)).setArma(nome, atributo);
    }

    public void novaArmadura(String nome, int atributo){
        ((Armadura) inventario.get(1)).setArmadura(nome, atributo);
    }

    public void novaPocao(char tamanho){
        ((Pocao) inventario.get(2)).setPocao(tamanho);
    }

    public int ataque() {
        return atk + ((Arma) inventario.get(0)).getAtk();
    }

    public int defesa() {
        return def + ((Armadura) inventario.get(1)).getDef();
    }

    public void reiniciarDistancia() {
        this.distancia = 0;
    }

    public void encherPocao() {
        ((Pocao) inventario.get(2)).setQuantidade(3);
    }

    public void movimentar() {
        distancia++;
    }

    public void adicionarXp(int add) {
        xp += add;
        if (xp >= xpMax) {
            nivel++;
            xp = xp - xpMax;
            xpMax = Math.round(xpMax * 2.0f);
            pvMax = Math.round(pvMax * 1.2f);
            if (nivel%2 == 0) {
                atk++;
                def++;
            }
            System.out.println(" \"Seu nível agora é: "+nivel+"\"\n");
        }
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
