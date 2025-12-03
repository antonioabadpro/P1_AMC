/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package libClases;

import java.util.ArrayList;

/**
 * @version 02/11/24
 * @author Antonio Abad Hernandez Galvez
 */
public class Algoritmo
{
    /*********************************** ATRIBUTOS PRIVADOS ***********************************/
    private static int contador; // Cuenta el numero de veces que se llama al metodo distancia() en cada Algoritmo
    private static double dis_min; // Contiene la distancia minima entre los 2 Puntos mas cercanos del Vector de Puntos
    
    /*********************************** MÉTODOS AUXILIARES ***********************************/
    
    /**
     * @return Devuelve el Contador del numero de veces que se llama al metodo distancia() en el Algoritmo
     */
    public static int getContador()
    {
        return Algoritmo.contador;
    }
    
    /**
     * Establece el contador al parametro que le indiquemos en su llamada
     * Se utiliza para iniciar el contador desde el main() en los Algoritmos, ya que si lo hacemos desde el Algoritmo no contabilizamos correctamente el nº de veces que se llama al metodo 'distancia()'
     * @param valor Es el valor al que queremos iniciar el contador, normalmente sera un '0'
     */
    public static void setContador(int valor)
    {
        Algoritmo.contador=valor;
    }
    
    /**
     * Calcula la distancia entre 2 puntos mediante la distancia euclidea
     * @param a Punto Inicial para calcular la distancia
     * @param b Punto Final para calcular la distancia
     * @return Devuelve la distancia (double) de los Puntos introducidos por parametro
     */
    public static double calcularDistancia(Punto a, Punto b)
    {
        double d;
        d=Math.sqrt((b.getX()-a.getX())*(b.getX()-a.getX())+(b.getY()-a.getY())*(b.getY()-a.getY()));
        Algoritmo.contador++;
        
        return d;
    }
    
    /**
     * Establece el valor de la Distancia Minima (Algoritmo.dis_min) al maximo valor 'Double' de Java
     * Se utiliza para resetear el valor de 'dis_min' desde el main() en los Algoritmos, ya que si lo hacemos desde el Algoritmo no reseteamos correctamente el valor de 'dis_min'
     */
    public static void resetDistanciaMinima()
    {
        Algoritmo.dis_min=Double.MAX_VALUE;
    }
    
    /**
     * Se utiliza para reducir el numero de veces que se llama al metodo 'distancia()'
     * @return Devuelve la Distancia Minima que hay entre los 2 Puntos mas cercanos del Vector de Puntos
     */
    public static double getDistanciaMinima()
    {
        return Algoritmo.dis_min;
    }
    
    /*********************************** MÉTODOS PÚBLICOS ***********************************/
    
    /**
     * PRIMERA APROXIMACION -> Busqueda Exhaustiva
     * @param v_puntos Es un vector de tipo 'Punto' que contiene todos los Puntos que han sido cargados desde el fichero
     * @param izq Es la posicion inicial (mas a la izquierda) del vector 'v_puntos'
     * @param dch Es la posicion final (mas a la derecha) del vector 'v_puntos'
     * @return Devuelve un vector 'sol' de tipo Punto con los 2 Puntos mas cercanos de todos los que hay cargados en el vector 'v_puntos'
     */
    public static Punto[] busquedaExhaustiva(Punto v_puntos[], int izq, int dch)
    {
        double dis; // Distancia utilizada para comparar la distancia entre 2 Puntos en cada vuelta de bucle
        Punto sol[]=new Punto[2]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos

        // Inicialmente, empezamos a recorrer el Vector 'v_puntos' de izq a dch, y por tanto, los primeros puntos candidatos a ser la solucion son los 2 primeros
        sol[0]=v_puntos[0];
        sol[1]=v_puntos[1];
        
        // Recorremos el vector 'v_puntos' y vamos comparando la distancia de los Puntos
        for(int i=izq; i<=dch; i++)
        {
            /* Los Puntos anteriores a 'i' NO debemos recorrerlos, ya que sino el Algoritmo seria menos eficiente porque estariamos comparando un Punto que ya ha sido comparado
            anteriormente, es decir, si hemos comparado P(2,3) NO debemos comparar P(3,2) porque ya conocemos su distancia */
            for(int j=i+1; j<=dch; j++)
            {
                // Comparamos la distancia de los 2 Puntos adyacentes en el fichero
                dis=Algoritmo.calcularDistancia(v_puntos[i], v_puntos[j]);
                if(dis < Algoritmo.dis_min) // Si la distancia entre esos 2 puntos es menor que la distancia minima, realizamos un swap
                {
                    Algoritmo.dis_min=dis;
                    sol[0]=v_puntos[i];
                    sol[1]=v_puntos[j];
                }
            }
        }
        return sol;
    }
    
    /**
     * SEGUNDA APROXIMACION -> Busqueda Exhaustiva con Poda en el Eje X
     * La Poda consiste en NO seguir buscando mas Puntos cuando la distancia respecto al eje X entre 2 Puntos sea mayor que la distancia mínima que tenemos calculada
     * Para poder utilizar la busqueda Exhaustiva con Poda, previamente en el 'main()' debemos ordenar los Puntos del Vector respecto al Eje X (QuickSort)
     * @param v_puntos Es un vector de tipo 'Punto' que contiene todos los Puntos que han sido cargados desde el fichero
     * @param izq Es la posicion inicial (mas a la izquierda) del vector 'v_puntos'
     * @param dch Es la posicion final (mas a la derecha) del vector 'v_puntos'
     * @return Devuelve un vector 'sol' de tipo Punto con los 2 Puntos mas cercanos de todos los que hay cargados en el vector 'v_puntos'
     */
    public static Punto[] busquedaExhaustivaPoda(Punto v_puntos[], int izq, int dch)
    {
        double dis; // Distancia utilizada para comparar la distancia entre 2 Puntos en cada vuelta de bucle
        Punto sol[]=new Punto[2]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos

        // Inicialmente, empezamos a recorrer el Vector 'v_puntos' de izq a dch, y por tanto, los primeros puntos candidatos a ser la solucion son los 2 primeros
        sol[0]=v_puntos[0];
        sol[1]=v_puntos[1];
        
        // Recorremos el vector 'v_puntos' y vamos comparando la distancia de los Puntos
        for(int i=izq; i<=dch; i++)
        {
            // Mientras NO lleguemos al final del Vector y nos interese seguir comparando, seguimos comparando las distancias entre los 2 puntos del eje X
            for(int j=i+1; j<=dch; j++) 
            {
                // Si la distancia que hay entre los 2 Puntos del Eje X es mayor que la distancia minima, NO nos interesa seguir comparando (break), debemos Podar
                dis=Math.abs(v_puntos[j].getX() - v_puntos[i].getX());
                if(dis >= Algoritmo.dis_min) break;
                else
                {
                    // Comparamos la distancia de los 2 Puntos adyacentes en el fichero
                    dis=Algoritmo.calcularDistancia(v_puntos[i], v_puntos[j]);
                    if(dis<Algoritmo.dis_min) // Si la distancia entre esos 2 puntos es menor que la distancia minima, realizamos un swap
                    {
                        Algoritmo.dis_min=dis;
                        sol[0]=v_puntos[i];
                        sol[1]=v_puntos[j];
                    }
                }
            }
        }
        return sol;
    }
    
    /**
     * TERCERA APROXIMACION -> Busqueda basada en la tecnica Divide y Venceras
     * Para poder utilizar la busqueda mediante un Algoritmo basado en la tecnica Divide y Venceras, debemos ordenar los Puntos del Vector respecto al Eje X (QuickSort)
     * @param v_puntos Es un vector de tipo 'Punto' que contiene todos los Puntos que han sido cargados desde el fichero
     * @param izq Es la posicion inicial (mas a la izquierda) del vector 'v_puntos'
     * @param dch Es la posicion final (mas a la derecha) del vector 'v_puntos'
     * @return Devuelve un vector 'sol' de tipo Punto con los 2 Puntos mas cercanos de todos los que hay cargados en el vector 'v_puntos'
     */
    public static Punto[] busquedaDyV(Punto v_puntos[], int izq, int dch)
    {
        int nPuntos=dch-izq+1; // Nº de Puntos que hay en el Vector 'v_puntos'
        int mitad=izq+(dch-izq)/2; // Contiene la ultima posicion del Subvector de la Izquierda -> 0+(0+9)/2 = 4'5 = 4
        Punto sol_izq[]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos del Subvector de la izquierda
        Punto sol_dch[]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos del Subvector de la derecha
        Punto sol[]=new Punto[2]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos
        
        double dis_izq; // Distancia utilizada para comparar la distancia entre 2 Puntos del Subvector de la Izquierda en cada vuelta de bucle
        double dis_dch; // Distancia utilizada para comparar la distancia entre 2 Puntos del Subvector de la Derecha en cada vuelta de bucle
        double dis; // Distancia Actual calculada
        int i_izq=0; // Indice para recorrer el Subvector de la izquierda
        int i_dch=0; // Indice para recorrer el Subvector de la derecha
        
        if(nPuntos<4) // Si el vector 'v_puntos' tiene tantos Puntos como el Caso Base es mas eficiente utilizar el Algoritmo Exhaustivo con Poda
        {
            sol=Algoritmo.busquedaExhaustivaPoda(v_puntos, izq, dch);
        }
        else // Si el vector 'v_puntos' tiene mas Puntos que el Caso Base es mas eficiente utilizar la estrategia de Divide y Venceras
        {
            // Dividimos el Vector 'v_puntos' por la mitad en 2 Subvectores
            sol_izq=Algoritmo.busquedaDyV(v_puntos, izq, mitad);
            dis_izq=Algoritmo.dis_min;
            
            sol_dch=Algoritmo.busquedaDyV(v_puntos, mitad+1, dch);
            dis_dch=Algoritmo.dis_min;
            
            if(dis_izq<=dis_dch) // Si la distancia que hay entre los 2 Puntos del Subvector Izquierdo es menor que la distancia que hay entre los 2 Puntos del Subvector Derecho
            {
                Algoritmo.dis_min=dis_izq;
                sol=sol_izq;
            }
            else // Si la distancia que hay entre los 2 Puntos del Subvector Derecho es menor que la distancia que hay entre los 2 Puntos del Subvector Izquierdo
            {
                Algoritmo.dis_min=dis_dch;
                sol=sol_dch;
            }
            
            // Calculamos la distancia minima entre los Puntos que quedan en el Subvector Izquierdo
            for(i_izq=mitad; i_izq>=izq; i_izq--)
            {
                // Como el Vector inicial esta ordenado, si llegamos a un valor de 'i' en el que la distancia es mayor que la minima, los siguientes valores del Subvector tambien tendran una distancia mayor que la minima
                dis=Math.abs(v_puntos[mitad].getX()-v_puntos[i_izq].getX());
                if(dis > Algoritmo.dis_min) break;
            }
            
            // Calculamos la distancia minima entre los Puntos que quedan en el Subvector Derecho            
            for(i_dch=mitad+1; i_dch<=dch; i_dch++)
            {
                // Como el Vector inicial esta ordenado, si llegamos a un valor de 'i' en el que la distancia es mayor que la minima, los siguientes valores del Subvector tambien tendran una distancia mayor que la minima
                dis=Math.abs(v_puntos[i_dch].getX()-v_puntos[mitad].getX());
                if(dis > Algoritmo.dis_min) break;
            }
            
            // Como la distancia minima entre 2 puntos puede estar entre un Punto de la Franja Izquierda y un Punto de la Franja Derecha
            // Recorremos el vector 'v_puntos' hasta las posiciones podadas y comparamos la distancia de los Puntos del Subvector de la Izquierda (i) y del Subvector de la Derecha (j)
            // En el caso de que la distancia minima se repita, la distancia minima sera la del primer Punto del Vector, si NO queremos que sea asi -> if(dis >= dis_min)
            for(int i=mitad; i>i_izq; i--) // Recorremos el Subvector de la Izquierda
            {
                for(int j=mitad+1; j<i_dch; j++) // Recorremos el Subvector de la Derecha
                {
                    dis=(Math.abs(v_puntos[j].getX() - v_puntos[i].getX()));
                    // Si la distancia que hay entre un Punto del subvector de la izquierda y un punto del subvector de la derecha es mayor que la distancia minima, NO debemos seguir comparando mas Puntos del Subvecotr de la Derecha y pasamos al siguiente Punto del Subvector de la izquierda
                    if(dis > Algoritmo.dis_min) break;
                    else
                    {
                        // Comparamos la distancia de los 2 Puntos adyacentes en el vector
                        dis=Algoritmo.calcularDistancia(v_puntos[i], v_puntos[j]);
                        if(dis < Algoritmo.dis_min) // Si la distancia entre esos 2 puntos es menor que la distancia minima
                        {
                            Algoritmo.dis_min=dis;
                            sol[0]=v_puntos[i];
                            sol[1]=v_puntos[j];
                        }
                    }
                }
            }
        }
        return sol;
    }
    
    /**
     * CUARTA APROXIMACION -> Busqueda basada en la tecnica Divide y Venceras Mejorada
     * @param v_puntos Es un vector de tipo 'Punto' que contiene todos los Puntos que han sido cargados desde el fichero
     * @param izq Es la posicion inicial (mas a la izquierda) del vector 'v_puntos'
     * @param dch Es la posicion final (mas a la derecha) del vector 'v_puntos'
     * @return Devuelve un vector 'sol' de tipo Punto con los 2 Puntos mas cercanos de todos los que hay cargados en el vector 'v_puntos'
     */
    public static Punto[] busquedaDyV_mejorada(Punto v_puntos[], int izq, int dch)
    {
        int nPuntos=dch-izq+1; // Nº de Puntos que hay en el Vector 'v_puntos'
        int mitad=izq+(dch-izq)/2; // Contiene la ultima posicion del Subvector de la Izquierda -> 0+(0+9)/2 = 4'5 = 4
        Punto sol_izq[]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos del Subvector de la izquierda
        Punto sol_dch[]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos del Subvector de la derecha
        Punto sol[]=new Punto[2]; // Vector solucion en el que cargaremos los 2 Puntos mas cercanos
        
        double dis_izq; // Distancia utilizada para comparar la distancia entre 2 Puntos del Subvector de la Izquierda en cada vuelta de bucle
        double dis_dch; // Distancia utilizada para comparar la distancia entre 2 Puntos del Subvector de la Derecha en cada vuelta de bucle
        double dis; // Distancia Actual calculada
        int i_izq=0; // Indice para recorrer el Subvector de la izquierda
        int i_dch=0; // Indice para recorrer el Subvector de la derecha
        
        if(nPuntos<4) // Si el vector 'v_puntos' tiene tantos Puntos como el Caso Base es mas eficiente utilizar el Algoritmo Exhaustivo con Poda
        {
            sol=Algoritmo.busquedaExhaustivaPoda(v_puntos, izq, dch);
        }
        else // Si el vector 'v_puntos' tiene mas Puntos que el Caso Base es mas eficiente utilizar la estrategia de Divide y Venceras
        {
            // Dividimos el Vector 'v_puntos' por la mitad en 2 Subvectores
            sol_izq=Algoritmo.busquedaDyV_mejorada(v_puntos, izq, mitad);
            dis_izq=Algoritmo.dis_min;
            
            sol_dch=Algoritmo.busquedaDyV_mejorada(v_puntos, mitad+1, dch);
            dis_dch=Algoritmo.dis_min;
            
            if(dis_izq<=dis_dch) // Si la distancia que hay entre los 2 Puntos del Subvector Izquierdo es menor que la distancia que hay entre los 2 Puntos del Subvector Derecho
            {
                Algoritmo.dis_min=dis_izq;
                sol=sol_izq;
            }
            else // Si la distancia que hay entre los 2 Puntos del Subvector Derecho es menor que la distancia que hay entre los 2 Puntos del Subvector Izquierdo
            {
                Algoritmo.dis_min=dis_dch;
                sol=sol_dch;
            }
            
            // Calculamos los Puntos mas cercanos a la frontera, es decir, a la mitad del Vector Original
            // Calculamos la distancia de los Puntos que quedan en el Subvector Izquierdo
            for(i_izq=mitad; i_izq>=izq; i_izq--)
            {
                // Como el Vector inicial esta ordenado, si llegamos a un valor de 'i' en el que la distancia es mayor que la minima, los siguientes valores del Subvector tambien tendran una distancia mayor que la minima
                dis=Math.abs(v_puntos[mitad].getX()-v_puntos[i_izq].getX());
                if(dis > Algoritmo.dis_min) break;
            }
            
            // Calculamos la distancia de los Puntos que quedan en el Subvector Derecho
            for(i_dch=mitad+1; i_dch<=dch; i_dch++)
            {
                // Como el Vector inicial esta ordenado, si llegamos a un valor de 'i' en el que la distancia es mayor que la minima, los siguientes valores del Subvector tambien tendran una distancia mayor que la minima
                dis=Math.abs(v_puntos[i_dch].getX()-v_puntos[mitad].getX());
                if(dis > Algoritmo.dis_min) break;
            }
            
            // Tratamos la franja Central, es decir, vamos recorriendo el vector de puntos desde la posicion podada 'i_izq' hasta 'i_dch' y cargamos los Puntos en un Vector 'v_franja'
            ArrayList<Punto> v_franja=new ArrayList<>();
            for(int i=i_izq+1; i<i_dch; i++)
            {
                v_franja.add(v_puntos[i]);
            }
            
            // Convertimos el ArrayList<Punto> en Punto[] 'v_central' para poder pasarselo como parametro al QuickSort()
            Punto[] v_central=v_franja.toArray(new Punto[v_franja.size()]);
            
            // Ordenamos el Vector 'v_centtral' por el eje 'Y'
            Algoritmo.QuickSort(v_central, 0, v_central.length-1, 'y');
            
            // Como la distancia minima entre 2 puntos puede estar entre un Punto de la Franja Izquierda y un Punto de la Franja Derecha
            // Recorremos el vector 'v_puntos' hasta las posiciones podadas y comparamos la distancia de los Puntos del Subvector de la Izquierda (i) y del Subvector de la Derecha (j)
            // En el caso de que la distancia minima se repita, la distancia minima sera la del primer Punto del Vector, si NO queremos que sea asi -> if(dis >= dis_min)
            for(int i=0; i<v_central.length; i++) // Recorremos el Subvector de la Izquierda
            {
                for(int j=i+1; j<v_central.length && (Math.abs(v_central[j].getY()-v_central[i].getY()) < Algoritmo.dis_min); j++) // Recorremos el Subvector de la Derecha
                {
                    // Comparamos la distancia de los 2 Puntos adyacentes en el vector
                    dis = Algoritmo.calcularDistancia(v_central[i], v_central[j]);
                    if (dis < Algoritmo.dis_min) // Si la distancia entre esos 2 puntos es menor que la distancia minima
                    {
                        Algoritmo.dis_min = dis;
                        sol[0] = v_central[i];
                        sol[1] = v_central[j];
                    }
                }
            }
        }
        return sol;
    }
    
    // Algoritmos necesarios para ordenar el Vector en la Busqueda Divide y Venceras
    
    /**
     * Divide o Particiona el Vector 'v_puntos' segun la coordenada X de los Puntos en 2 subvectores segun el pivote
     * La coordenada X de los Puntos del 1er Subvector debe ser menor o igual que la Coordenada X de los Puntos del 2º Subvector
     * @param v_puntos Es un vector de tipo 'Punto' que contiene todos los Puntos que han sido cargados desde el fichero
     * @param izq Indice del primer elemento del Vector de Puntos (siempre debe ser un 1, ya que si le metemos un 0 NO funciona)
     * @param dch Indice del ultimo elemento del Vector de Puntos
     * @param eje Es el eje por el que queremos ordenar el Vector de Puntos (x, y)
     * @return Devuelve el elemento que esta en la mitad del Vector, es decir, el ultimo elemento del Subvector de la izquierda
     */
    public static int Partition(Punto v_puntos[], int izq, int dch, char eje)
    {
        Punto pivote = v_puntos[izq]; // Contiene el 2º elemento del Vector de Puntos inicial
        int i = izq - 1;
        int j = dch + 1;
        Punto aux;
        
        switch(eje)
        {
            case 'x':
            {
                do
                {
                    do
                    {
                        j--;
                    } while (v_puntos[j].getX() > pivote.getX());

                    do
                    {
                        i++;
                    } while (v_puntos[i].getX() < pivote.getX());

                    if (i < j)
                    {
                        aux = v_puntos[i];
                        v_puntos[i] = v_puntos[j];
                        v_puntos[j] = aux;
                    }
                } while (i < j);
            }; break;
            
            case 'y':
            {
                do
                {
                    do
                    {
                        j--;
                    } while (v_puntos[j].getY() > pivote.getY());

                    do
                    {
                        i++;
                    } while (v_puntos[i].getY() < pivote.getY());

                    if (i < j)
                    {
                        aux = v_puntos[i];
                        v_puntos[i] = v_puntos[j];
                        v_puntos[j] = aux;
                    }
                } while (i < j);
            }; break;
        }
        return j;
    }
    
    /**
     * Ordena de menor a mayor el vector de Puntos 'v_puntos' que le entra por parametro por la coordenada X del Punto
     * @param v_puntos Es un vector de tipo 'Punto' que contiene todos los Puntos que han sido cargados desde el fichero
     * @param izq Indice del primer elemento del Vector de Puntos (siempre debe ser un 1, ya que si le metemos un 0 NO funciona)
     * @param dch Indice del ultimo elemento del Vector de Puntos
     * @param eje Es el eje por el que queremos ordenar el Vector de Puntos (x, y)
     */
    public static void QuickSort(Punto v_puntos[], int izq, int dch, char eje)
    {
        int m=0; // Contiene la posicion mitad de la subdivision del Vector, es decir, la posicion final del Subvector izquierdo tras llamar al metodo Partition()
        
        switch(eje)
        {
            case 'x':
            {
                if(izq < dch)
                {
                    m=Partition(v_puntos, izq, dch, 'x');
                    QuickSort(v_puntos, izq, m, 'x');
                    QuickSort(v_puntos, m+1, dch,'x');
                }
            }; break;
            
            case 'y':
            {
                if(izq < dch)
                {
                    m=Partition(v_puntos, izq, dch, 'y');
                    QuickSort(v_puntos, izq, m, 'y');
                    QuickSort(v_puntos, m+1, dch,'y');
                }
            }; break;
        }
    }
};
