package personagens;

public class Inimigo extends Character
{
    public Inimigo(String nome, int xp) {
        super(nome, (xp*2), xp, (int) xp/2, xp);
    }

    public int getXP() { return xp; }

    public int ataque() {
        return atk;
    }
}
