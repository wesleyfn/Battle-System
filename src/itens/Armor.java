package itens;

public class Armor extends Itens
{
    public Armor(String nome, int def) {
        this.nome = nome;
        this.quantidade = 1;
        this.atributo = def;
    }

    public int getDef() {
        return atributo;
    }

    @Override
    public String toString() {
        return String.format("%s 🛡 %d", nome, atributo);
    }
}
