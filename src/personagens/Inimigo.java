package personagens;

public class Inimigo extends Character
{
    public Inimigo(String nome, int xp) {
        super(nome, (xp*2), 1, 1);
        this.xp = xp;
    }

    public int getXP() { return xp; }
}
