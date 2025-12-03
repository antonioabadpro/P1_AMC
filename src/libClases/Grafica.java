/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libClases;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


    
/**
 * @version 02/11/24
 * @author Antonio Abad Hernandez Galvez
 */
public class Grafica extends JFrame
{
    private JFreeChart grafica;
    private XYSeriesCollection datos_grafica; // Datos que queremos representar en la Grafica
    private String titulo; // Titulo de la Grafica
    private String tituloX; // Titulo del Eje 'X'
    private String tituloY; // Titulo del Eje 'Y'
    private int tipo; // Indica el tipo de Grafica que queremos representar -> tipo=1 (Comparacion de Todos los Algoritmos), tipo=2 (Comparacion de 2 Algoritmos)
    
    public Grafica(String tituloGrafica, String ejeX, String ejeY, int tipoGrafica)
    {
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.tipo=tipoGrafica;
        this.titulo=tituloGrafica;
        this.tituloX=ejeX;
        this.tituloY=ejeY;
        
        this.setSize(800, 800);
        //this.setLayout(new BorderLayout()); // Rodeamos la Grafica con bordes
        
        if(tipo==1) // Si la Grafica es para Comparar todos los Algoritmos
        {
            this.setTitle("Comparativa de todos Algoritmos");
        }
        if(tipo==2) // Si la Grafica es para comparar 2 Algoritmos
        {
            this.setTitle("Comparativa de 2 Algoritmos");
        }
    }
    
    /**
     * 
     * @param v_nomAlgoritmo Es un vector que contiene el Nombre de los 2 Algoritmos que vamos a representar en la Grafica
     * @param v_tiempos_A1 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 1
     * @param v_tiempos_A2 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 2
     * @param talla_inicial Es la talla inicial que debemos representar en el Eje 'X'
     * @param incremento Es el valor que se le va sumando al valor de la talla inicial a lo largo del Eje 'X'
     */
    public void agregarGraficaComparativa(String[] v_nomAlgoritmo, double[] v_tiempos_A1, double[] v_tiempos_A2, int talla_inicial, int incremento)
    {
        XYSeries s_A1=new XYSeries(v_nomAlgoritmo[0]);
        XYSeries s_A2=new XYSeries(v_nomAlgoritmo[1]);
        
        int valor_ejeX=talla_inicial;
        
        for(int i=0; i<v_tiempos_A1.length; i++)
        {
            s_A1.add(valor_ejeX, v_tiempos_A1[i]); // Cargamos la Serie de valores que queremos representar en el Algoritmo 1 -> X(valor talla), Y(tiempo)
            s_A2.add(valor_ejeX, v_tiempos_A2[i]); // Cargamos la Serie de valores que queremos representar en el Algoritmo 2 -> X(valor talla), Y(tiempo)

            valor_ejeX=valor_ejeX+incremento;
        }
        
        // Añadimos cada Serie de Datos (s_A1 y s_A2) a los datos de la Grafica (datos_grafica)
        this.datos_grafica=new XYSeriesCollection();
        this.datos_grafica.addSeries(s_A1);
        this.datos_grafica.addSeries(s_A2);
        
        this.grafica=ChartFactory.createXYLineChart(titulo, tituloX, tituloY, this.datos_grafica, PlotOrientation.VERTICAL, true, true, false);
        this.grafica.setBackgroundPaint(Color.WHITE); // Color del texto de los ejes X, Y en blanco
        
        XYPlot plot= this.grafica.getXYPlot();
        plot.setBackgroundPaint(Color.decode("#f8f8f8")); // Color del fondo en el que se representan las lineas de las graficas (Blanco mas oscuro)
        plot.setDomainGridlinePaint(Color.GRAY); // Color de las lineas de la Cuadricula Horizontal (X)
        plot.setRangeGridlinePaint(Color.GRAY); // Color de las lineas de la Cuadricula Vertical (Y)
        
        XYLineAndShapeRenderer render=new XYLineAndShapeRenderer();
        
        // Modificamos el Color de las lineas de cada Algoritmo de la Grafica
        render.setSeriesPaint(0, Color.GREEN);
        render.setSeriesPaint(1, Color.BLUE);
        // Modificamos el Grosor de las lineas de cada Algoritmo de la Grafica
        render.setSeriesStroke(0, new BasicStroke(2.0f));
        render.setSeriesStroke(1, new BasicStroke(2.0f));
        
        plot.setRenderer(render); // Establecemos las caracteristicas del Objeto 'render' en la Grafica 'plot'
        
        ChartPanel cp=new ChartPanel(this.grafica);
        this.add(cp, BorderLayout.CENTER);
    }
    
    /**
     * 
     * @param v_nomAlgoritmo Es un vector que contiene el Nombre de los 2 Algoritmos que vamos a representar en la Grafica
     * @param v_tiempos_A1 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 1
     * @param v_tiempos_A2 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 2
     * @param v_tiempos_A3 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 3
     * @param v_tiempos_A4 Es el Vector que contiene el tiempo Medio en ms del Algoritmo 4
     * @param talla_inicial Es la talla inicial que debemos representar en el Eje 'X'
     * @param incremento Es el valor que se le va sumando al valor de la talla inicial a lo largo del Eje 'X'
     */
    public void agregarGraficaTodos(String[] v_nomAlgoritmo, double[] v_tiempos_A1, double[] v_tiempos_A2, double[] v_tiempos_A3, double[] v_tiempos_A4, int talla_inicial, int incremento)
    {
        XYSeries s_A1=new XYSeries(v_nomAlgoritmo[0]);
        XYSeries s_A2=new XYSeries(v_nomAlgoritmo[1]);
        XYSeries s_A3=new XYSeries(v_nomAlgoritmo[2]);
        XYSeries s_A4=new XYSeries(v_nomAlgoritmo[3]);
        
        int valor_ejeX=talla_inicial;
        
        for(int i=0; i<v_tiempos_A1.length; i++)
        {
            s_A1.add(valor_ejeX, v_tiempos_A1[i]); // Cargamos la Serie de valores que queremos representar en el Algoritmo 1 -> X(valor talla), Y(tiempo)
            s_A2.add(valor_ejeX, v_tiempos_A2[i]); // Cargamos la Serie de valores que queremos representar en el Algoritmo 2 -> X(valor talla), Y(tiempo)
            s_A3.add(valor_ejeX, v_tiempos_A3[i]); // Cargamos la Serie de valores que queremos representar en el Algoritmo 3 -> X(valor talla), Y(tiempo)
            s_A4.add(valor_ejeX, v_tiempos_A4[i]); // Cargamos la Serie de valores que queremos representar en el Algoritmo 4 -> X(valor talla), Y(tiempo)
            valor_ejeX=valor_ejeX+incremento;
        }
        
        // Añadimos cada Serie de Datos (s_A1 y s_A2) a los datos de la Grafica (datos_grafica)
        this.datos_grafica=new XYSeriesCollection();
        this.datos_grafica.addSeries(s_A1);
        this.datos_grafica.addSeries(s_A2);
        this.datos_grafica.addSeries(s_A3);
        this.datos_grafica.addSeries(s_A4);
        
        this.grafica=ChartFactory.createXYLineChart(titulo, tituloX, tituloY, this.datos_grafica, PlotOrientation.VERTICAL, true, true, false);
        this.grafica.setBackgroundPaint(Color.WHITE); // Color del texto de los ejes X, Y en blanco
        
        XYPlot plot= this.grafica.getXYPlot();
        plot.setBackgroundPaint(Color.decode("#f8f8f8")); // Color del fondo en el que se representan las lineas de las graficas (Blanco mas oscuro)
        plot.setDomainGridlinePaint(Color.GRAY); // Color de las lineas de la Cuadricula Horizontal (X)
        plot.setRangeGridlinePaint(Color.GRAY); // Color de las lineas de la Cuadricula Vertical (Y)
        
        XYLineAndShapeRenderer render=new XYLineAndShapeRenderer();
        
        // Modificamos el Color de las lineas de cada Algoritmo de la Grafica
        render.setSeriesPaint(0, Color.GREEN);
        render.setSeriesPaint(1, Color.BLUE);
        render.setSeriesPaint(2, Color.decode("#dda51a")); // Color Naranja
        render.setSeriesPaint(3, Color.MAGENTA);
        // Modificamos el Grosor de las lineas de cada Algoritmo de la Grafica
        render.setSeriesStroke(0, new BasicStroke(2.0f));
        render.setSeriesStroke(1, new BasicStroke(2.0f));
        render.setSeriesStroke(2, new BasicStroke(2.0f));
        render.setSeriesStroke(3, new BasicStroke(2.0f));
        
        plot.setRenderer(render); // Establecemos las caracteristicas del Objeto 'render' en la Grafica 'plot'

        ChartPanel cp=new ChartPanel(this.grafica);
        this.add(cp, BorderLayout.CENTER);
    }
};
