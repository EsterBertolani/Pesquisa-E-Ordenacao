package Utilitarios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TratamentoErros {

    public static int lerInteiro(Scanner sc, String msg) {
        int valor;
        while (true) {
            try {
                System.out.print(msg);
                valor = sc.nextInt();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                sc.nextLine();
            }
        }
    }
}

