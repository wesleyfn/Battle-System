package itens;

import java.io.Serializable;

public abstract class Itens implements Serializable
{
    protected String nome;
    protected int quantidade;
    protected int atributo;

    public String getNome() {
        return nome;
    }

    public int getAtributo() {
        return atributo;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
