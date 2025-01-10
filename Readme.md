# 🌟 Projet Java avec MySQL et Java EE 🌟

## 📝 Description

Ce projet consiste à développer une **application de gestion des employés et projets** pour une petite entreprise. Il offre des fonctionnalités **CRUD** (Créer, Lire, Mettre à jour, Supprimer) et permet de **générer des rapports**.  
Deux options sont disponibles :  
- **Java SE** : Application console ou desktop.  
- **Java EE (Bonus)** : Application web avec servlets et JSP.

---

## 🚀 Fonctionnalités principales

### 👨‍💼 Module Employés
- ➕ **Ajouter un employé** : Nom, Prénom, Poste, Email, Téléphone.
- 📜 **Consulter la liste des employés**.
- ✏️ **Modifier les informations d'un employé** existant.
- ❌ **Supprimer un employé**.

### 📁 Module Projets
- ➕ **Ajouter un projet** : Titre, Description, Date de début, Date de fin.
- 🤝 **Assigner des employés** à un projet (relation plusieurs-à-plusieurs).
- 📜 **Consulter la liste des projets** et les employés assignés.
- ✏️ **Modifier ou supprimer un projet**.

### 📊 Rapports
- 📄 **Liste des projets** avec les employés affectés.
- 📄 **Liste des employés** et leurs projets associés.

---

## 🔧 Contraintes techniques

### 🗄️ Base de données
- **Table Employés** : Informations sur les employés.  
- **Table Projets** : Détails des projets.  
- **Table intermédiaire** : Relation plusieurs-à-plusieurs entre employés et projets.

### 💻 Technologies Java SE
- 📡 **JDBC** : Interaction avec MySQL.
- 🖥️ **Interface utilisateur** : Menu interactif en console ou application desktop.

### 🌐 Technologies Java EE (Bonus)
- 🖥️ **Interface web** : Utilisation de Servlets et JSP.
- 🔒 **Sécurité** : Authentification des utilisateurs.
- 🎨 **Interface améliorée** : Utilisation de CSS ou Bootstrap.

---

## 💡 Améliorations possibles
- 🎨 **Design** : Interface web plus moderne avec Bootstrap.
- 🌍 **Déploiement** : Serveur d'applications (ex. Apache Tomcat).
- 📊 **Données interactives** : Affichage sous forme de tableaux dynamiques.
- 📝 **Formulaires HTML** : Simplification de la saisie utilisateur.

---

## 📦 Prérequis
- ☕ **JDK** 8 ou supérieur.  
- 🗄️ **MySQL** (avec un utilisateur ayant les permissions nécessaires).  
- 🌐 **Serveur d'applications** (ex. Apache Tomcat, si Java EE est choisi).

---

## ⚙️ Instructions d'installation

1. 📥 **Cloner le dépôt** :
   ```bash
   git clone <URL_DU_DEPOT>
