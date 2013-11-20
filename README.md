Guide d'installation
**********************
1. Installer Eclipse Kepler

2. Installer le framework Play!

3. Installer postgresql avec pgAdminIII

4. Sur pgAdmin, cr�er une base de donn�e nomm�e  "JeuRegime"

5. Modifier le fichier application.conf en fonction des param�tres choisi sur pgAdminIII :
	>ligne 91 db=postgres://postgres:sobitha@localhost/JeuRegime 
		remplacer postgres par le nom d'utilisateur sur pgAdminIII
		remplacer sobitha par votre mot de passe d'acc�s � pgAdminIII
	>ligne 96 et 97 : remplacer comme pr�c�demment

6. Lancer l'invite de commande et aller dans le dossier JeuRegime

7. Taper "play eclipsify" afin de pouvoir ouvrir les fichiers sur eclipse

8. Taper "play run" afin de lancer le jeu

9. Aller sur le navigateur � l'adresse http://localhost:9000/@tests et lancer le Basic Test
	> cette �tape permettre de remplir votre base de donn�e gr�ce � des tests

10. Aller � l'adresse http://localhost:9000 et voici la page d'accueil

11. Pour se connecter, cliquer sur connection et rentrer:
	>Email: job@gmail.com
	>Mot de passe: secret
Cela permet d'avoir acc�s l'interface des inscrits 
(ces donn�es ont �t� enregistr�s dans la base de donn�e lors de la phase de test)