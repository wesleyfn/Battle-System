package config;

import personagens.Heroi;

public class Estagio
{
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
        "Chefe Goblin", 
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
                descricao = " \""+heroi.getNome()+" mantenha o foco, muitos bravos guerreiros se perderam nesse local.\n";
                descricao += " Chegar ao final do labirinto não será uma tarefa fácil.\"";
                iniciarInimigos("Orc", "Orc de Elite");
                break;
            case 5:
                nome = "Deserto Não Verdejante";
                descricao = " \"Um horizonte seco e vazio, espera... vazio?"+".\n  ";
                descricao += heroi.getNome()+" não baixe a guarda, o perigo pode estar perto!\"";
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

        if (moeda == 1) {
            switch (heroi.getEstagioAtual()) {
                case 2:
                    heroi.novaArma("Espada Longa", 5);
                    heroi.novaArmadura("Couraça", 3);
                    break;
                case 3:
                    heroi.novaArma("Cimitarra", 8);
                    heroi.novaArmadura("Armadura de Ferro", 5);
                    heroi.novaPocao('M');
                    break;
                case 4:
                    heroi.novaArma("Espada Ferro Vermelho", 10);
                    heroi.novaArmadura("Armadura Escama do Dragão", 7);
                    break;
                case 5:
                    heroi.novaArma("Espada da Lua Cheia", 12);
                    heroi.novaArmadura("Armadura Aço Negro", 9);
                    break;
                case 6:
                    heroi.novaArma("Espada Rúnica", 15);
                    heroi.novaArmadura("Armadura Rúnica", 12);
                    heroi.novaPocao('G');
                    break;
                default:
                    break;
            }
        }
    }
}
