package config;

import java.io.IOException;
import java.util.Scanner;

import personagens.Heroi;
import personagens.HeroiData;
import personagens.Inimigo;

public class GameEngine 
{   
    protected static Heroi heroi;
    protected static Estagio estagio;
    protected static boolean novojogo = true;
    protected static Scanner scanner = new Scanner(System.in);

    public static void cabecalho(String titulo, int n_separador) {
        System.out.println("\n ["+titulo+"]");
        separador(n_separador);
    }

    public static void separador(int n) {
        for (int i = 0; i < n/2; i++) {
            System.out.print("-");
            System.out.print("=");
        }
        System.out.println("-");
    }

    public static void printMenuPrincipal() 
    {
        cabecalho("RPG", 6);
        System.out.format("  1) %s", novojogo ? "Novo Jogo\n" : "Continuar\n");
        System.out.print("  2) Carregar");
        System.out.format("%s", novojogo ? "\n" : "\n  3) Salvar\n");
        System.out.format("  %d) Como Jogar\n", novojogo ? 3 : 4);
        System.out.println("  0) Sair do Jogo");
        separador(6);
    }

    public static void printMenuEstagios() 
    {
        cabecalho("Escolha um estágio (0 = voltar)", 6);
        for (int i = 1; i < 7; i++) {
            System.out.format("  %d) %s\n", i, estagio.getNome(i));
        }
        separador(6);
    }

    public static String barraPVXP(int atual, int max) 
    {
        String barra = "[";
        if (atual == 0)
            barra = "[                                   ";
        else { 
            double p = ((max - atual) / (double) atual) + 1;
            for (int i = 0; i < 35; i++) {
                if (i < Math.floor(35/p))
                    barra += "∎";
                else
                    barra += " ";
            }
        }
        barra += "]";
        return barra;
    }

    private static void printStatus(String titulo) {
        cabecalho(titulo, 6);
        System.out.println(heroi.toStringPV());
        System.out.println(barraPVXP(heroi.getPvAtual(), heroi.getPvMax())); 
        System.out.println(heroi.toStringXP());
        System.out.println(barraPVXP(heroi.getXpAtual(), heroi.getXpMax()));
        System.out.println(heroi.toStringAtributos());
        System.out.println("\n"+heroi.toStringInventario());
        separador(6);
    }

    public static int opcao(String comecoLinha, int escolhas) {
        int op_escolhida;
        do {
            System.out.print(comecoLinha);
            try {
                op_escolhida = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                op_escolhida = -1;
            }
        } while (op_escolhida < 0 || op_escolhida > escolhas);
        return op_escolhida;
    }

    public static void limparConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void enterContinua() {
        System.out.print("> Enter para continuar... ");
        try {
            System.in.read();
        } catch (IOException e) {}
    }
    
    private static void batalha(Inimigo inimigo) {
        int op;
        limparConsole();
        cabecalho(inimigo.getNome()+" aparece!", 6);
        do {
            System.out.println(" "+inimigo.getNome());
            System.out.println(barraPVXP(inimigo.getPvAtual(), inimigo.getPvMax())+"\n");
            
            System.out.println(heroi.toStringPV());
            System.out.println(barraPVXP(heroi.getPvAtual(), heroi.getPvMax()));
            separador(6);
            System.out.println(" 1) Atacar  2) "+heroi.getInventario().get(2).toString()+"  0) Correr");
            separador(6);
            op = opcao(" > ", 2);

            limparConsole();
            if (op == 1) {
                int ataqueH = 1 + (int) (Math.random() * heroi.ataque());
                int ataqueI = 1 + (int) (Math.random() * inimigo.ataque());
                
                //configurar turno de ataque (quem ataca primeiro)
                //condição caso o heroi morra
                inimigo.adicionarPv(-ataqueH);
                heroi.adicionarPv(-ataqueI);
                cabecalho(heroi.getNome()+" causa "+ataqueH+" de dano e recebe "+ataqueI+"!", 6);
                if (inimigo.getPvAtual() < 1) 
                    heroi.adicionarXp(inimigo.getXP());
            }
            else if (op == 2) {
                int ataqueI = 1 + (int) (Math.random() * inimigo.ataque());
                int cura = heroi.getInventario().get(2).getAtributo();
                if (cura == 0)
                    cabecalho("Poção vazia!", 6);
                else {
                    heroi.adicionarPv(cura);
                    heroi.adicionarPv(-ataqueI);
                    cabecalho("Você bebe a poção e recebe "+ataqueI+" de dano!", 6);
                }
            }
        } while (heroi.getPvAtual() > 0 && inimigo.getPvAtual() > 0 && op != 0);
    }
    
    public static void gerarEncontro() {
        int op;
        limparConsole();
        cabecalho("subestagio"+heroi.getDistancia(), 6);
        do {
            System.out.println("\""+heroi.getNome()+" encontrou uma criatura. Derrote-a Herói!\"");
            separador(6);
            System.out.println(" 1) Lutar  2) Correr");
            separador(6);
            op = opcao(" > ", 2);

            String nomeInimigo = estagio.getInimigo((int) (Math.random()*5));
            if (op == 1) {
                batalha(new Inimigo(nomeInimigo, 5));
                enterContinua();
            } 
            else if (op == 2) {
                limparConsole();
                int moeda = 1 + (int) (Math.random()*2);
                if (moeda == 1) {
                    cabecalho("subestagio"+heroi.getDistancia(), 6);
                    System.out.println("\""+heroi.getNome()+" fugiu covardemente...\"");
                    separador(6);
                    enterContinua();
                }
                else {
                    cabecalho("A criatura estava atenta!", 6);
                    limparConsole();
                    batalha(new Inimigo(nomeInimigo, 5));
                }
            }
            limparConsole();
        } while (op != 1 && op != 2);
    }

    private static void menuEstagio(int estagio_escolhido) {
        int op;
        do {
            limparConsole();
            estagio = new Estagio(estagio_escolhido, heroi);
            
            cabecalho(estagio.getNome(estagio_escolhido), 6);
            System.out.println(estagio.getDescricao());

            separador(6);
            System.out.print(" 1) Andar  2) Status&Mochila  0) Voltar (Estágios)");
            op = opcao(": ", 2);

            if (op == 1) {
                if (estagio_escolhido == 1) {
                    limparConsole();
                    cabecalho(estagio.getNome(estagio_escolhido), 6);
                    System.out.println(" \""+heroi.getNome()+" anda ao redor da fogueira, por algum motivo.\"");
                    separador(6);
                    enterContinua();
                }
                else {
                    heroi.movimentar();
                    gerarEncontro();
                    if (heroi.getDistancia() == 5) {
                        heroi.reiniciarDistancia();
                        return;
                    }
                }
            }
            else if (op == 2) {
                limparConsole();
                printStatus("Status&Mochila");
                enterContinua();
            }
        } while (op != 0);
        heroi.setEstagioAtual(estagio_escolhido);
    }

    public static void localHeroi(int estagio_escolhido) {
        do {
            switch (estagio_escolhido) {
                case 1: //Fogueira
                    heroi.encherPotion();
                    heroi.adicionarPv(heroi.getPvMax());
                    menuEstagio(estagio_escolhido);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    menuEstagio(estagio_escolhido);
                    break;
                default:
                    break;
            }
            limparConsole();
            printMenuEstagios();
            estagio_escolhido = opcao(" > ", 6);
        } while (estagio_escolhido != 0);
    }

    public static void iniciarJogo() 
    {
        limparConsole();
        cabecalho("RPG", 40);
        System.out.println(" RPG por Wesley Nascimento e Vitor Hugo\n");
        enterContinua();

        int op;
        do {
            limparConsole();
            printMenuPrincipal();
            op = opcao(" > ",novojogo ? 3 : 4);
            switch (op) {
                case 1: //Novo Jogo/Continuar
                    int op1;
                    if (novojogo == true) {
                        do {
                            limparConsole();
                            cabecalho("Qual é o nome do Herói?", 6);
                            System.out.print(" > ");
                            heroi = new Heroi(scanner.next()); 

                            cabecalho("O nome do Herói é "+heroi.getNome()+". Correto?", 6);
                            System.out.println(" 1) Sim!\n 0) Não, quero renomear o Herói.");
                            op1 = opcao(" > ",2);
                        } while (op1 != 1);
                    }
                    novojogo = false;
                    localHeroi(heroi.getEstagioAtual());
                    break;
                case 2: //Carregar
                    limparConsole();
                    heroi = HeroiData.carregar();
                    if(heroi != null) {
                        limparConsole();
                        printStatus("Dados do Herói carregados!");
                        novojogo = false;
                    }
                    else 
                        cabecalho("Não há dados do Herói salvos!", 6);
        
                    enterContinua();
                    break;
                case 3: //Salvar
                    if (novojogo == false) {
                        limparConsole();
                        if(HeroiData.salvar(heroi)) 
                            cabecalho("Dados do Herói salvos!", 6);
                        else 
                            cabecalho("Ocorreu um erro!\n", 6);
                        enterContinua();
                        break;
                    }
                case 4: //Sair
                    limparConsole();
                    cabecalho("Como Jogar", 6);
                    System.out.println(" Para jogar primeiramente é preciso criar seu Herói:");
                    System.out.println("  - Em \"Novo Jogo\" será possível criar seu Herói.");
                    System.out.println("  - Se já tiver algum Herói guardado, em \"Carregar\" poderá recuperá-lo.");
                    separador(6);
                    enterContinua();
                    break;
                default:
                    break;
            } 
        } while (op != 0);
    }
}
