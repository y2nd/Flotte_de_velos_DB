import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.pool.OracleDataSource;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, java.io.IOException {
		// Preparation de la connexion.
		OracleDataSource ods = new OracleDataSource();
		ods.setUser("username");
		ods.setPassword("password");
		// URL de connexion, on remarque que le pilote utilise est "thin".
		ods.setURL("jdbc:oracle:thin:@localhost:1521/oracle");

		Connection conn = null;
		Statement stmt = null;
		try {
		conn = ods.getConnection();
		} catch(Exception e) {
		e.printStackTrace();
		}
		while(true){
			mainMenu(conn);
        }
		
	}

	public static void mainMenu(Connection conn) throws SQLException{
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|-------------- Flotte de vélos ------------|");
		System.out.println("|        Que souhaitez-vous faire ?         |");
		System.out.println("|           1-Consultations                 |");
		System.out.println("|           2-Statisques                    |");
		System.out.println("|           3-Mise à jour                   |");
		System.out.println("|           4-Quitter                       |");
		System.out.println("|-------------------------------------------|");
		System.out.println("");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				consultMenu(conn);
				break;
			case 2:
				statistiquesMenu(conn);
				break;
			case 3:
				updateMenu(conn);
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;
		}
	}

	public static void consultMenu(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|---------------------------------- Flotte de vélos --------------------------------|");
		System.out.println("|   Que souhaitez-vous consulter ?                                                  |");
		System.out.println("|        1-Liste des FOURNISSEURS                                                   |");
		System.out.println("|        2-Liste des COMMUNES                                                       |");
		System.out.println("|        3-Liste des ADHERENTS                                                      |");
		System.out.println("|        4-Liste des STATIONS                                                       |");
		System.out.println("|        5-Liste des VELOS                                                          |");
		System.out.println("|        6-Liste des EMPRUNTS                                                       |");
		System.out.println("|        7-Liste des vélos par station                                              |");
		System.out.println("|        8-liste des stations dans une commune donnée                               |");
		System.out.println("|        9-liste des vélos en cours d'utilisation                                   |");
		System.out.println("|        10-Menu principal                                                          |");
		System.out.println("|        11-Quitter                                                                 |");
		System.out.println("| ----------------------------------------------------------------------------------|");
		System.out.println("");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				Consultation.consultTable(conn, "FOURNISSEURS");
				break;
			case 2:
				Consultation.consultTable(conn, "COMMUNES");
				break;
			case 3:
				Consultation.consultTable(conn, "ADHERENTS");
				break;
			case 4:
				Consultation.consultTable(conn, "STATIONS");
				break;
			case 5:
				Consultation.consultTable(conn, "VELOS");
				break;
			case 6:
				Consultation.consultTable(conn, "EMPRUNTS");
				break;
			case 7: 
				Consultation.consultQuery(conn, "VELO_STATION", "", "");
				break;
			case 8:
				System.out.println("veuillez entrer le nom de la commune : ");
				Scanner userInput2 = new Scanner(System.in);
				String commune = userInput2.nextLine();
				Consultation.consultQuery(conn, "STATION_COMMUNE", commune, "");
				break;
			case 9: 	
				Consultation.consultQuery(conn, "VELOS_UTILISES", "", "");
				break;
			case 10:
				break;
			default:
				System.exit(0);
				break;
		}
	}

	public static void statistiquesMenu(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|----------------------------------- Flotte de vélos -----------------------------------|");
		System.out.println("|   Que souhaitez-vous visualiser ?                                                     |");
		System.out.println("|   1-Moyenne de nombre d'usagers par vélos par jour                                    |");
		System.out.println("|   2-Classement des stations par nombre de places disponibles par commune              |");
		System.out.println("|   3-Classement des vélos les plus chargés par station                                 |");
		System.out.println("|   4-Menu principale                                                                   |");
		System.out.println("|   5-Quitter                                                                           |");
		System.out.println("| --------------------------------------------------------------------------------------|");
		System.out.println("");
		int userChoice = userInput.nextInt();

		switch (userChoice) {
			case 1:
				Consultation.consultstat(conn, "AVG_VELOS_JOUR", "");
				break;
			case 2: 
				Consultation.consultstat(conn, "NB_BORNE_STATION_COMMUNE", "");
				break;
			case 3:
				Consultation.consultstat(conn, "VELOS_CHARGE_STATION", "");
				break;
			case 4:
				break;
			case 5:
				System.exit(0);
				break;
			default:
				break;
		}
	}

	public static void updateMenu(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|------------------- Flotte de vélos -------------------|");
		System.out.println("|   Que souhaitez-vous faire ?                          |");
		System.out.println("|   1-Ajouter un enregistrement                         |");
		System.out.println("|   2-supprimer un enregistrement                       |");
		System.out.println("|   3-modifier un enregistrement                        |");
		System.out.println("|   4-Menu Principale                                   |");
		System.out.println("|   5-Quitter                                           |");
		System.out.println("| ------------------------------------------------------|");
		System.out.println("");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				addmenu(conn);
				break;
			case 2:
				deleteMenu(conn);
			case 3:
				modifyMenu(conn);
			case 4:
				break;
			case 5:
				System.exit(0);
		}
	}
	
	public static void addmenu(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|------------------- Flotte de vélos -------------------|");
		System.out.println("|   Que souhaitez-vous Ajouter ?                        |");
		System.out.println("|   1-Ajouter un Fournisseur                            |");
		System.out.println("|   2-Ajouter une commune                               |");
		System.out.println("|   3-Ajouter un adhérent                               |");
		System.out.println("|   4-Ajouter une station                               |");
		System.out.println("|   5-Ajouter un vélo                                   |");
		System.out.println("|   6-Ajouter un emprunt                                |");
		System.out.println("|   7-Menu précédent                                    |");
		System.out.println("|   8-Menu Principale                                   |");
		System.out.println("|   9-Quitter                                           |");
		System.out.println("|-------------------------------------------------------|");
		System.out.println("");
		int userChoice = userInput.nextInt();

		switch (userChoice) {
			case 1:
				Update.addFournisseur(conn);
				break;
			case 2:
				Update.addCommune(conn);
			case 3:
				Update.addAdherent(conn);
			case 4:
				Update.addStation(conn);
			case 5:
				Update.addVelo(conn);
			case 6:
				Update.addEmprunt(conn);
			case 7:
				Main.updateMenu(conn);
				break;
			case 8:
				break;
			case 9: 
				System.exit(0);
				break;
			default:
				// mainMenu(conn);
				break;
		}
	}

	public static void deleteMenu(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|------------------ Flotte de vélos --------------------|");
		System.out.println("|   De quel table souhaitez-vous supprimer ?            |");
		System.out.println("|   1-Emprunts                                          |");
		System.out.println("|   2-Adhérent                                          |");
		System.out.println("|   3-Vélos                                             |");
		System.out.println("|   4-Fournisseurs                                      |");
		System.out.println("|   5-Menu précédent                                    |");
		System.out.println("|   6-Menu principale                                   |");
		System.out.println("| ------------------------------------------------------|");
		System.out.println("");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				Update.deleteRecord(conn, "EMPRUNTS");
				break;
			case 2:
				Update.deleteRecord(conn, "ADHERENTS");
				break;
			case 3:
				Update.deleteRecord(conn, "VELOS");
				break;
			case 4: 
				Update.deleteRecord(conn, "FOURNISSEURS");
				break;
			case 5: 
				Main.updateMenu(conn);
				break;
			case 6:
				break;
			default:
				break;
		}
	} 

	public static void modifyMenu(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|------------------- Flotte de vélos -------------------|");
		System.out.println("|   Dans quelle table ?                                 |");
		System.out.println("|   1-FOURNISSEURS                                      |");
		System.out.println("|   2-ADHERENTS                                         |");
		System.out.println("|   3-STATIONS                                          |");
		System.out.println("|   4-Menu précédent                                    |");
		System.out.println("|   5-Menu principale                                   |");
		System.out.println("| ------------------------------------------------------|");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				modifyMenuFournisseur(conn);
				break;
			case 2:
				modifyMenuAdherent(conn);
				break;
			case 3:
				modifyMenuStation(conn);
				break;
			case 4:
				Main.updateMenu(conn);
				break;
			case 5:
				break;
			default:
				break;
		}
	} 

	public static void modifyMenuFournisseur(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|------------------- Flotte de vélos -------------------|");
		System.out.println("|   Quel attribut ?                                     |");
		System.out.println("|   1-Nom fournisseur                                   |");
		System.out.println("|   2-Adresse fournisseur                               |");
		System.out.println("|   3-Ville fournisseur                                 |");
		System.out.println("|   4-Menu précédent                                    |");
		System.out.println("|   5-Menu principale                                   |");
		System.out.println("| ------------------------------------------------------|");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				Update.updateFournisseur(conn, "nom");
				break;
			case 2:
				Update.updateFournisseur(conn, "adresse");
				break;
			case 3:
				Update.updateFournisseur(conn, "ville");
				break;
			case 4:
				Main.modifyMenu(conn);
				break;
			case 5:
				break;
			default:
				break;
		}
	}

	public static void modifyMenuAdherent(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|-------------------- Flotte de vélos ------------------|");
		System.out.println("|   Quel attribut ?                                     |");
		System.out.println("|   1-Nom adhérent                                      |");
		System.out.println("|   2-Prénom adhérent                                   |");
		System.out.println("|   3-Solde                                             |");
		System.out.println("|   4-Menu précédent                                    |");
		System.out.println("|   5-Menu principale                                   |");
		System.out.println("|-------------------------------------------------------|");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				Update.updateAdherent(conn, "nom");
				break;
			case 2:
				Update.updateAdherent(conn, "prenom");
				break;
			case 3:
				Update.updateAdherent(conn, "solde");
				break;
			case 4:
				Main.modifyMenu(conn);
				break;
			case 5:
				break;
			default:
				break;
		}
	}

	public static void modifyMenuStation(Connection conn) throws SQLException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("");
		System.out.println("|------------------- Flotte de vélos -------------------|");
		System.out.println("|   Quel attribut ?                                     |");
		System.out.println("|   1-Adresse station                                   |");
		System.out.println("|   2-Nombre de bornes                                  |");
		System.out.println("|   3-Commune                                           |");
		System.out.println("|   4-Menu précédent                                    |");
		System.out.println("|   5-Menu principale                                   |");
		System.out.println("| ------------------------------------------------------|");
		int userChoice = userInput.nextInt();
		switch (userChoice) {
			case 1:
				Update.updateStation(conn, "adresse");
				break;
			case 2:
				Update.updateStation(conn, "borne");
				break;
			case 3:
				Update.updateStation(conn, "commune");
				break;
			case 4:
				Main.modifyMenu(conn);
				break;
			case 5:
				break;
			default:
				break;
		}
	}
}
