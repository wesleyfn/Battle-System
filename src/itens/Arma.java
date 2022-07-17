package itens;

public class Arma extends Itens
{ 
    public Arma(String nome, int atk){
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
