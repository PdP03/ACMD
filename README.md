# ACMD
Text based game for software eng.

# REQUISITI
È neccessario aver installato maven nel PC. 
## Windows
A questo [link](https://maven.apache.org/download.cgi) è possibile scaricare i file binari. Dopo averli scaricati bisogna aggiungere il percorso alla variable dambiente PATH

## Linux
È possibile installare maven usando il package manager ad es per apt:
```
> sudo apt install maven -y
```
Inoltre la funzione di salvataggio della partita è disponibile solo se si possiede una chiave per un bucket di aws.

# COMPILAZIONE(Windows e Linux)
Aprire il terminale ed entrare nella cartella ACMD contentente il pom.xml e lanciare il comando:
```
> mvn compile
```
Per eliminare i file compilati lanciare:
```
> mvn clean
```

# START POINT
Il gioco inizia da [Game.java](https://github.com/PdP03/ACMD/blob/dev/ACMD/src/main/java/com/ACMD/app/Kernel_Layer/Game.java) il quale andra a richiamare tutti i metodi/classi che servono per:
* Avvio del gioco ("start")
* Caricare una partita gia salvata("load")

# ENGINE ([GameEngine.java](https://github.com/PdP03/ACMD/blob/dev/ACMD/src/main/java/com/ACMD/app/Engine_Layer/GameEngine/GameEngine.java))
È la classe che implementa la logia del gioco ovvero:
* Gestione dei combattimenti
* Carica la mappa (quindi anche Stanze, Mostri e Chest)
* Gestisce interazioni Player-Mappa (Player raccoglie oggetti, Player usa Pozioni...)

# LAYER DIAGRAM
![LayerDiagram](https://github.com/PdP03/ACMD/blob/dev/DiagrammiUML/LayerDiagram.svg)

# CLASS DIAGRAM
![Class Diagram](https://github.com/PdP03/ACMD/blob/dev/DiagrammiUML/ClassDiagram(Entita-MenuGraphic-Mappa-Prompt).svg)
![Class Diagram](https://github.com/PdP03/ACMD/blob/dev/DiagrammiUML/ClassDiagram(Storage-Persistance-Adapter).svg)
![Class Diagram](https://github.com/PdP03/ACMD/blob/dev/DiagrammiUML/ClassDiagram(GameEngine-Game).svg)

# SEQUENCE DIAGRAM (Work in progress....)
Il diagramma di avvio del gioco è:
![Alt text](https://github.com/PdP03/ACMD/blob/dev/DiagrammiUML/SequenceDiagram.svg)
Altri sequence diagram utili sono:
* [comandi move per player](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/provvisorio1.svg)
* [load di una partita](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/provvisorio2.svg)
* altri da fare .....


