import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.LinkedList;


public class Consultation {
    public static void consultTable(Connection conn, String tableName) throws SQLException{
        Statement stmt = null;
        
        try {
            stmt = conn.createStatement();
            // Execution de la requete.
            ResultSet rset = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsetm = rset.getMetaData();
            HTMLTableBuilder hg = new HTMLTableBuilder();
            hg.setTitle("Liste des " + tableName);
            LinkedList<String> header = new LinkedList<String>();
            for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                header.add(rsetm.getColumnLabel(i));
            }
            hg.setTableHeader(header);
            if(tableName.equals("VELOS")) {
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(rset.getString(3));
                    row.add(Integer.toString(rset.getInt(4)));
                    row.add(rset.getString(5));
                    row.add(Integer.toString(rset.getInt(6)));
                    row.add(Integer.toString(rset.getInt(7)));
                    row.add(Integer.toString(rset.getInt(8)));
                    row.add(Integer.toString(rset.getInt(9)));
                    hg.fillTableRow(row);
                }
            } else if(tableName.equals("STATIONS")) {
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(Integer.toString(rset.getInt(3)));
                    row.add(Integer.toString(rset.getInt(4)));
                    hg.fillTableRow(row);
                }
            } else if(tableName.equals("ADHERENTS")) {
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(rset.getString(3));
                    row.add(rset.getString(4));
                    row.add(Integer.toString(rset.getInt(5)));
                    hg.fillTableRow(row);
                }
            } else if(tableName.equals("FOURNISSEURS")){
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(rset.getString(3));
                    row.add(rset.getString(4));
                    hg.fillTableRow(row);
                }
            } else if(tableName.equals("EMPRUNTS")) { // here is an error 
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(rset.getString(3));
                    row.add(Integer.toString(rset.getInt(4)));
                    row.add(Integer.toString(rset.getInt(5)));
                    row.add(Integer.toString(rset.getInt(6)));
                    row.add(Integer.toString(rset.getInt(7)));
                    row.add(Integer.toString(rset.getInt(8)));
                    hg.fillTableRow(row);
                }
            } else if(tableName.equals("COMMUNES")) {
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    hg.fillTableRow(row);
                }
            }
            hg.finishHTML();
            hg.printHTML(tableName);
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }
    public static void consultQuery(Connection conn, String queryName, String commune, String date) throws SQLException {
        Statement stmt = null;
        try {
            HTMLTableBuilder hg = new HTMLTableBuilder();
            if(queryName.equals("VELO_STATION")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("select VELOS.NUMERO_REFERENCE, STATIONS.NUMERO_STATION, VELOS.MODEL from VELOS inner join STATIONS on VELOS.NUMERO_STATION = STATIONS.NUMERO_STATION order by STATIONS.NUMERO_STATION asc");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Liste des vélos par station");
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(Integer.toString(rset.getInt(2)));
                    row.add(rset.getString(3));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("STATION_VELOS");
            } else if(queryName.equals("STATION_COMMUNE")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("select COMMUNES.NUMERO_COMMUNE, COMMUNES.NOM_COMMUNE, STATIONS.NUMERO_STATION from STATIONS inner join COMMUNES on STATIONS.NUMERO_COMMUNE = COMMUNES.NUMERO_COMMUNE where NOM_COMMUNE= '" + commune + "'");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Liste des stations par commune: " + commune);
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(Integer.toString(rset.getInt(3)));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("STATION_" + commune);
            } else if(queryName.equals("VELOS_UTILISES")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("select NUMERO_REFERENCE, MODEL from VELOS where NUMERO_STATION is NULL");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Liste des vélos en cours d'utilisation");
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("VELOS_UTILISES");
            } 
            Main.mainMenu(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void consultstat(Connection conn, String queryName , String week) throws SQLException{
        Statement stmt = null;
        try {
            HTMLTableBuilder hg = new HTMLTableBuilder();
            if(queryName.equals("AVG_VELOS_JOUR")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("SELECT AVG(NB_USAGER) AS MOYENNE FROM (SELECT to_char(DATE_DE_DEBUT, 'DD-MM-YY'), COUNT(distinct NUMERO_ADHERENT) AS NB_USAGER FROM EMPRUNTS GROUP BY to_char(DATE_DE_DEBUT, 'DD-MM-YY'))");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Moyenne des usagers par vélo par jour" );
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Float.toString(rset.getFloat(1)));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("AVG_VELOS_JOUR");
            } else if(queryName.equals("")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("SELECT AVG(NB_USAGER) AS MOYENNE FROM (SELECT to_char(DATE_DE_DEBUT, 'DD-MM-YY'), COUNT(distinct NUMERO_ADHERENT) AS NB_USAGER FROM EMPRUNTS GROUP BY to_char(DATE_DE_DEBUT, 'DD-MM-YY'))");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Moyenne de nombre d'usagers par vélos par jour " );
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Float.toString(rset.getFloat(1)));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("AVG_VELOS_JOUR");
            } else if(queryName.equals("NB_BORNE_STATION_COMMUNE")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("select STATIONS.NUMERO_COMMUNE,NOM_COMMUNE, NUMERO_STATION, NB_BORNES from STATIONS inner join COMMUNES on COMMUNES.NUMERO_COMMUNE = STATIONS.NUMERO_COMMUNE order by STATIONS.NUMERO_COMMUNE asc, NB_BORNES desc");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Classement des stations par nombre de places disponibles par commune" );
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    row.add(Integer.toString(rset.getInt(3)));
                    row.add(Integer.toString(rset.getInt(4)));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("NB_BORNE_STATION_COMMUNE");
            } else if(queryName.equals("VELOS_CHARGE_STATION")) {
                stmt = conn.createStatement();
                // Execution de la requete.
                ResultSet rset = stmt.executeQuery("select NUMERO_STATION, NUMERO_REFERENCE, NIVEAU_DE_BATTERIE from VELOS where NUMERO_STATION is not null order by NUMERO_STATION,NIVEAU_DE_BATTERIE desc");
                ResultSetMetaData rsetm = rset.getMetaData();
                hg.setTitle("Classement des vélos les plus chargés par station");
                LinkedList<String> header = new LinkedList<String>();
                for (int i = 1; i <= rsetm.getColumnCount(); i++) {
                    header.add(rsetm.getColumnLabel(i));
                }
                hg.setTableHeader(header);
                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(Integer.toString(rset.getInt(2)));
                    row.add(Integer.toString(rset.getInt(3)));
                    hg.fillTableRow(row);
                }
                hg.finishHTML();
                hg.printHTML("VELOS_CHARGE_STATION");
            }
        } catch(Exception e) {

        }
        Main.mainMenu(conn);
    }
}
