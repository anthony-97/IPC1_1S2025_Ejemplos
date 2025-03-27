/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Pokemon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author polares
 */
public class GraficadorControlador extends ControladorBase {
    
    public ChartPanel generarGrafica() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Pokemon pokemon: this.modelo.getPokemonesDesordenados()) {
            dataset.addValue(pokemon.getAtaque(), "Ataque", pokemon.getNombre());
        }
        
        //Aca se crea la grafica
        JFreeChart chart = ChartFactory.createBarChart(
                "Pokemones",  //Titulo
                "Pokemon",                 //TituloEjeX
                "Ataque",                 //TituloEjeY
                dataset                      //Datos
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
    
    public ChartPanel generarGraficaInicial() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Pokemon pokemon: this.modelo.getPokemones()) {
            dataset.addValue(pokemon.getAtaque(), "Ataque", pokemon.getNombre());
        }
        
        //Aca se crea la grafica
        JFreeChart chart = ChartFactory.createBarChart(
                "Pokemones",  //Titulo
                "Pokemon",                 //TituloEjeX
                "Ataque",                 //TituloEjeY
                dataset                      //Datos
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
}
