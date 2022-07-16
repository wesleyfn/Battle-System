/*package config;
import java.io.IOException;
import java.util.Scanner;

import itens.*;
import personagens.*;

public class App {
    public static void main(String[] args) throws Exception 
    {
        Scanner scanner = new Scanner(System.in);
        //Itens arma = new Arma("Espada", 6);
        //Itens armadura = new Armadura("Armadura", 2);
        //Itens potion = new Potion();

        //Inventario.add(arma, armadura, potion);


        
        Heroi heroi = null;
        int op;
        boolean novojogo = true;

        do {
            Menus.principal(novojogo);
            op = scanner.nextInt();
            switch (op) 
            {
                case 1: //Iniciar
                    if (novojogo == true) {
                        System.out.print("\n > Digite o nome do Herói: ");
                        heroi = new Heroi(scanner.next()); 
                        //heroi = new Heroi("Herói", 5, 10, 2, 7, 1);
                    }
                    menuHeroi(heroi);
                    novojogo = false;
                    break;
                case 2: //Salvar
                    HeroiSave.salvar(heroi);
                    System.out.println("\n> Dados salvos!");
                    System.out.print("\n > Enter para voltar... ");
                    System.in.read();
                    break;
                case 3: //Carregar
                    heroi = HeroiSave.carregar();
                    System.out.println("\n> Dados carregados!");
                    novojogo = false;
                    System.out.print("\n > Enter para voltar... ");
                    System.in.read();
                    break;
                case 4: //Como Jogar
                    System.out.print("\n > Enter para voltar... ");
                    System.in.read();
                    break;
                case 0: //Sair
                    scanner.close();
                    break;
                default:
                    break;
            }
        } while (op != 0);
    }

    @SuppressWarnings("resource")
    private static void menuHeroi(Heroi heroi) throws IOException 
    {
        int op;
        do{
            Menus.heroi();
            Scanner scanner = new Scanner(System.in);
            op = scanner.nextInt();
            switch (op) 
            {
                case 1: //Estágios
                    int op1;
                    do {
                        Menus.estagios();
                        op1 = scanner.nextInt();
                        switch (op1) {
                            case 1:
                                
                                break;
                        
                            default:
                                break;
                        }
                    } while (op1 != 0);
                    break;
                case 2: //Inventário
                    Inventario.print();
                    System.out.print("\n> Enter para voltar... ");
                    System.in.read();
                    break;
                case 3: //
                    System.out.println("\n"+heroi.mostrarStatus());
                    System.out.print("\n> Enter para voltar... ");
                    System.in.read();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (op != 0);
        
    }
}
*/