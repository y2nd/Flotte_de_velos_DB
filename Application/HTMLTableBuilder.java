import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class HTMLTableBuilder {
    private String HTML = "<!DOCTYPE html> <html> <head> <style> h1 {text-align: center;} #table { font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; } #table td, #table th { border: 1px solid #ddd; padding: 8px; } #table tr:nth-child(even){background-color: #f2f2f2;} #table tr:hover {background-color: #ddd;} #table th { padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white; } </style> </head> <body>";

    public void setTitle(String title) {
        HTML = HTML + "<h1>" + title + "</h1>";
    }

    public void setTableHeader(LinkedList<String> L) {
        HTML = HTML + "<table id=\"table\"><tr>";
        for (String column : L) {
            HTML = HTML + "<th>" + column + "</th>";
        }
        HTML = HTML + "</tr>";
    }

    public void fillTableRow(LinkedList<String> L) {
        HTML = HTML + "<tr>";
        for (String column : L) {
            HTML = HTML + "<td>" + column + "</td>";
        }
        HTML += "</tr>";
    }

    public void finishHTML() {
        HTML = HTML + "</table></body></html>";
    }

    public void printHTML(String fileName) {
        try {
            File dir = new File("./html");
            dir.mkdir();
            FileWriter file = new FileWriter("./html/" + fileName + ".html");
            PrintWriter p = new PrintWriter(file);
            p.println(HTML);
            file.close();
            System.out.println("      Le fichier " + fileName + ".html a été créé       ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        HTMLTableBuilder hg = new HTMLTableBuilder();

        hg.setTitle("Table des Vélos");
        LinkedList<String> header = new LinkedList<String>();
        header.add("N référence");
        header.add("Modèle");
        header.add("nom");
        hg.setTableHeader(header);
        LinkedList<String> donnees = new LinkedList<String>();
        donnees.add("125");
        donnees.add("XPS");
        donnees.add("VTT");
        hg.fillTableRow(donnees);
        hg.finishHTML();
        hg.printHTML("table");
        
    }


}