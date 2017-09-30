package natacion;

import java.io.*;
import java.util.*;

public class Vuelo
{   int llave=0;
    long apuntadorF=0;
    String clvOrigen,clvDestino;
    Nodo raiz=null;
    double precio=0.0,distancia=0.0;
    public void escribir_Arch_Maestro()throws IOException
    {   int n;
        StringBuffer buffer=null, buff=null;
        System.out.println("Archivo maestro");
        RandomAccessFile archi=new RandomAccessFile("vuelo","rw");
        RandomAccessFile archiIndice=new RandomAccessFile("indice","rw");
        Scanner entrada=new Scanner(System.in);
        
        apuntadorF=archi.length();
        llave=recorre_archivo_indice(archiIndice);
        
        archiIndice.writeInt(llave);
        archiIndice.writeLong(apuntadorF);
        
        //System.out.println(llave);
        archi.seek(apuntadorF);
        
        archi.writeInt(llave);
        
        System.out.print("Clave origen:");
        clvOrigen=entrada.next();
        buff=new StringBuffer(clvOrigen);
        buff.setLength(3);
        archi.writeChars(buff.toString());

        System.out.print("Clave destino:");
        clvDestino=entrada.next();
        buffer=new StringBuffer(clvDestino);
        buffer.setLength(3);
        archi.writeChars(buffer.toString());
        
        archi.writeDouble(precio);
        archi.writeDouble(distancia);
        archi.close();
        archiIndice.close();
        
    }
    
    /*public int recorre_archivo_maestro(RandomAccessFile leer_archi) throws IOException
    {   long ap_actual, ap_final;
        int cont=0;
        while((ap_actual=leer_archi.getFilePointer())!=(ap_final=leer_archi.length()))
        {   leer_archi.readInt();
            char nombre[]=new char[3];
            for(int c=0; c<nombre.length; c++) //Lectura clvOrigen
                nombre[c]=leer_archi.readChar();
            for(int c=0; c<nombre.length; c++) //Lectura clvDestino
                nombre[c]=leer_archi.readChar();
            leer_archi.readDouble();
            leer_archi.readDouble();
            cont++;
        }
        return cont;
    }
    */
    public int recorre_archivo_indice(RandomAccessFile leer_archi) throws IOException
    {   long ap_actual, ap_final;
        int cont=0;
        while((ap_actual=leer_archi.getFilePointer())!=(ap_final=leer_archi.length()))
        {   leer_archi.readInt();
            leer_archi.readLong();
            cont++;
        }
        return cont;
    }
    
    public void leer_secuencial_maestro()throws IOException
    {   long ap_actual, ap_final;
        RandomAccessFile leer_archi=new RandomAccessFile("vuelo","r");
        while((ap_actual=leer_archi.getFilePointer())!=(ap_final=leer_archi.length()))
        {   llave=leer_archi.readInt();
            System.out.print(llave+" ");
            char nombre[]=new char[3];
            for(int c=0; c<nombre.length; c++)
                nombre[c]=leer_archi.readChar();
            new String(nombre).replace('\0', ' ');
            System.out.print(nombre);
            System.out.print(" ");
            
            for(int c=0; c<nombre.length; c++)
                nombre[c]=leer_archi.readChar();
            new String(nombre).replace('\0', ' ');
            System.out.print(nombre);
            System.out.print(" ");
            
            precio=leer_archi.readDouble();
            distancia=leer_archi.readDouble();
            System.out.println(precio+" "+distancia);
        }
        leer_archi.close();
    }
    
    public void leer_secuencial_indice()throws IOException
    {   long ap_actual, ap_final;
        long fin;
        RandomAccessFile leer_archi=new RandomAccessFile("indice","r");
        while((ap_actual=leer_archi.getFilePointer())!=(ap_final=leer_archi.length()))
        {   llave=leer_archi.readInt();
            System.out.print(llave+" ");
            fin=leer_archi.readLong();
            System.out.println(fin);
        }
        leer_archi.close();
    }
    
    public void leer_apuntador()throws IOException
    {   long ap;
        RandomAccessFile archi=new RandomAccessFile("vuelo", "r");
        Scanner entrada=new Scanner(System.in);
        
        System.out.println("Introduce apuntador");
        ap=entrada.nextInt();
        archi.seek(ap);
        llave=archi.readInt();
        System.out.println(llave);
        char nombre[]=new char[3];
        
        for(int c=0; c<nombre.length; c++)
            nombre[c]=archi.readChar();
        new String(nombre).replace('\0', ' ');
        System.out.print(nombre);
        System.out.print(" ");
        
        for(int c=0; c<nombre.length; c++)
            nombre[c]=archi.readChar();
        new String(nombre).replace('\0', ' ');
        System.out.print(nombre);
        System.out.print(nombre);
        System.out.print(" ");

        precio=archi.readDouble();
        distancia=archi.readDouble();
        System.out.println(precio+" "+distancia);
        archi.close();
                
    }
    public void nodo()
    {   
        
    }
}

class Nodo
{   int llave;
    long direccion;
    Nodo izq, der;
    public Nodo(int key, long dir, Nodo I, Nodo D)
    {   llave=key;
        direccion=dir;
        izq=I;
        der=D;
    }
    public Nodo(int key, long dir)
    {   this(key,dir,null,null);
    }
    
}
/*
Leer origen, buscar arreglo
    si no esta
        lo mete en el arbol
        lo registra en el arreglo
    si esta
        lo busca en el arbol, y se queda el apuntador en él
leer destino, buscar arreglo
    si no esta
        lo mete en el arbol como hijo del origen anterior
        lo registra en el arreglo
*/