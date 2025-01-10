import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Entreprise";
        String user = "root";
        String password = "Poukiem34*";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connexion réussie !\n");

            boolean exit = false;
            while (!exit) {
                System.out.println("=== Menu de la TATCorp ===");
                System.out.println("1. Afficher les employés");
                System.out.println("2. Ajouter un employé");
                System.out.println("3. Modifier un employé");
                System.out.println("4. Supprimer un employé");
                System.out.println("5. Afficher les projets");
                System.out.println("6. Ajouter un projet");
                System.out.println("7. Modifier un projet");
                System.out.println("8. Supprimer un projet");
                System.out.println("9. Faire une relations employés - projet");
                System.out.println("10. Enlever une relation employes - projet");
                System.out.println("11. Quitter");
                System.out.print("Choisissez une option : ");

                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1 -> afficherTableEmployes(conn);
                    case 2 -> ajouterEmploye(conn, scanner);
                    case 3 -> modifierEmploye(conn, scanner);
                    case 4 -> supprimerEmploye(conn, scanner);
                    case 5 -> afficherTableProjets(conn);
                    case 6 -> ajouterProjet(conn, scanner);
                    case 7 -> modifierProjet(conn, scanner);
                    case 8 -> supprimerProjet(conn,scanner);
                    case 9 -> ajouterEP(conn,scanner);
                    case 10 ->  supprimerEP(conn, scanner);
                    case 11 -> exit = true;
                    default -> System.out.println("Option invalide, réessayez.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur de connexion ou d'exécution de requête : " + e.getMessage());
        }
    }


    private static void afficherTableEmployes(Connection conn) {
        System.out.println("=== Liste des Employés ===");
        String query = "SELECT * FROM Employes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun employé trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_employe") +
                        ", Nom: " + rs.getString("nom") +
                        ", Prénom: " + rs.getString("prenom") +
                        ", Poste: " + rs.getString("poste") +
                        ", Email: " + rs.getString("email") +
                        ", Téléphone: " + rs.getString("telephone"));
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des employés : " + e.getMessage());
        }
    }

    private static void ajouterEmploye(Connection conn, Scanner scanner) {
        System.out.println("=== Ajouter un Employé ===");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Poste : ");
        String poste = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        String query = "INSERT INTO Employes (nom, prenom, poste, email, telephone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, poste);
            pstmt.setString(4, email);
            pstmt.setString(5, telephone);
            pstmt.executeUpdate();
            System.out.println("Employé ajouté avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'employé : " + e.getMessage());
        }
    }

    private static void modifierEmploye(Connection conn, Scanner scanner) {

        System.out.println("=== Liste des Employés ===");
        String queryAfficher = "SELECT id_employe, nom, prenom FROM Employes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryAfficher)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun employé trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_employe") +
                        ", Nom: " + rs.getString("nom") +
                        ", Prénom: " + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des employés : " + e.getMessage());
            return;
        }


        System.out.println("=== Modifier un Employé ===");
        System.out.print("ID de l'employé à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Demander les nouvelles informations
        System.out.print("Nouveau nom : ");
        String nom = scanner.nextLine();
        System.out.print("Nouveau prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nouveau poste : ");
        String poste = scanner.nextLine();
        System.out.print("Nouvel email : ");
        String email = scanner.nextLine();
        System.out.print("Nouveau téléphone : ");
        String telephone = scanner.nextLine();


        String queryModifier = "UPDATE Employes SET nom = ?, prenom = ?, poste = ?, email = ?, telephone = ? WHERE id_employe = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(queryModifier)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, poste);
            pstmt.setString(4, email);
            pstmt.setString(5, telephone);
            pstmt.setInt(6,id);
            pstmt.executeUpdate();
            System.out.println("Employé modifié avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la modification de l'employé : " + e.getMessage());
        }
    }

    private static void supprimerEmploye(Connection conn, Scanner scanner) {

        System.out.println("=== Liste des Employés ===");
        String queryAfficher = "SELECT id_employe, nom, prenom FROM Employes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryAfficher)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun employé trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_employe") +
                        ", Nom: " + rs.getString("nom") +
                        ", Prénom: " + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des employés : " + e.getMessage());
            return;
        }
        System.out.println("=== Supprimer un Employé ===");
        System.out.print("ID de l'employé à supprimer : ");
        int id = scanner.nextInt();

        String query = "DELETE FROM Employes WHERE id_employe = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Employé supprimé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de l'employé : " + e.getMessage());
        }
    }

    // Affiche projet
    private static void afficherTableProjets(Connection conn) {
        System.out.println("=== Liste des Projets ===");
        String query = "SELECT * FROM Projets";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun projet trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_projet") +
                        ", Titre: " + rs.getString("titre") +
                        ", Description: " + rs.getString("description") +
                        ", Date de début: " + rs.getDate("date_debut") +
                        ", Date de fin: " + rs.getDate("date_fin"));
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des projets : " + e.getMessage());
        }
    }

    private static void ajouterProjet(Connection conn, Scanner scanner) {
        System.out.println("=== Ajouter un Projet ===");
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("Date de début (yyyy-MM-dd) : ");
        String dateDebut = scanner.nextLine();
        System.out.print("Date de fin (yyyy-MM-dd) : ");
        String dateFin = scanner.nextLine();

        String query = "INSERT INTO Projets (titre, description, date_debut, date_fin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, titre);
            pstmt.setString(2, description);
            pstmt.setDate(3, Date.valueOf(dateDebut));
            pstmt.setDate(4, Date.valueOf(dateFin));
            pstmt.executeUpdate();
            System.out.println("Projet ajouté avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout du projet : " + e.getMessage());
        }
    }

    private static void modifierProjet(Connection conn, Scanner scanner) {
        System.out.println("=== Liste des Projets ===");
        String query = "SELECT * FROM Projets";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun projet trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_projet") +
                        ", Titre: " + rs.getString("titre") +
                        ", Description: " + rs.getString("description") +
                        ", Date de début: " + rs.getDate("date_debut") +
                        ", Date de fin: " + rs.getDate("date_fin"));
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des projets : " + e.getMessage());
        }

        //Modifier le projet
        System.out.println("=== Modifier un projet ===");
        System.out.println("Selectioner l'id du projet a modifier :");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nouveau titre : ");
        String titre = scanner.nextLine();
        System.out.print("Nouvelle description : ");
        String description = scanner.nextLine();
        System.out.print("Nouvelle date de debut : ");
        String date_debut = scanner.nextLine();
        System.out.print("Nouvelle date de fin : ");
        String date_fin = scanner.nextLine();

        String queryModifier = "UPDATE Projets SET titre = ?, description = ?,date_debut = ? , date_fin = ? WHERE id_projet = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(queryModifier)) {
            pstmt.setString(1, titre);
            pstmt.setString(2, description);
            pstmt.setString(3, date_debut);
            pstmt.setString(4, date_fin);
            pstmt.setInt(5,id);
            pstmt.executeUpdate();
            System.out.println("Projet modifié avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la modification du projet : " + e.getMessage());
        }

    }

    private static void supprimerProjet(Connection conn, Scanner scanner) {
        System.out.println("=== Liste des Projets ===");
        String selectQuery = "SELECT * FROM Projets";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun projet trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_projet") +
                        ", Titre: " + rs.getString("titre") +
                        ", Description: " + rs.getString("description") +
                        ", Date de début: " + rs.getDate("date_debut") +
                        ", Date de fin: " + rs.getDate("date_fin"));
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des projets : " + e.getMessage());
        }

        System.out.println("=== Supprimer Un projet ===");
        System.out.print("ID du projet à supprimer : ");
        int id = scanner.nextInt();

        String deleteQuery = "DELETE FROM Projets WHERE id_projet = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Projet supprimé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du projet : " + e.getMessage());
        }
    }

    private static void ajouterEP(Connection conn, Scanner scanner) {
        System.out.println("=== Associer un Employé à un Projet ===");

        System.out.println("Liste des employés disponibles :");
        String queryEmployes = "SELECT id_employe, nom, prenom FROM Employes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryEmployes)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun employé trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_employe") +
                        ", Nom: " + rs.getString("nom") +
                        ", Prénom: " + rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des employés : " + e.getMessage());
            return;
        }

        System.out.print("Entrez l'ID de l'employé : ");
        int idEmploye = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Liste des projets disponibles :");
        String queryProjets = "SELECT id_projet, titre FROM Projets";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryProjets)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucun projet trouvé.\n");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_projet") +
                        ", Titre: " + rs.getString("titre"));
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des projets : " + e.getMessage());
            return;
        }

        System.out.print("Entrez l'ID du projet : ");
        int idProjet = scanner.nextInt();
        scanner.nextLine();

        // Ajouter la relation dans la table Employe_projet
        String insertQuery = "INSERT INTO Employe_projet (id_employe, id_projet) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setInt(1, idEmploye);
            pstmt.setInt(2, idProjet);
            pstmt.executeUpdate();
            System.out.println("Relation employé-projet ajoutée avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la relation : " + e.getMessage());
        }
    }

    private static void supprimerEP(Connection conn, Scanner scanner) {
        System.out.println("=== Supprimer une Relation Employé-Projet ===");

        // Afficher toutes les relations existantes
        String queryRelations = """
            SELECT ep.id_employe, e.nom AS employe_nom, e.prenom AS employe_prenom,
                   ep.id_projet, p.titre AS projet_titre
            FROM Employe_projet ep
            JOIN Employes e ON ep.id_employe = e.id_employe
            JOIN Projets p ON ep.id_projet = p.id_projet
            """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryRelations)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Aucune relation employé-projet trouvée.\n");
                return;
            }

            System.out.println("Liste des relations employé-projet :");
            while (rs.next()) {
                System.out.println("Employé : " + rs.getString("employe_nom") + " " + rs.getString("employe_prenom") +
                        " (ID Employé : " + rs.getInt("id_employe") + ")" +
                        " - Projet : " + rs.getString("projet_titre") +
                        " (ID Projet : " + rs.getInt("id_projet") + ")");
            }
            System.out.println();

        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des relations : " + e.getMessage());
            return;
        }

        // Demander les IDs de l'employé et du projet pour supprimer la relation
        System.out.print("Entrez l'ID de l'employé : ");
        int idEmploye = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        System.out.print("Entrez l'ID du projet : ");
        int idProjet = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        // Supprimer la relation dans la table Employe_projet
        String deleteQuery = "DELETE FROM Employe_projet WHERE id_employe = ? AND id_projet = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, idEmploye);
            pstmt.setInt(2, idProjet);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relation employé-projet supprimée avec succès !");
            } else {
                System.out.println("Aucune relation correspondante trouvée. Vérifiez les IDs.");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la relation : " + e.getMessage());
        }
    }

}
