# ACMD
Text based game for software eng.

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

# LAYER DIAGRAM
![LayerDiagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/PdP03/ACMD/dev/DiagrammiUML/LayerDiagram.puml)

# CLASS DIAGRAM
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/PdP03/ACMD/dev/DiagrammiUML/ClassDiagram.puml)

# SEQUENCE DIAGRAM (Work in progress....)
Il diagramma di avvio del gioco è:
![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=)
Altri sequence diagram utili sono:
* [comandi move per player](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/provvisorio1.puml)
* [load di una partita](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/provvisorio2.puml)
* altri da fare .....


