package natacion;

import java.io.*;
import java.util.*;

public class Main
{   public static void main(String[] args)throws IOException
    {   Vuelo vul=new Vuelo();
        Scanner leer=new Scanner(System.in);
        int opcion=0;
        do
        {   System.out.print("\n Introduce número de opción:\n"
                + "1 = Nuevo registro\n"
                + "2 = Leer archivo maestro\n"
                + "3 = Leer archivo índice\n"
                + "0 = Salir\n"
                + "Opción:");
            opcion=leer.nextInt();
            switch(opcion)
            {   case 1:
                    vul.escribir_Arch_Maestro();
                break;
                case 2:
                    System.out.println("Lectura del archivo maestro\n");
                    vul.leer_secuencial_maestro();
                break;
                case 3:
                    vul.leer_secuencial_indice();
                break;
                case 4:
                    System.out.println("Holis");
                break;
                case 0:
                    System.out.println("\n\nPrograma cerrado");
                break;
                default:
                    System.out.println("\n\n");
                break;
            }
        }while(opcion!=0);
    }
}
