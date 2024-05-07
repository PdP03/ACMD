# ACMD
Text based game for software eng. 
**NB**:è possibile vedere anche il il Class Diagramm
![Alt text](https://github.com/PdP03/ACMD/blob/main/DiagrammiUML/ClassDiagram.svg)
<img src="https://github.com/PdP03/ACMD/blob/main/DiagrammiUML/ClassDiagram.svg">

# START POINT
Il gioco inizia da [Game.java](https://github.com/PdP03/ACMD/blob/main/src/Game.java) il quale andra a richiamare tutti i metodi/classi che servono per:
* Avvio del gioco ("start")
* Caricare una partita gia salvata("load")

# ENGINE ([GameEngine.java](https://github.com/PdP03/ACMD/blob/main/src/GameEngine.java))
È la classe che implementa la logia del gioco ovvero:
* Attacca una Entity
* Carica la mappa e gli oggetti
* Istanzia i Nemici
* Gestisce interazioni Player-Mappa (Player raccoglie oggetti, Player usa Pozioni, Player prende i drop da un Mostro...)

