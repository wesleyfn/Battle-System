package itens;

public class Armadura extends Itens
{
    public Armadura(String nome, int def) {
        this.nome = nome;
        this.quantidade = 1;
        this.atributo = def;
    }

    public void setArmadura(String nome, int atributo) {
        this.nome = nome;
        this.atributo = atributo;
    }

    public int getDef() {
        return atributo;
    }

    @Override
    public String toString() {
        return String.format("%s 🛡 %d", nome, atributo);
    }
}
