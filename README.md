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
![LayerDiagram](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/SequenceDiagram.svg)

# CLASS DIAGRAM
![Class Diagram](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/ClassDiagram(Entita-MenuGraphic-Mappa-GameEngine-Prompt).svg)
![Class Diagram](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/ClassDiagram(Storage-Persistance-Adapter).svg)

# SEQUENCE DIAGRAM (Work in progress....)
Il diagramma di avvio del gioco è:
![Alt text](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/SequenceDiagram.svg)
Altri sequence diagram utili sono:
* [comandi move per player](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/provvisorio1.svg)
* [load di una partita](https://github.com/PdP03/ACMD/tree/dev/DiagrammiUML/provvisorio2.svg)
* altri da fare .....


