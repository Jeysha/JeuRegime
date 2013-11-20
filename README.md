Guide d'installation
**********************
1. Installer Eclipse Kepler

2. Installer le framework Play!

3. Installer postgresql avec pgAdminIII

4. Sur pgAdmin, créer une base de donnée nommée  "JeuRegime"

5. Modifier le fichier application.conf en fonction des paramètres choisi sur pgAdminIII :
	>ligne 91 db=postgres://postgres:sobitha@localhost/JeuRegime 
		remplacer postgres par le nom d'utilisateur sur pgAdminIII
		remplacer sobitha par votre mot de passe d'accès à pgAdminIII
	>ligne 96 et 97 : remplacer comme précédemment

6. Lancer l'invite de commande et aller dans le dossier JeuRegime

7. Taper "play eclipsify" afin de pouvoir ouvrir les fichiers sur eclipse

8. Taper "play run" afin de lancer le jeu

9. Aller sur le navigateur à l'adresse http://localhost:9000/@tests et lancer le Basic Test
	> cette étape permettre de remplir votre base de donnée grâce à des tests

10. Aller à l'adresse http://localhost:9000 et voici la page d'accueil

11. Pour se connecter, cliquer sur connection et rentrer:
	>Email: job@gmail.com
	>Mot de passe: secret
Cela permet d'avoir accès l'interface des inscrits 
(ces données ont été enregistrés dans la base de donnée lors de la phase de test)