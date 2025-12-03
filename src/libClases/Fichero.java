/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libClases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @version 02/11/24
 * @author Antonio Abad Hernandez Galvez
 */
public class Fichero
{
    /**
     * Metodo para leer un fichero .TSP de la ruta introducida por parametro
     * @param ruta Es la ruta donde se encuentra el fichero que queremos leer
     * @return Devuelve el Vector de Puntos que contiene el fichero .TSP
     */
    public static Punto[] leerFichero(File ruta)
    {
        Punto[] v_puntos=null; // Contiene los Puntos que qhemos leido del fichero
        String linea_dimension; // Cadena de Caracteres que contiene la linea del fichero en la que esta la dimension del fichero
        String v_division[]; // Vector que contiene la dimension del fichero en la posicion 1, una vez dividido el vector a partir del caracter ': '
        int tamFichero; // Contiene el tamaño/dimension del fichero, es decir, el nº de Puntos que hay en el fichero
        String linea_fichero; // Cadena de Caracteres que contiene una linea del fichero en la que esta el idPunto, la coordenada X y la coordenada Y
        String v_texto[]; // Vector que contiene la linea del fichero dividida en 3 posiciones -> 0(ID), 1(X), 2(Y)

        try
        {
            FileReader f=new FileReader(ruta); // Objeto para leer el fichero
            BufferedReader b=new BufferedReader(f); // Objeto que lee el Buffer
            
            // Nos saltamos las 3 primeras lineas del fichero .TSP
            for(int i=0; i<3; i++)
            {
                b.readLine();
            }
            
            // Leemos la line que contiene la dimension del fichero para saber cuantos Puntos tenemos que recorrer
            linea_dimension=b.readLine();
            
            // Nos quedamos solo con el valor 'dimension' dividiendo a partir del caracter ': '
            v_division=linea_dimension.split(": ");
            tamFichero=Integer.parseInt(v_division[1]);
            
            // Saltamos las 2 siguientes lineas a la dimension del fichero .TSP
            for(int i=0; i<2; i++)
            {
                b.readLine();
            }
            
            // Creamos un vector cuya dimension debe ser la leida en la variable 'tamFichero'
            v_puntos=new Punto[tamFichero];
            for(int i=0; i<tamFichero; i++)
            {
                linea_fichero=b.readLine();
                v_texto=linea_fichero.split(" ");
                
                // Creamos un nuevo Punto y guardamos los valores leidos del fichero
                v_puntos[i]=new Punto();
                v_puntos[i].setId(Integer.parseInt(v_texto[0]));
                v_puntos[i].setX(Double.parseDouble(v_texto[1]));
                v_puntos[i].setY(Double.parseDouble(v_texto[2]));
            }
        }catch(Exception e)
        {
            System.out.println("Error al leer el fichero de la siguiente ruta: " + ruta);
        }
        
        return v_puntos;
    }
    
    /**
     * Escribe un fichero .TSP con Puntos cuyas coordenadas son generadas aleatoriamente en el rango [0 - 5000]
     * @param tamFichero Es la dimension del nuevo fichero que vamos a crear, es decir, el nº de Puntos aleatorios que queremos que tenga el fichero
     * @return Devuelve el Nombre del nuevo Fichero creado con el formato: 'dataset+dimension+.tsp'
     */
    public static String escribirFichero(int tamFichero)
    {
        String nomFichero=""; // Nombre del nuevo Fichero que vamos a crear con formato: 'dataset+dimension+.tsp'
        Random aleatorio=new Random(System.nanoTime());
        double coord_x;
        double coord_y;
        
        // Escribimos el Nombre del Fichero en la variable
        nomFichero="dataset" + tamFichero + ".tsp";

        try
        {
            // Creamos el Fichero con su nombre
            File fichero=new File(nomFichero);
            
            // Como vamos a escribir en el fichero necesitamoss crear un objeto de la Clase 'FileWriter' para poder escribir en el fichero 'File'
            FileWriter f=new FileWriter(nomFichero);
            
            // Escribimos los datos internos del fichero
            f.write("NAME: " + nomFichero + "\n");
            f.write("TYPE: TSP\n");
            f.write("COMMENT: " + tamFichero + " ciudades creadas por Antonio Abad\n");
            f.write("DIMMENSION: " + tamFichero + "\n");
            f.write("EDGE_WEIGHT_TYPE: EUC_2D\n");
            f.write("NODE_COORD_SECTION\n");
            
            
            for(int i=0; i<tamFichero; i++)
            {
                // Generamos las coordenadas de los Puntos aleatoriamente y los escribimos en el fichero [0 - 5000]
                coord_x=0+aleatorio.nextDouble(5000-0+1);
                coord_y=0+aleatorio.nextDouble(5000-0+1);
                
                // Hacemos que las coordenadas del Punto generadas aleatoriamente tengan 10 decimales
                coord_x=Math.round(coord_x * 1e10) / 1e10;
                coord_y=Math.round(coord_y * 1e10) / 1e10;
                
                f.write(i+1 + " "); // ID del Punto
                f.write(Double.toString(coord_x) + " "); // Coordenada X del Punto generada aleatoriamente
                f.write(Double.toString(coord_y) + "\n"); // Coordenada Y del Punto generada aleatoriamente
            }
            
            // Escribimos el fin el fichero (EOF)
            f.write("EOF");
            f.close(); // Cerramos el fichero
            
        }catch(Exception e)
        {
            System.out.println("Error al crear el Fichero con nombre: " + nomFichero);
        }
        return nomFichero;
    }
    
    /**
     * Escribe un fichero .TSP con el contenido del Vector de Puntos (dataset) resultante tras aplicar el Algoritmo correspondiente en el 'case 3'
     * @param v_puntos Es el Vector de Puntos ordenado tras aplicar un Algoritmo del 'case 3' que contiene los Puntos que debemos escribir en el Fichero
     * @param nomFichero Es el Nombre del Fichero que vamos a escribir y debe tener el siguiente formato: 'nombreAlgoritmo.tsp'
     */
    public static void escribirFicheroArray(Punto[] v_puntos, String nomFichero)
    {
        // Escribimos el nombre del Fichero concatenando en el nombre la extension '.tsp'
        nomFichero=nomFichero + ".tsp";
        
        try
        {
            // Creamos el Fichero con su nombre
            File fichero=new File(nomFichero);
            
            // Como vamos a escribir en el fichero necesitamoss crear un objeto de la Clase 'FileWriter' para poder escribir en el fichero 'File'
            FileWriter f=new FileWriter(nomFichero);
            
            // Escribimos los datos internos del fichero
            f.write("NAME: " + nomFichero + "\n");
            f.write("TYPE: TSP\n");
            f.write("COMMENT: " + v_puntos.length + " ciudades creadas por Antonio Abad en el Vector tras aplicar el Algoritmo\n");
            f.write("DIMMENSION: " + v_puntos.length + "\n");
            f.write("EDGE_WEIGHT_TYPE: EUC_2D\n");
            f.write("NODE_COORD_SECTION\n");
            
            // Escribimos los Puntos del Vector en el nuevo fichero
            for(int i=0; i<v_puntos.length; i++)
            {
                f.write(i+1 + " "); // ID del Punto
                f.write(Double.toString(v_puntos[i].getX()) + " "); // Coordenada X del Punto
                f.write(Double.toString(v_puntos[i].getY()) + "\n"); // Coordenada Y del Punto
            }
            f.write("EOF");
            f.close();
            
        }catch(Exception e)
        {
            System.out.println("Error al crear el Fichero con nombre: " + nomFichero);
        }
    }
    
    /**
     * Muestra los ficheros .TSP que hay en el directorio actual (.)
     * Se utiliza en el 'case 2' para saber cuales son los ficheros que podemos cargar en Memoria
     * @return Devuelve el vector que contiene todos los ficheros del directorio actual
     */
    public static File[] mostrarFicherosTSP()
    {
        int contador=1; // Muestra la posicion del fichero
        File[] v_ficheros=null;
       
        try
        {
            // Obtenemos el directorio actual del proyecto
            String directorio = System.getProperty("user.dir");
            System.out.println("Directorio actual: " + directorio);

            // Creamos un objeto Path con la ruta del directorio
            Path folderPath = Paths.get(directorio);
            if (!Files.exists(folderPath)) // Si el directorio NO existe mostramos un mensaje de error
            {
                System.out.println("El directorio no existe.");
            }
            if (!Files.isReadable(folderPath)) // Si el directorio NO tiene permisos de lectura mostramos un mensaje de error
            {
                System.out.println("No se tienen permisos de lectura en el directorio.");
            }

            // Filtramos la extension del fichero para que solo nos muestren los ficheros .tsp
            FilenameFilter filtro_extension = new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String name)
                {
                    return name.toLowerCase().endsWith(".tsp");
                }
            };

            // Mostramos los ficheros .tsp por pantalla
            File carpeta = folderPath.toFile();
            v_ficheros = carpeta.listFiles(filtro_extension);

            if (v_ficheros != null && v_ficheros.length > 0) // Si hay ficheros y contienen informacion
            {
                System.out.println("Archivos .tsp encontrados en el directorio del proyecto:");
                for (File f : v_ficheros)
                {
                    System.out.println(contador + " " + f.getName());
                    contador++;
                }
            }
            else // Si NO hay ficheros o los ficheros estan vacios, mostramos un mensaje de error
            {
                System.out.println("No se encontraron archivos .tsp en el directorio.");
            }

        } catch (SecurityException e)
        {
            System.out.println("Error de seguridad: No se tienen permisos para acceder al directorio.");
            e.printStackTrace();
        }
        return v_ficheros;
    }
    
    /**
     * Escribe un fichero .DAT con la Tabla Resultante (Talla y Tiempo en ms) tras aplicar la Comparativa de los Algoritmos en el 'case 4'
     * Se utiliza en el 'case 4' para comparar los resultados de cada Algoritmo tras realizar el Caso Medio
     * @param v_nomAlgoritmo Es un vector que contiene el Nombre de los Algoritmos que deben escribirse en el Fichero .DAT
     * @param v_tiempos_A1 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 1
     * @param v_tiempos_A2 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 2
     * @param v_tiempos_A3 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 3
     * @param v_tiempos_A4 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 4
     * @param talla_inicial Es la dimension inicial del primer Vector de Puntos generado aleatoriamente para mostrar los tiempos
     * @param incremento  Es el incremento que sufre la talla/ dimension del Vector de Puntos en la segunda vuelta de bucle
     */
    public static void escribirFicheroDAT(String[] v_nomAlgoritmo, double[] v_tiempos_A1, double[] v_tiempos_A2, double[] v_tiempos_A3, double[] v_tiempos_A4, int talla_inicial, int incremento)
    {
        String nomFichero; // Es el Nombre del Fichero que vamos a escribir y debe tener el siguiente formato: 'nombreAlgoritmo.dat'
        int indice=0; // Indice para recorrer el vector v_tiempos
        
        // Escribimos el nombre del Fichero concatenando en el nombre la extension '.tsp'
        nomFichero=v_nomAlgoritmo[0] + v_nomAlgoritmo[1] + v_nomAlgoritmo[2] + v_nomAlgoritmo[3] + ".dat";
        
        try
        {
            // Creamos el Fichero con su nombre
            File fichero=new File(nomFichero);
            
            // Como vamos a escribir en el fichero necesitamoss crear un objeto de la Clase 'FileWriter' para poder escribir en el fichero 'File'
            FileWriter fich=new FileWriter(nomFichero);
            
            // Como quiero escribir el fichero con el mismo formato con el que lo muestro por pantalla en el 'case 4' necesito un objeto de tipo 'PrintWriter'
            PrintWriter f=new PrintWriter(fich);
            
            // Escribimos la cabecera del fichero
            f.write(" Fichero creado por Antonio Abad Hernandez Galvez\n\n");
            f.printf("%-10s %-16s %-16s %-16s %-16s%n", "" , v_nomAlgoritmo[0], v_nomAlgoritmo[1], v_nomAlgoritmo[2], v_nomAlgoritmo[3]);
            f.printf("%-10s %-16s %-16s %-16s %-16s%n", "Talla", "Tiempo (ms)", "Tiempo (ms)", "Tiempo (ms)", "Tiempo (ms)");
            
            // Escribimos el contenido del fichero (tallas y tiempos)
            for(int i=0; i<v_tiempos_A1.length; i++)
            {
                f.printf("%-10s ", talla_inicial);
                f.printf("%-16.6f ", v_tiempos_A1[i]);
                f.printf("%-16.6f ", v_tiempos_A2[i]);
                f.printf("%-16.6f ", v_tiempos_A3[i]);
                f.printf("%-16.6f ", v_tiempos_A4[i]);
                f.write("\n");
                
                talla_inicial=talla_inicial+incremento;
            }
            
            f.close();
            
        }catch(Exception e)
        {
            System.out.println("Error al crear el Fichero con nombre: " + nomFichero);
        }
    }
    
    /**
     * Escribe un fichero .DAT con la Tabla Resultante (Talla, Tiempo en ms y Calculadas) tras aplicar la Comparativa de los Algoritmos en el 'case 5'
     * Se utiliza en el 'case 5' para comparar los resultados de 2 Algoritmos tras realizar el Caso Medio
     * @param v_nomAlgoritmo Es un vector que contiene el Nombre de los Algoritmos que deben escribirse en el Fichero .DAT
     * @param v_tiempos_A1 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 1
     * @param v_tiempos_A2 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 2
     * @param v_calculadas_A1 Es el Vector que contiene el nº de llamadas al metodo 'calcularDistancia()' del Algoritmo 1
     * @param v_calculadas_A2 Es el Vector que contiene el nº de llamadas al metodo 'calcularDistancia()' del Algoritmo 2
     * @param talla_inicial Es la dimension inicial del primer Vector de Puntos generado aleatoriamente para mostrar los tiempos
     * @param incremento  Es el incremento que sufre la talla/ dimension del Vector de Puntos en la segunda vuelta de bucle
     */
    public static void escribirFicheroDAT_comparativa(String[] v_nomAlgoritmo, double[] v_tiempos_A1, double[] v_tiempos_A2, long[] v_calculadas_A1, long[] v_calculadas_A2, int talla_inicial, int incremento)
    {
        String nomFichero; // Es el Nombre del Fichero que vamos a escribir y debe tener el siguiente formato: 'nombreAlgoritmo.dat'
        int indice=0; // Indice para recorrer el vector v_tiempos
        
        // Escribimos el nombre del Fichero concatenando en el nombre la extension '.tsp'
        nomFichero=v_nomAlgoritmo[0] + v_nomAlgoritmo[1] + ".dat";
        
        try
        {
            // Creamos el Fichero con su nombre
            File fichero=new File(nomFichero);
            
            // Como vamos a escribir en el fichero necesitamoss crear un objeto de la Clase 'FileWriter' para poder escribir en el fichero 'File'
            FileWriter fich=new FileWriter(nomFichero);
            
            // Como quiero escribir el fichero con el mismo formato con el que lo muestro por pantalla en el 'case 4' necesito un objeto de tipo 'PrintWriter'
            PrintWriter f=new PrintWriter(fich);
            
            // Escribimos la cabecera del fichero
            f.write(" Fichero creado por Antonio Abad Hernandez Galvez\n\n");
            f.printf("%-10s %-16s %-16s %-16s %-16s%n", "" , v_nomAlgoritmo[0], v_nomAlgoritmo[1], v_nomAlgoritmo[0], v_nomAlgoritmo[1]);
            f.printf("%-10s %-16s %-16s %-16s %-16s%n", "Talla", "Tiempo (ms)", "Tiempo (ms)", "Calculadas", "Calculadas");
            
            // Escribimos el contenido del fichero (tallas y tiempos)
            for(int i=0; i<v_tiempos_A1.length; i++)
            {
                f.printf("%-10s ", talla_inicial);
                f.printf("%-16.6f ", v_tiempos_A1[i]);
                f.printf("%-16.6f ", v_tiempos_A2[i]);
                f.printf("%-16d ", v_calculadas_A1[i]);
                f.printf("%-16d ", v_calculadas_A2[i]);
                f.write("\n");
                
                talla_inicial=talla_inicial+incremento;
            }
            
            f.close();
            
        }catch(Exception e)
        {
            System.out.println("Error al crear el Fichero con nombre: " + nomFichero);
        }
    }
};
