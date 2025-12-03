/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libClases;

/**
 * @version 31/10/24
 * @author Antonio Abad Hernández Gálvez
 */
public class Punto implements Cloneable
{
/*********************************** ATRIBUTOS PRIVADOS ***********************************/
    private int id;
    private double x;
    private double y;
    
    /*********************************** MÉTODOS PRIVADOS ***********************************/
    
    
    /*********************************** MÉTODOS PÚBLICOS ***********************************/
    
    /**
     * Constructor sin parámetros para inicializar un Punto
     */
    public Punto()
    {
        
    }
    
    /**
     * Constructor con 2 parámetros para inicializar un punto con los valores que entran por parámetro
     * @param idPunto Valor entero unico para identificar al Punto
     * @param x Coordenada X del Punto que queremos crear
     * @param y Coordenada Y del Punto que queremos crear
     */
    public Punto(int idPunto, double x, double y)
    {
        this.id=idPunto;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructor de copia para copiar los valores del Punto que nos entra por parámetro en un nuevo Punto y para simplificar el método 'clone()'
     * @param p Punto del que queremos copiar los valores en nuestro nuevo objeto Punto
     */
    public Punto(Punto p)
    {
        this.id=p.id;
        this.x=p.x;
        this.y=p.y;
    }

    /**
     * @return Devuelve la coordenada X del Punto
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * @return Devuelve la coordenada Y del Punto
     */
    public double getY()
    {
        return this.y;
    }
    
    /**
     * @return Devuelve el ID del Punto
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * @param x Coordenada X del Punto que queremos reemplazar en nuestro objeto Punto
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * @param y Coordenada Y del Punto que queremos reemplazar en nuestro objeto Punto
     */
    public void setY(double y)
    {
        this.y = y;
    }
    
    /**
     * @param idPunto ID del Punto que queremos reemplazar en nuestro objeto Punto
     */
    public void setId(int idPunto)
    {
        this.id=idPunto;
    }

    /**
     * @return Devuelve el 'String' que tiene que mostrar por pantalla el método 'System.out.println()' de la Clase Object
     */
    @Override
    public String toString()
    {
        String s;
        
        s=this.id +"(" + this.x + ", " + this.y + ")";
        return s;
    }
    
    /**
     * Muestra todos los puntos del Vector de Puntos introducido por parametro
     * @param v_puntos Vector de puntos que queremos mostrar sin conocer su longitud
     */
    public void mostrarVectorPuntos(Punto[] v_puntos)
    {
        for(int i=0; i<v_puntos.length; i++)
        {
            System.out.println(v_puntos[i]);
        }
    }
    
    /**
     * @return Devuelve una copia del objeto Punto (mismas coordenadas, pero distinta dirección de memoria)
     */
    @Override
    public Object clone()
    {
        return new Punto(this);
        /*Punto p=new Punto(this);
        
        return p;*/
    }
    
    /**
     * @param obj Objeto de tipo Punto que le pasamos para ver si son iguales
     * @return Devuelve 'true' si ambos Puntos son iguales (misma coordenada X y misma coordenada Y)
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean son_iguales=false;
        
        if(obj.getClass()==this.getClass()) // if(obj instanceof Punto)
        {
            Punto p=(Punto)obj;
            if(this.x==p.x && this.y==p.y) son_iguales=true;
        }
        return son_iguales;
    }
};
