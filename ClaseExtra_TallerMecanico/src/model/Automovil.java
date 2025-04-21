/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author polares
 */
public class Automovil {
    private String placa;
    private String marca;
    private String modelo;
    private String foto;
    private String propietario; //Dpi del propietario

    public Automovil(String placa, String marca, String modelo, String foto, String propietario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.foto = foto;
        this.propietario = propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "\n\tAutomovil{" + "placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", foto=" + foto + ", propietario=" + propietario + '}';
    }
    
}
