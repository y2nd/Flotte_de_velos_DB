import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
import java.text.*;




class Update {
    public static String getString(){
        String s;
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            s = bufferRead.readLine();
            return s;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }


    public static int getMax(String tableName, Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        // Execution de la requete.
        int maxIndex = 0;
        try {
            ResultSet rset = stmt.executeQuery("select * from "+ tableName);
            while (rset.next()) {
                if(maxIndex < rset.getInt(1)) {
                    maxIndex = rset.getInt(1);
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return maxIndex;
    }

    
    


    public static void deleteRecords(Connection conn, String numero, String tableName, String attribut) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery("delete from "+ tableName + " where " + attribut + "=" + numero);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void modifyRecord(Connection conn, String numero, String tableName, String attribut, String newValue ,String numberAtt) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        try {
            System.out.println("update "+ tableName + " set " + attribut + "=" + newValue + " where " + numberAtt + "=" + numero);
            ResultSet rset = stmt.executeQuery("update "+ tableName + " set " + attribut + "=" + newValue + " where " + numberAtt + "=" + numero);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public static void addFournisseur(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer le nom du Fournisseur");
        String NOM_FOURNISSEUR = getString();
        System.out.println("Veuillez entrer l'adresse du Fournisseur");
        String ADRESSE_FOURNISSEUR = getString();
        System.out.println("Veuillez entrer la ville du Fournisseur");
        String VILLE_FOURNISSEUR =getString();
        PreparedStatement stmt = null;
        int index = getMax("FOURNISSEURS", conn) + 1;
        try {
            stmt = conn.prepareStatement("insert into FOURNISSEURS values (" + index + ", ? , ? , ?) ");
            stmt.setString(1, NOM_FOURNISSEUR);
            stmt.setString(2, ADRESSE_FOURNISSEUR);
            stmt.setString(3, VILLE_FOURNISSEUR);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    
    public static void addCommune(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer le nom de la commune");
        String NOM_COMMUNE = getString();;
       
        PreparedStatement stmt = null;
        int index = getMax("COMMUNES", conn) + 1;
        try {
            stmt = conn.prepareStatement("insert into COMMUNES values (" + index + ", ?) ");
            stmt.setString(1, NOM_COMMUNE);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    public static void addAdherent(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer le nom de l'adh??rent");
        String NOM_ADHERENT = getString();
        System.out.println("Veuillez entrer le prenom de l'adh??rent");
        String PRENOM_ADHERENT = getString();
        System.out.println("Veuillez entrer la date d'adh??sion sous format YYYY-MM-DD");
        String DATE_ADHESION = getString();
        System.out.println("Veuillez entrer le solde de l'adh??rent");
        int SOLDE = Integer.parseInt(getString());
        PreparedStatement stmt = null;
        int index = getMax("ADHERENTS", conn) + 1;
        try {
            stmt = conn.prepareStatement("insert into ADHERENTS values (" + index + ", ? , ? , ?, ?) ");
            stmt.setString(1, NOM_ADHERENT);
            stmt.setString(2, PRENOM_ADHERENT);
            stmt.setDate(3, Date.valueOf(DATE_ADHESION));
            stmt.setInt(4, SOLDE);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    public static void addStation(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer l'adresse de la station");
        String ADRESSE_STATION = getString();
        System.out.println("Veuillez entrer le nombre de bornes");
        int NB_BORNES = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro de la commune -- Attention il doit etre valide ! --");
        System.out.println("Pour savoir les num??ros des communes disponibles, veuillez relancer le programme et consulter");
        System.out.println("la liste des communes enregistr??es.");
        int NUMERO_COMMUNE = Integer.parseInt(getString());
        PreparedStatement stmt = null;
        int index = getMax("STATIONS", conn) + 1;
        try {
            stmt = conn.prepareStatement("insert into STATIONS values (" + index + ", ? , ? , ?) ");
            stmt.setString(1, ADRESSE_STATION);
            stmt.setInt(2, NB_BORNES);
            stmt.setInt(3,NUMERO_COMMUNE);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    public static void addVelo(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer le mod??le du v??lo");
        String MODEL = getString();
        System.out.println("Veuillez entrer la date de mise en service sous format yyyy-mm-dd ");
        String DATE_MISE_SERVICE = getString();
        System.out.println("Veuillez entrer le kilom??trage (le nombre de kilom??tres que le v??lo a parcouru)");
        int KILOMETRAGE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer l'??tat du v??lo (bon-moyen-mauvais)");
        String ETAT = getString();
        System.out.println("Veuillez entrer le prix horaire (entre 1 et 3)");
        int PRIX_HORAIRE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le niveau de la batterie");
        int NIVEAU_DE_BATTERIE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro du fournisseur -- Attention il doit etre valide ! --");
        System.out.println("Pour savoir les num??ros des fournisseurs disponibles, veuillez relancer le programme et consulter");
        System.out.println("la liste des fournisseurs enregistr??es.");
        int NUMERO_FOURNISSEUR = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro de la station -- Attention il doit etre valide ! --");
        System.out.println("Pour savoir les num??ros des stations disponibles, veuillez relancer le programme et consulter");
        System.out.println("la liste des stations enregistr??es.");
        int NUMERO_STATION = Integer.parseInt(getString());

        PreparedStatement stmt = null;
        int index = getMax("VELOS", conn) + 1;
        try {
            stmt = conn.prepareStatement("insert into VELOS values (" + index + ", ? , ? , ?, ?, ?, ?, ?, ?) ");
            stmt.setString(1, MODEL);
            stmt.setDate(2, Date.valueOf(DATE_MISE_SERVICE));
            stmt.setInt(3,KILOMETRAGE);
            stmt.setString(4, ETAT);
            stmt.setInt(5, PRIX_HORAIRE);
            stmt.setInt(6, NIVEAU_DE_BATTERIE);
            stmt.setInt(7, NUMERO_FOURNISSEUR);
            stmt.setInt(8, NUMERO_STATION);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    public static void addEmprunt(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer date d??but emprunt sous format yyyy:mm:dd");
        String DATE_DE_DEBUT = getString();
        System.out.println("Veuillez entrer date fin emprunt yyyy:mm:dd");
        String DATE_DE_FIN = getString();
        System.out.println("Veuillez entrer la distance parcourue ");
        int DISTANCE_PARCOURUE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro r??ference -- Attention ! il doit etre valide --");
        int NUMERO_REFERENCE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro de la station de d??part -- Attention ! il doit etre valide --");
        int NUMERO_STATION_DEPART = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro de la station d'arriv??e -- Attention ! il doit etre valide --");
        int NUMERO_STATION_ARRIVEE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le d'adh??rent qui a emprunt?? le v??lo -- Attention ! il doit etre valide --");
        int NUMERO_ADHERENT = Integer.parseInt(getString());
    
    PreparedStatement stmt = null;
        int index = getMax("EMPRUNTS", conn) + 1;
        try {
            stmt = conn.prepareStatement("insert into EMPRUNTS values (" + index + ", ? , ? , ?, ?, ?, ?, ?) ");
            stmt.setDate(1, Date.valueOf(DATE_DE_DEBUT));
            stmt.setDate(2, Date.valueOf(DATE_DE_FIN));
            stmt.setInt(3, DISTANCE_PARCOURUE);
            stmt.setInt(4, NUMERO_REFERENCE);
            stmt.setInt(5, NUMERO_STATION_DEPART);
            stmt.setInt(6, NUMERO_STATION_ARRIVEE);
            stmt.setInt(7, NUMERO_ADHERENT);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    public static void addDistance(Connection conn) throws SQLException {
        System.out.println("===== debut ajout =====");
        System.out.println("Veuillez entrer le num??ro station du d??part ");
        int NUMERO_STATION_DEPART = Integer.parseInt(getString());
        System.out.println("Veuillez entrer le num??ro station de l'arriv??e");
        int NUMERO_STATION_ARRIVEE = Integer.parseInt(getString());
        System.out.println("Veuillez entrer la distance");
        int DISTANCE = Integer.parseInt(getString());;
        
    
    PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into DISTANCES values (? , ? , ? ) ");
            
            stmt.setInt(1, NUMERO_STATION_DEPART);
            stmt.setInt(2, NUMERO_STATION_ARRIVEE);
            stmt.setInt(3, DISTANCE);
            stmt.executeUpdate();
            stmt.close();
	        System.out.println("===== fin ajout =====");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        Main.mainMenu(conn);
    }

    public static void deleteRecord(Connection conn, String queryName) throws SQLException {
        System.out.println("===== debut suppression =====");
        System.out.println("Attention, d'autres donn??ees peuvent d??pendre de l'enregistrement que vous voulez supprimer ! elles seront modifi??es");
        Statement st = conn.createStatement();
        if(queryName.equals("VELOS")) {
            String NUMERO = getString();
            System.out.println("Veuillez entrer le num??ro du v??lo ");
            try {
                deleteRecords(conn, NUMERO, "VELOS", "NUMERO_REFERENCE");
                // stmt = conn.prepareStatement("delete from VELOS where NUMERO_REFERENCE =" + NUMERO);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("EMPRUNTS")) {
            System.out.println("Veuillez entrer le num??ro emprunt");
            String NUMERO = getString();
            try {
                deleteRecords(conn, NUMERO, "EMPRUNTS", "NUMERO_EMPRUNT");
                // conn.executeQuery("delete from EMPRUNTS where NUMERO_EMPRUNT =" + NUMERO);
            } finally {
                if (st != null) {
                    st.close();
                }
            }
        } else if(queryName.equals("FOURNISSEURS")) {
            System.out.println("Veuillez entrer le num??ro fournisseur ");
            String NUMERO = getString();
            deleteRecords(conn, NUMERO, "FOURNISSEURS", "NUMERO_FOURNISSEUR");
        } else if(queryName.equals("ADHERENTS")) {
            System.out.println("Veuillez entrer le num??ro adh??rent ");
            String NUMERO = getString();
            deleteRecords(conn, NUMERO, "ADHERENTS", "NUMERO_ADHERENT");
        }
        System.out.println("===== fin suppression =====");
        Main.mainMenu(conn);
    }

    public static void updateFournisseur(Connection conn, String queryName) throws SQLException {
        System.out.println("===== debut modification =====");
        System.out.println("Veuillez entrer le num??ro du fournisseur");
        String NUMERO = getString();
        System.out.println("Veuillez entrer la nouvelle valeur ");
        String newValue = getString();
        PreparedStatement stmt = null;
        if(queryName.equals("nom")) {
            
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update FOURNISSEURS set NOM_FOURNISSEUR=? where NUMERO_FOURNISSEUR=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("adresse")) {
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update FOURNISSEURS set ADRESSE_FOURNISSEUR=? where NUMERO_FOURNISSEUR=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("ville")) {
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update FOURNISSEURS set VILLE_FOURNISSEUR=? where NUMERO_FOURNISSEUR=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("===== fin modification =====");
        Main.mainMenu(conn);
    }

    public static void updateAdherent(Connection conn, String queryName) throws SQLException {
        System.out.println("===== debut modification =====");
        System.out.println("Veuillez entrer le num??ro de l'adh??rent");
        String NUMERO = getString();
        System.out.println("Veuillez entrer la nouvelle valeur ");
        String newValue = getString();
        PreparedStatement stmt = null;
        if(queryName.equals("nom")) {
            
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update ADHERENTS set NOM_ADHERENT=? where NUMERO_ADHERENT=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("prenom")) {
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update ADHERENTS set PRENOM_ADHERENT=? where NUMERO_ADHERENT=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("solde")) {
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update ADHERENTS set SOLDE=? where NUMERO_ADHERENT=?");
                stmt.setInt(1, Integer.parseInt(newValue));
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("===== fin modification =====");
        Main.mainMenu(conn);
    }


    public static void updateStation(Connection conn, String queryName) throws SQLException {
        System.out.println("===== debut modification =====");
        System.out.println("Veuillez entrer le num??ro de station");
        String NUMERO = getString();
        System.out.println("Veuillez entrer la nouvelle valeur ");
        String newValue = getString();
        PreparedStatement stmt = null;
        if(queryName.equals("adresse")) {
            
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update STATIONS set ADRESSE_STATION=? where NUMERO_STATION=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("borne")) {
            try {
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update STATIONS set NB_BORNES=? where NUMERO_STATION=?");
                stmt.setInt(1, Integer.parseInt(newValue));
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(queryName.equals("commune")) {
            try {
                System.out.println("Attention ! il doit etre valide");
                // update FOURNISSEURS set NOM_FOURNISSEUR=mp where NUMERO_FOURNISSEUR=3
                stmt = conn.prepareStatement("update STATIONS set NUMERO_COMMUNE=? where NUMERO_STATION=?");
                stmt.setInt(1, Integer.parseInt(newValue));
                stmt.setInt(2, Integer.parseInt(NUMERO));
                stmt.executeUpdate();
                stmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("===== fin modification =====");
        Main.mainMenu(conn);
    }
}