package config;

import java.io.IOException;
import java.util.Scanner;

import personagens.Heroi;
import personagens.HeroiData;

public class GameEngine 
{   
    protected static Heroi heroi;
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
        cabecalho("RPG", 40);
        System.out.format("  1) %s\n", novojogo ? "Novo Jogo" : "Continuar");
        System.out.println("  2) Salvar");
        System.out.println("  3) Carregar");
        System.out.println("  4) Como Jogar");
        System.out.println("  0) Sair do Jogo");
        separador(40);
    }

    public static void printMenuEstagios() 
    {
        cabecalho("Escolha um estágio (0 = voltar)", 40);
        System.out.println("  1) Fogueira");
        System.out.println("  2) Colinas Verdejantes");
        System.out.println("  3) Floresta Sempre Noturna");
        System.out.println("  4) Labirinto da Perdição");
        System.out.println("  5) Deserto Não Verdejante");
        System.out.println("  6) Caverna Ossos Vívidos");
        separador(40);
    }

    public static void printMenuSecundario() 
    {
        cabecalho("Derrote seus inimigos, "+heroi.getNome()+"!", 40);
        System.out.println( "  1) Estágios");
        System.out.println( "  2) Mochila");
        System.out.println( "  3) Status");
        System.out.println( "  0) Voltar");
        separador(40);
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

    public static int opcao(String comecoLinha, int escolhas) {
        int op_escolhida;
        do {
            System.out.print(comecoLinha);
            try {
                op_escolhida = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                op_escolhida = -1;
                cabecalho("Por favor digite um inteiro!", 40);
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



    
    public static void estagios(int op_escolhida) {
        do {
            switch (op_escolhida) {
                case 1: //Fogueira
                    int op1;
                    do {
                        limparConsole();
                        heroi.adicionarPv(heroi.getPvMax());
                        heroi.encherPotion();

                        cabecalho("Fogueira", 6);
                        System.out.println(" \""+heroi.getNome()+" descansa no calor da fogueira. Se recupera");
                        System.out.println(" completamente e, misteriosamente, a poção está cheia\"");
                        separador(6);
                        System.out.print(" 1) Status e Mochila\t0) Voltar (Estágios)");
                        op1 = opcao(": ", 1);
    
                        if (op1 == 1) {
                            limparConsole();
                            cabecalho("Status & Mochila", 36);
                            System.out.println(heroi.toStringPV());
                            System.out.println(barraPVXP(heroi.getPvAtual(), heroi.getPvMax())); 
                            System.out.println(heroi.toStringXP());
                            System.out.println(barraPVXP(heroi.getXpAtual(), heroi.getXpMax())); 
                            
                            System.out.println(heroi.toStringInventario());
                            separador(36);
                            enterContinua();
                        }
                    } while (op1 != 0);

                    break;
            
                default:
                    break;
            }
            limparConsole();
            printMenuEstagios();
            op_escolhida = opcao(" > ", 6);
        } while (op_escolhida != 0);
    }

    public static void menuSecundario() 
    {
        int op;
        do {
            limparConsole();
            printMenuSecundario();
            op = opcao(" > ",3);
            switch (op) {
                case 1:
                    printMenuEstagios();
                    estagios(1);
                    break;
                case 2:
                    limparConsole();
                    cabecalho("Mochila", 36);
                    
                    System.out.println(heroi.toStringInventario());
                    separador(36);
                    enterContinua();
                    break;
                case 3:
                    limparConsole();
                    cabecalho("Dados do Herói", 36);
                    System.out.println(heroi.toStringPV());
                    System.out.println(barraPVXP(heroi.getPvAtual(), heroi.getPvMax())); 
                    System.out.println(heroi.toStringXP());
                    System.out.println(barraPVXP(heroi.getXpAtual(), heroi.getXpMax()));
                    System.out.println(heroi.toStringAtributos());
                    enterContinua();
                default:
                    break;
            }
        } while (op != 0);
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
            op = opcao(" > ",4);
            switch (op) {
                case 1: //Novo Jogo/Continuar
                    int op1;
                    if (novojogo == true) {
                        do {

                            limparConsole();
                            cabecalho("Qual é o nome do Herói?", 46);
                            System.out.print("> ");
                            heroi = new Heroi(scanner.next()); 
                            //heroi = new Heroi("Herói", 5, 10, 2, 7, 1);

                            cabecalho("O nome do Herói é "+heroi.getNome()+". Correto?", 46);
                            System.out.println(" 1) Sim!\n 0) Não, quero renomear o Herói.");
                            op1 = opcao(" > ",2);
                            
                        } while (op1 != 1);
                    }
                    novojogo = false;
                    estagios(1);
                    break;
                case 2: //Salvar
                    limparConsole();
                    if(HeroiData.salvar(heroi)) 
                        cabecalho("Dados do Herói salvos!", 36);
                    else 
                        cabecalho("Ocorreu um erro!\n", 36);
                    enterContinua();
                    break;
                case 3: //Carregar
                    limparConsole();
                    heroi = HeroiData.carregar();
                    if(heroi != null) {
                        cabecalho("Dados do Herói carregados!", 6);
                        System.out.println(heroi.toStringPV());
                        System.out.println(barraPVXP(heroi.getPvAtual(), heroi.getPvMax())); 
                        System.out.println(heroi.toStringXP());
                        System.out.println(barraPVXP(heroi.getXpAtual(), heroi.getXpMax()));
                        System.out.println(heroi.toStringAtributos());
                        separador(6);
                        novojogo = false;
                    }
                    else 
                        cabecalho("Ocorreu um erro!", 36);
                    enterContinua();
                    break;
                case 4:
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
