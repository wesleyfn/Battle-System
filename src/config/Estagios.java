package config;

import personagens.Heroi;

public class Estagios
{
    private static String nome;
    private static String descricao;
    private static int estagio;
    private static int tamanho = 5;
    private static String[] inimigos = {"Goblin", "Goblin", "Goblin", "Goblin", "Soldado Goblin"};

    public static void selecionarEstagio(int estagio_escolhido, Heroi heroi)
    {    
        switch (estagio_escolhido)
        {
            case 1:
                nome = "Fogueira";
                estagio = estagio_escolhido;
                descricao = " \""+heroi.getNome()+" descansa no calor da fogueira. Se recupera";
                descricao += " completamente e, misteriosamente, sua poção está cheia\"";
                break;
            case 2:
                nome = "estagio2";
                estagio = estagio_escolhido;
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 3:
                nome = "estagio3";
                estagio = estagio_escolhido;
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 4:
                nome = "estagio4";
                estagio = estagio_escolhido;
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 5:
                nome = "estagio5";
                estagio = estagio_escolhido;
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 6:
                nome = "estagio6";
                estagio = estagio_escolhido;
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            default:
                break;
        }
    }

    public static String getDescricao() { return descricao; }
    public static int getEstagio() { return estagio; }
    public static String getNome() { return nome; }
    public static int getTamanho() { return tamanho; }
    public static String[] getInimigos() { return inimigos; }

    public static void iniciarInimigos(String inimigo1, String inimigo2) 
    {
        inimigos[0] = inimigo1;
        inimigos[1] = inimigo1;
        inimigos[2] = inimigo1;
        inimigos[3] = inimigo1;
        inimigos[4] = inimigo2;
    }
}
