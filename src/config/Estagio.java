package config;

import personagens.Heroi;

public class Estagio
{
    /*private String[] nome = {
        "Fogueira", 
        "Colinas Verdejantes", 
        "Floresta Sempre Noturna", 
        "Labirinto da Perdição", 
        "Deserto Não Verdejante", 
        "Caverna Ossos Vívidos"
    };*/
    private String nome;

    private String[][] subStagios = {
        {"Serpentes Venenosas", "Entrada da Caverna", "Águas Passadas", "Rio das Piranhas", "Mina Secreta", "Esconderijo Dos Goblins" }, 
        {"Arvores Fantasma", "Correntes Instáveis", "Planicie Seca", "Abismo", "Tempestade Eterna", "Torre dos Mortos Vivos"},
        {"Terra do Nunca", "Mar dos Piratas", "Oceano Desconhecido", "Caminho Congelado", "Iceberg", "Triangulo das Bermudas"},
        {"Monte Tsereve", "Base da Montanha", "Floresta da Montanha", "Oasis", "Cidade das Nuvens", "Castelo do Rei Orc",},
        {"Highway to Hell", "Deserto das Almas Perdidas", "Cachoeira de Sangue", "Gruta Demoniaca", "Salão dos Demônios", "Trono do Capeta"}
    };

    private String[] inimigos = {
        "Goblin", 
        "Goblin", 
        "Goblin", 
        "Goblin", 
        "Soldado Goblin"
    };
    private String[] bossInimigos = {
        " ",
        "O Chefe Goblin", 
        "Salazar, O Necromante", 
        "Davy Jones, O Dominador", 
        "Gugrok, O Rei Orc", 
        "Sauron, O Senhor do Inferno"
    };

    private String descricao;


    public Estagio(int estagio_escolhido, Heroi heroi)
    {    
        switch (estagio_escolhido)
        {
            case 1:
                nome = "Fogueira";
                descricao = " \""+heroi.getNome()+" descansa no calor da fogueira. Se recupera\n";
                descricao += " completamente e, misteriosamente, sua poção está cheia.\"";
                break;
            case 2:
                nome = "Colinas Verdejantes";
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Goblin", "Soldado Goblin");
                break;
            case 3:
                nome = "Floresta Sempre Noturna";
                descricao = " \"A escuridão é aliada dos moradores deste local, tome cuidado "+heroi.getNome()+".\n";
                descricao += " Qualquer movimento errado pode ser fatal!\"";
                iniciarInimigos("Esqueleto", "Cavaleiro Fantasma");
                break;
            case 4:
                nome = "Labirinto da Perdição";
                descricao = " \""+heroi.getNome()+ " mantenha o foco, muitos bravos guerreiros se perderam nesse local.\n";
                descricao += " Chegar ao final do labirinto não será uma tarefa fácil.\"";
                iniciarInimigos("Orc", "Orc de Elite");
                break;
            case 5:
                nome = "Deserto Não Verdejante";
                descricao = " \"Um horizonte seco e vazio, espera... vazio?"+".\n";
                descricao += heroi.getNome() + " Não baixe a guarda, o perigo pode estar por perto!\"";
                iniciarInimigos("Harpia", "Minotauro");
                break;
            case 6:
                nome = "Caverna Ossos Vívidos";
                descricao = " \"Um lugar traiçoeiro e verde se aproxima de "+heroi.getNome()+".\n";
                descricao += " Cuidado é necessário, pois não é só o lugar que é verde.\"";
                iniciarInimigos("Quimera", "Leviatã");
                break;
            default:
                break;
        }
    }

    public String getDescricao() { return descricao; }
    public String getNome() { return nome; }
    public String getInimigo(int i) { return inimigos[i]; }
    public String getBossInimigos(int i) { return bossInimigos[i]; }
    
    public void iniciarInimigos(String inimigo1, String inimigo2) 
    {
        inimigos[0] = inimigo1;
        inimigos[1] = inimigo1;
        inimigos[2] = inimigo1;
        inimigos[3] = inimigo1;
        inimigos[4] = inimigo2;
    }

    public String getSubstagio(int estagioAtual, int subStagioAtual) {
        return subStagios[estagioAtual][subStagioAtual];
    }
    
    public void dropBoss(Heroi heroi) {
        int moeda = (int) (Math.random()*2);
        switch (heroi.getEstagioAtual()) {
            case 1:
                if (moeda == 1) {
                    heroi.addArma("Espada Longa", 5);
                    heroi.addArmadura("Couraça", 5);
                }
                break;
            case 2:
                if (moeda == 1) {
                    heroi.addArma("Cimitarra", 10);
                    heroi.addArmadura("Armadura de Ferro", 10);
                }
                    heroi.addPocao('M');
                break;
            case 3:
                if (moeda == 1) {
                    heroi.addArma("Espada Ferro Vermelho", 20);
                    heroi.addArmadura("Armadura Escama do Dragão", 20);
                    break;
                }
            case 4:
                if (moeda == 1) {
                    heroi.addArma("Espada da Lua Cheia", 30);
                    heroi.addArmadura("Armadura Aço Nego", 30);
                }
                break;
            case 5:
                if (moeda == 1) {
                    heroi.addArma("Espada Rúnica", 50);
                    heroi.addArmadura("Armadura Rúnica", 50);
                }
                heroi.addPocao('G');
            default:
                break;
        }
    }
}
