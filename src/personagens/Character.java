package personagens;

import java.io.Serializable;

public abstract class Character implements Serializable
{
    protected String nome;
    protected int pvAtual, pvMax, xpAtual, atk, def;

    public Character(String nome, int pvAtual, int pvMax, int xp, int atk, int def) {
        this.nome = nome;
        this.pvAtual = pvAtual;
        this.pvMax = pvMax;
        this.xpAtual = xp;
        this.atk = atk;
        this.def = def;
    }

    public void adicionarPv(int add) {
        pvAtual = pvAtual + add;
        if(pvAtual > pvMax) pvAtual = pvMax;
    }

    public void subtrairPv(int sub) {
        adicionarPv(-sub);
        if(pvAtual < 0) pvAtual = 0;
    }
}
