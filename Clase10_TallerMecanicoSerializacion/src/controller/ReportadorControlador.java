/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Vector;
import model.Cliente;
import model.Repuesto;
import model.Usuario;

/**
 *
 * @author polares
 */
public class ReportadorControlador extends ControladorBase {
    
    public void generarClientes() {
        //Se importa la clase documento de la libreria itext
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("clientes.pdf"));
            documento.open();

            Paragraph titulo = new Paragraph("Reporte de Clientes",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            tabla.addCell("DPI");
            tabla.addCell("Nombre");
            tabla.addCell("Tipo de cliente");

            for (Usuario user: this.modelo.getListaUsuarios()) {
                if(user instanceof Cliente) {
                    Cliente cliente = (Cliente) user;
                    tabla.addCell(cliente.getDpi());
                    tabla.addCell(cliente.getNombre());
                    tabla.addCell(cliente.getTipoCliente());
                }
                
            }

            documento.add(tabla);
            System.out.println("PDF generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }

}
