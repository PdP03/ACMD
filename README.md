# ACMD
Text based game for software eng. \
**NB**:È presente anche un class diagramm dell'intero progetto:
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/PdP03/ACMD/dev/DiagrammiUML/ClassDiagram.puml)


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

**NB**:È presente anche un class diagramm dell'intero progetto:
![Alt text](https://github.com/PdP03/ACMD/blob/main/DiagrammiUML/ClassDiagram.svg)

# LAYER DIAGRAM
![Alt text](https://github.com/PdP03/ACMD/blob/main/DiagrammiUML/LayerDiagram.svg)

# SEQUENCE DIAGRAM (Work in progress....)
![Alt text](https://github.com/PdP03/ACMD/blob/main/DiagrammiUML/SequenceDiagram.svg)


