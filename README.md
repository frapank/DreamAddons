

# DreamAddons

Plugin realizzato per DreamNetwork, si occupa di aggiungere funzionalità come gli AirDrop e i Frammenti

Versione: 1.8.8 

## Utilizzo

Una volta messo dentro il server, bastera fare i comandi dedicati allo staff, che inizieranno con:
1. /airdrop, 
2. /frammento 
3. /frammenti
4. /itemstats

## Caratteristiche principali

- **Feature 1:** Puoi evocare tu stesso gl'AriDrop.
- **Feature 2:** Con un comando apposto avrai la possibilità di scegliere tu stesso i posti dove ogni minuto gl'AirDrop verranno evocati.

- **Feature 1:** I frammenti sono degl'Item riscattabili tramite comando o tramite gli Airdrop.
- **Feature 2:** Quando ci clicchi sopra ti daranno dei punti che potrai utilizzare in un mercato apposito.

- **Feature 1:** Ogni kill si aggiorneranno gl'itemstats di determinati utensili.


## Comandi (Admin)

- `/airdrop spawn`: Evochi un airdrop alla tua posizione.
- `/airdrop list`: Fai vedere tutti gli spawn.
- `/airdrop create <number>`: Crea uno spawn.
- `/airdrop create <number>`: Crea uno spawn.
- `/frammento`: Ti dara un frammento.
- `/frammenti <player>`: Serve a vedere tutti i frammenti di un player.
- `/frammenti <player> azzera`: Azzera i punti di un player.
- `/frammenti <player> imposta`: Imposta i punti di un player.
- `/itemstats`: Testa la funzione delle statistiche

## Permessi

- `dream.airdrop.admin`: Accesso ai comandi per gli AirDrop
- `dream.fragment.admin` Accesso ai comandi admin dei frammenti
- `dream.fragment.placefragments`: Permesso per piazzare le head
- `dream.boost.criticalboost`: Ti dara questo permesso all'aquisto del booster critical
- `dream.boost.keepinv`: Ti dara questo permesso all'aquisto del booster keep inventory
  
## Config

```yaml
############################
#       AirDrops          #
############################

config-version: 1.0-BETA

# General Settings
name: "&bAirDrops"                              # AirDrop Name tag
block: "DIAMOND_BLOCK"                          # Block used to represent the AirDrop
spawn-count: 3                                  # Set how may spawner will appear in seconds
spawn-time: 1                                   # Count how many seconds apart they are generated

# Sound Effects
fall-sound: "NOTE_PLING"                        # Sound when the AirDrop falls
end-sound: "LEVEL_UP"                           # Sound when the AirDrop lands
claim-sound: "EXPLODE"                          # Sound when the AirDrop is claimed

# Gameplay Settings
command-claim: "give %player% minecraft:apple"  # Command executed when a player claims the AirDrop
world: "world"                                  # World in which AirDrops will be spawned

# Staff Messages
spawn-message: "&eAirDrop spawned"              # Message when an AirDrop is spawned
list-message: "&eList of AirDrops:"             # Header for the list of AirDrops spawner
list-spawn-message: "&f-> %spawn-id%"           # Item in the list of AirDrops spawner
spawn-already-exist: "&cSpawn already exists"   # Message when attempting to create an AirDrop at an existing ID
spawn-added: "&aSpawn added"                    # Message when a new AirDrop location is added
no-perm: "&cInsufficient permission"            # Message when a player does not have the necessary permissions


# Help Message
help-message:
  - "&eHelp message for &aAirDrops plugin&e:"   # Header for the help message
  - "&f/airdrop spawn"                          # Command to spawn an AirDrop
  - "&f/airdrop list"                           # Command to view the list of spawned AirDrops
  - "&f/airdrop create <number>"                # Command to add a new AirDrop location

# Spawn Locations
locations:                                      # AirDrop spawn locations will be automatically added here
  1:
    x: 0
    y: 0
    z: 0


############################
#       FRAGMENTS          #
############################

general:
  prefix: "&f&lDREAM&9&lFRAGMENTS &7» "
  default-message:
    - ""
    - "&7&m     [&f&lDREAM&9&lFRAGMENTS&7&m]     "
    - "&f/frammenti &7• &fVisualizza questo messaggio."
    - "&f/frammenti (player) &7• &fVisualizza i frammenti posseduti da un giocatore."
    - "&7&m                                       "
    - ""
  admin-message:
    - ""
    - "&7&m     [&f&lDREAM&9&lFRAGMENTS&7&m]     "
    - "&f/frammenti &7• &fVisualizza questo messaggio."
    - "&f/frammenti (player) &7• &fVisualizza i frammenti posseduti da un giocatore."
    - "&f/frammento &7• &fRiscatta frammento in un oggetto fisico."
    - "&f/frammenti imposta (player) (valore) &7• &fImposta i frammenti di un giocatore a un determinato valore."
    - "&f/frammenti azzera (player) &7• &fAzzera i frammenti di un giocatore."
    - "&7&m                                       "
    - ""

currency:
  name: "Frammenti"
  #Puoi usare il simbolo che preferisci, se riscontri problemi contattami su discord.
  symbol: "✧"

fragments:
  display-name: "&e•&c•&4• &f&lFrammento &4•&c•&e•"
  lore:
    - ""
    - "&7Frammento proveniente dalla meteora"
    - "&7che si è abbattutta sul KitPvP."
    - ""
    - "&7(Click destro per riscattare)"

messages:
  no-permission: "&7Comando sconosciuto. Se hai bisogno di aiuto contatta uno staffer."
  player-fragments: "&f%player% &7Possiede &f%amount% &7Frammenti."
  player-fragments-self: "&7Possiedi &f%amount% &7Frammenti."
  player-not-found: "&cIl giocatore &f%player% &cnon è stato trovato."
  fragments-resetted: "&7Hai azzerato i frammenti di &f%player%&7."
  fragments-setted: "&7Hai impostato i frammenti di &f%player%&7 a &f%amount%."
  fragment-item-given: "&7Hai ottenuto un frammento fisico."
  invalid-amount: "&cL'importo inserito deve essere un numero intero."
  fragments-redeemed: "&aHai riscattato un frammento."


