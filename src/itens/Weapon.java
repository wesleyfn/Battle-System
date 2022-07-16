package itens;

public class Weapon extends Itens
{ 
    public Weapon(String nome, int atk) {
        this.nome = nome;
        this.quantidade = 1;
        this.atributo = atk;
    }

    public int getAtk() {
        return atributo;
    }

    @Override
    public String toString() {
        return String.format("%s 🗡 %d", nome, atributo);
    }
}
