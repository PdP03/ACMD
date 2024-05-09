# ACMD
Text based game for software eng. \
**NB**:È presente anche un class diagramm dell'intero progetto:
![DSS](https://www.plantuml.com/plantuml/png/TLRlRjis4F_EfpXq7ve1BOeC-rHlGOCaQmiqJPQatP-Y2CxInIPAaHf9UNCrlyk-xZdwOhiJQL5sFHkGoDyTxy_lU1HVX8W-TerHBLQVS47mw7MavlWHV7C-_7g0Pu-3imjd2A9kdN4BJK266h9HFtwce38O0iWow1K0t4QlxGAiQsWcSsqZj1ISJ0QrktLRRE6CKPMq5XH_xj3gY4VVYlRAwHgmhWMC8ye1nIv63O669H83g8r8o7RDwA9NrrU_lxvwUt6y7NodJcykJu-7PnhVlRkHUHenjb7MHG933n7SGqhgERnksfGlGcSXQ6739Q7A-NFMpZD__H3VWaBKVeSc1J1tpX1QoKWoAO2dngs8iM5nIxvQVlaN0h4hQBrR8I1Kk4As-4U7fd81mJeB57I813DmRTknqA2jjCI8G5L5dxMpnAuJwny-Wc5rlCWnIGWR0DZDEAV1IPz9MWHpv0U718bp3PMpaQncFJtMtgw47AzTp_7NPA0rk2O_-9wl8mtrlsHLtRZP53kDaHCYJbm8QhkvqTN4tD6eA7n9iQzvB96d3HC8cQiX4qPoD6TB2h7Vg8AS-WPx9GjMQ9odefw5iVF_ht3j1Y9tvNcxtWUAoVr6bR3XwTE_JxPKAd6G42c3olwcKFOmGOOSTiseqg8NGpSyBatAh9-Ut-KIHTrgg3MGQ7tv1sJpuJn4ZrNCAGq6rjAjaw2rNGclOUbyJEoYwIADzQhHMdS_nr0WpBbajFG-DaN9w1KPmtNFWF3s9c52NGR352oMeak3KlDgYNP1xyVZOD9LGjsXpYfuIPVRWUD8S-VNIOmnCiU9dQFqugOSVqnpvSIemQXNABqvnxasGZEpl06LTyom-_jjQcVesJ1veKJo7EV9wM8FKMgpNwzBPqDC5cllskr9mIj1RpEG0DIE-lrFM8caXzqKKcCZUv1yOJYiGaNGE8RoMPLDGEK98nq9yN7TaWIy9xj1MpkXTtJ_aiswNfLmSE7kBVafzWRhtNdNJgSltTneAm3_4vr3DwIQg1p151gSCi08PB4Q7U_XoVeUA17iGnBa7gRso1NXuFkGe0HmI9h2E2YIO0uAKaYJm-2IR7Sy7WIPFW5bqq-_nvLh6hJrR0BL52glswYTdQcyDvCMbpFDpujAsQrJdSK1dOsQn374S8dj3ydfap6uasr8B8GVmTAVy0h3yg2gM1Kxrup7BHgIjr4B0Xcg8jK27EKSFz5wx_CWJtr_haFhh9RkXRw81sFtskhF6Wrz9PXBnooCHfPib4337Xn3K0UiZmfgn_OUNamNmRJCsBPOpllfRvAQSxxM5YE5Ff-MVprPpyhjwbTchCn-AxEm8mjPnYcguKe5vzfJ9GKMks-lRixvzSl5xHs_hjwdz-tLkxjNQYozTJKfqPu41HKFsKannwTmhNskVa6__vYttmWTxIn9XtLT7n1duLHzCR6JqYgKtKQqdx4lfrew1PtOI1_XENQ6PaL1p5qEEc8hQmau30JRY8TIkcllcZQ6qgFQifa7h0Xo9-LwbYEwtBL4p2ERxcvyDe_Dg-lPEE4aqMWCCrNakurQD4LmAihJ8pjhtfyvOqgXfVPVgLoZ3-JxdNXhlT9rkYOQXC09yehy9QcML7swrJOCsE1qxywvQncUmpNvV9VrDDva1nOg30xgWPZ4Z7KCEkz927i6p2hMTDXyoQtSRiLafo6myV6ERntGmuCONA4017lbO4eEsEJFn0baNF5F6bZ03qjTrsG_fekTFAReoNPxgDJXKr6vwAbnT7Zj2x9rrvZ_0000)


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

