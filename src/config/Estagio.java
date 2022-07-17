package config;

import personagens.Heroi;

public class Estagio
{
    private String[] nome = {
        " ",
        "Fogueira", 
        "Colinas Verdejantes", 
        "Floresta Sempre Noturna", 
        "Labirinto da Perdição", 
        "Deserto Não Verdejante", 
        "Caverna Ossos Vívidos"
    };
    private String[] inimigos = {
        "Goblin", 
        "Goblin", 
        "Goblin", 
        "Goblin", 
        "Soldado Goblin"
    };
    private String descricao;


    public Estagio(int estagio_escolhido, Heroi heroi)
    {    
        switch (estagio_escolhido)
        {
            case 1:
                descricao = " \""+heroi.getNome()+" descansa no calor da fogueira. Se recupera\n";
                descricao += " completamente e, misteriosamente, sua poção está cheia\"";
                break;
            case 2:
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 3:
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 4:
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 5:
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 6:
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            default:
                break;
        }
    }

    public String getDescricao() { return descricao; }
    public String getNome(int i) { return nome[i]; }
    public String getInimigo(int i) { return inimigos[i]; }
    
    public void iniciarInimigos(String inimigo1, String inimigo2) 
    {
        inimigos[0] = inimigo1;
        inimigos[1] = inimigo1;
        inimigos[2] = inimigo1;
        inimigos[3] = inimigo1;
        inimigos[4] = inimigo2;
    }

    
}
