package config;

import java.io.IOException;
import java.util.Scanner;

import personagens.Heroi;
import personagens.HeroiData;
import personagens.Inimigo;

public class GameEngine {
    private static Heroi heroi;
    private static Estagio estagio;
    private static boolean novojogo = true;
    private static Scanner scanner = new Scanner(System.in);
    private static int contadorFogueira = 0;

    public static void cabecalho(String titulo, int n_separador) {
        System.out.println("\n [" + titulo + "]");
        separador(n_separador);
    }

    public static void separador(int n) {
        for (int i = 0; i < n / 2; i++) {
            System.out.print("-");
            System.out.print("=");
        }
        System.out.println("-");
    }

    public static void printMenuPrincipal() {
        cabecalho("RPG", 6);
        System.out.format(" 1) %s", novojogo ? "Novo Jogo\n" : "Continuar\n");
        System.out.print(" 2) Carregar");
        System.out.format("%s", novojogo ? "\n" : "\n 3) Salvar\n");
        System.out.format(" %d) Como Jogar\n", novojogo ? 3 : 4);
        System.out.println(" 0) Sair do Jogo");
        separador(6);
    }

    public static void printMenuEstagios() {
        cabecalho("Escolha um estágio (0 = voltar)", 6);
        System.out.println(" 1) Fogueira");
        System.out.println(" 2) Colinas Verdejantes"); 
        System.out.println(" 3) Floresta Sempre Noturna");
        System.out.println(" 4) Labirinto da Perdição");
        System.out.println(" 5) Deserto não Verdejante");
        System.out.println(" 6) Caverna Ossos Vívidos");
        separador(6);
    }

    public static String barraPVXP(int atual, int max) {
        String barra = "[";
        if (atual == 0)
            barra = "[                                   ";
        else {
            double p = ((max - atual) / (double) atual) + 1;
            for (int i = 0; i < 35; i++) {
                if (i < Math.floor(35 / p))
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
        System.out.println("\n" + heroi.toStringInventario());
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
        } catch (IOException e) {
        }
    }

    private static void batalha(Inimigo inimigo) {
        int op;
        limparConsole();
        cabecalho(inimigo.getNome() + " aparece!", 6);
        do {
            System.out.println(" " + inimigo.getNome());
            System.out.println(barraPVXP(inimigo.getPvAtual(), inimigo.getPvMax()) + "\n");

            System.out.println(heroi.toStringPV());
            System.out.println(barraPVXP(heroi.getPvAtual(), heroi.getPvMax()));
            separador(6);
            System.out.println(" 1) Atacar  2) " + heroi.getInventario().get(2).toString() + "  0) Correr");
            separador(6);
            op = opcao(" > ", 2);

            limparConsole();
            if (op == 0) {
                int moeda = (int) (Math.random() * 5);
                if (moeda == 0) {
                    cabecalho(estagio.getSubstagio(heroi.getEstagioAtual()-1, heroi.getDistancia()-1), 6);
                    System.out.println("\"" + heroi.getNome() + " fugiu covardemente...\"");
                    separador(6);
                    break;
                } 
                else {
                    int ataqueInimigo = inimigo.ataque();
                    heroi.subtrairPv(ataqueInimigo - heroi.defesa());
                    cabecalho("A criatura percebeu! Você recebeu "+ataqueInimigo+" de dano!", 6);
                
                    // condição caso o heroi morra
                    if (heroi.getPvAtual() < 1) {
                        limparConsole();
                        heroi.reiniciarDistancia();
                        heroi.setEstagioAtual(1);
                        cabecalho(heroi.getNome()+" morreu em batalha...", 6);
                    }
                }
            }
            else if (op == 1) {
                int ataqueHeroi = heroi.ataque(); 
                int ataqueInimigo = inimigo.ataque();

                inimigo.subtrairPv(ataqueHeroi - inimigo.getDef());
                heroi.subtrairPv(ataqueInimigo - heroi.defesa());

                cabecalho(heroi.getNome()+" causa "+ataqueHeroi+" de dano e recebe "+ataqueInimigo+"!", 6);
                
                // condição caso o heroi morra
                if (heroi.getPvAtual() < 1) {
                    limparConsole();
                    heroi.reiniciarDistancia();
                    heroi.setEstagioAtual(1);
                    cabecalho(heroi.getNome()+" morreu em batalha...", 6);
                }
                else if (inimigo.getPvAtual() < 1) {

                    if (heroi.getDistancia() == 6){
                        if (heroi.getEstagioAtual() == 6){
                            limparConsole();
                            cabecalho("Parabéns " + heroi.getNome() + "," + " o seu ápice foi alcançado!", 6);
                            System.out.println(" \""+heroi.getNome()+" utilizou a fogueira "+contadorFogueira+" vezes.\"");
                            separador(6);
                            enterContinua();    
                        }
                        else{
                            estagio.dropBoss(heroi);
                            cabecalho("O inimigo deixou algo cair...", 6);
                            System.out.println("\"O Herói adquiriu " +heroi.toStringInventario()+  "\"");
                            System.out.println(" Ao eliminar o " +estagio.getBossInimigos(heroi.getEstagioAtual()-1)+ ".\"");
                            separador(6);
                            enterContinua();
                        }  
                    }
                    limparConsole();
                    cabecalho(inimigo.getNome()+" derrotado. "+inimigo.getXP()+" de XP adquiridos", 6);
                    heroi.adicionarXp(inimigo.getXP());
                }
            } 
            else if (op == 2) {
                int ataqueI = 1 + (int) (Math.random() * inimigo.ataque());
                int cura = heroi.getInventario().get(2).getAtributo();
                if (cura == 0)
                    cabecalho("Poção vazia!", 6);
                else {
                    heroi.adicionarPv(cura);
                    heroi.subtrairPv(ataqueI);
                    cabecalho("Você bebe a poção e recebe " + ataqueI + " de dano!", 6);
                }
            }
        } while (heroi.getPvAtual() > 0 && inimigo.getPvAtual() > 0);
    }

    public static void gerarEncontro() {
        int op;
        limparConsole();
        cabecalho(estagio.getSubstagio(heroi.getEstagioAtual()-2, heroi.getDistancia()-1), 6);
        do {
            int ramdomNumber = (int) (Math.random()*5);
            String nomeInimigo = estagio.getInimigo(ramdomNumber);
            System.out.println("\"" + heroi.getNome() + " encontrou "+nomeInimigo+". Derrote-a Herói!\"");
            separador(6);
            System.out.println(" 1) Lutar  2) Correr");
            separador(6);
            op = opcao(" > ", 2);

            
            if (heroi.getDistancia() == 6){
                nomeInimigo = estagio.getBossInimigos(heroi.getEstagioAtual()-1);
            }
            
            if (op == 1) {
                if(heroi.getDistancia() == 6)
                    batalha(new Inimigo(nomeInimigo, 6 * heroi.getEstagioAtual()));
                else if (ramdomNumber == 4)
                    batalha(new Inimigo(nomeInimigo, 4 * heroi.getEstagioAtual()));
                else 
                    batalha(new Inimigo(nomeInimigo, 2 * heroi.getEstagioAtual()));
                enterContinua();
            } 
            else if (op == 2) {
                limparConsole();
                int moeda = (int) (Math.random()*2);
                if (moeda == 0) {
                    cabecalho(estagio.getSubstagio(heroi.getEstagioAtual()-1, heroi.getDistancia()-1), 6);
                    System.out.println("\"" + heroi.getNome() + " fugiu covardemente...\"");
                    separador(6);
                    enterContinua();
                } 
                else {
                    cabecalho("A criatura estava atenta!", 6);
                    enterContinua();
                    limparConsole();
                    batalha(new Inimigo(nomeInimigo, 5));
                }
            }
            limparConsole();
            
        } while (op != 1 && op != 2 && heroi.getPvAtual() > 0);
    }

    private static void menuEstagio(int estagio_escolhido) {
        int op;
        do {
            String isFogueira = estagio_escolhido != 1 ? estagio.getNome() : "Fogueira, usos: "+contadorFogueira;
            limparConsole();
            
            estagio = new Estagio(estagio_escolhido, heroi);


            cabecalho(isFogueira, 6);
            System.out.println(estagio.getDescricao());

            separador(6);
            System.out.print(" 1) Andar  2) Status&Mochila  0) Voltar (Estágios)");
            op = opcao(": ", 2);
                
            if (op == 1) {
                if (estagio_escolhido == 1) {
                    limparConsole();
                    cabecalho(estagio.getNome(), 6);
                    System.out.println(" \"" + heroi.getNome() + " anda ao redor da fogueira, por algum motivo.\"");
                    separador(6);
                    enterContinua();
                } 
                else {
                    heroi.movimentar();
                    gerarEncontro();
                    if (heroi.getDistancia() == 6) {
                        heroi.reiniciarDistancia();
                    }
                }
            } 
            else if (op == 2) {
                limparConsole();
                printStatus("Status&Mochila");
                enterContinua();
            }
        } while (op != 0 && heroi.getPvAtual() > 0);
    }

    public static void localHeroi(int estagio_escolhido) {
        int estagioAnterior = 1;
        do {    
            switch (estagio_escolhido) {
                case 1: // Fogueira
                    heroi.encherPotion();
                    heroi.adicionarPv(heroi.getPvMax());
                    contadorFogueira++;
                    menuEstagio(estagio_escolhido);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    if (estagioAnterior != estagio_escolhido){
                        heroi.reiniciarDistancia();
                    }
                    menuEstagio(estagio_escolhido);
                    break;
                default:
                    break;
            }

            if (estagio_escolhido != 1)
                estagioAnterior = heroi.getEstagioAtual();
                
            limparConsole();
            printMenuEstagios();
            estagio_escolhido = heroi.getPvAtual() > 0 ? opcao(" > ", 6) : 1;
            heroi.setEstagioAtual(estagio_escolhido);
        } while (estagio_escolhido != 0);
    }

    public static void iniciarJogo() {
        limparConsole();
        cabecalho("RPG", 40);
        System.out.println(" RPG por Wesley Nascimento e Vitor Hugo\n");
        enterContinua();

        int op;
        do {
            limparConsole();
            printMenuPrincipal();
            op = opcao(" > ", novojogo ? 3 : 4);
            switch (op) {
                case 1: // Novo Jogo/Continuar
                    int op1;
                    if (novojogo == true) {
                        do {
                            limparConsole();
                            cabecalho("Qual é o nome do Herói?", 6);
                            System.out.print(" > ");
                            String nomeHeroi = scanner.next();
                            heroi = new Heroi(nomeHeroi);
                            if (nomeHeroi.compareTo("MOOD007") == 0){
                                heroi.addArma("Espada Milenar", 100);
                                heroi.addArmadura("Armadura Milenar", 100);
                                heroi.addPocao('G');
                            }
                            cabecalho("O nome do Herói é " + heroi.getNome() + ". Correto?", 6);
                            System.out.println(" 1) Sim!\n 0) Não, quero renomear o Herói.");
                            op1 = opcao(" > ", 2);
                        
                        } while (op1 != 1);
                    }
                    novojogo = false;
                    localHeroi(heroi.getEstagioAtual());
                    break;
                case 2: // Carregar
                    limparConsole();
                    heroi = HeroiData.carregar();
                    if (heroi != null) {
                        limparConsole();
                        printStatus("Dados do Herói carregados!");
                        novojogo = false;
                        estagio = new Estagio(1, heroi);
                    } 
                    else {
                        cabecalho("Não há dados do Herói salvos!", 6);
                    }
                    enterContinua();
                    break;
                case 3: // Salvar
                    if (novojogo == false) {
                        limparConsole();
                        if (HeroiData.salvar(heroi)) {
                            cabecalho("Dados do Herói salvos!", 6);
                        }
                        else {
                            cabecalho("Ocorreu um erro!\n", 6);
                        }
                        enterContinua();
                        break;
                    }
                case 4: // Sair
                    limparConsole();
                    cabecalho("Como Jogar", 10);
                    System.out.println("  - Em Novo Jogo será possível criar seu Herói.");
                    System.out.println("  - Se já tiver algum Herói guardado, em Carregar poderá recuperá-lo.");
                    System.out.println("  - Utilize o mapa Fogueira para recuperar vida e recarregar a poção.");
                    System.out.println("  - Se o seu personagem morrer, o progresso do estagio será perdido.");
                    System.out.println("  - Ao selecionar a opção Correr, se obter sucesso, o heroi avança.");
                    System.out.println("  - Os chefes estão no final de cada estágio, derrote-os para adquirir novos itens.");
                    System.out.println("  - O objetivo é chegar ao final com a menor quantidade de usos da Fogueira.");
                    separador(10);
                    enterContinua();
                    break;
                default:
                    break;
            }
        } while (op != 0);
    }
}
