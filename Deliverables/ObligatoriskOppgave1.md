#Oblig 1 - Ballmer’s Peak

##Deloppgave 1: Organiser teamet

GRUPPEINFORMASJON
Gruppenavn: BalmersPeak
Roller:
Team Lead/Scrum Master: Magnus
Project Owner/Kundekontakt: Philip
Developing Team : Simen, Vetle, Sverre
Som beskrevet i kompetanse delen har alle på gruppen grei kunnskap om java, og hører derfor godt hjemme i Developing Team. Ved valg av de andre rollene gikk vi etter hvem som var mest entusiastisk til hver av rollene. Dermed ble Magnus Team Leader og Philip ble Project Owner.

Kompetanse:
Alle går informatikk så alle har programmeringserfaring i Java fra tidligere emner. Vetle også har brukt github for fellesprosjekt tidligere.
Vi blir å bruke project board på github siden vi vil holde oss nærmest mulig det. I tillegg blir vi å bruke Tiled for brettet, Google Docs for oversikt og Discord for kommunikasjon.

Se Github for project board.

##Deloppgave 2: Velg og tilpass en prosess for laget

PROSJEKTMETODIKK - SCRUM
Hva er egentlig scrum?
Enkelt rammesett for å optimalisere produktutvikling. Baserer seg på selvstyrte team som jobber med utvikling og leveranser i korte perioder, da 1-4 uker. Man skal komme raskt i gang med å utvikle den viktigste funksjonaliteten. For oss vil dette være kravene for MVP. Sprint-perioden vår vil i utgangspunktet vare frem til neste oblig. Om disse sprintene blir for store vil vi revurdere lengden på sprinten fortløpende.


Hvordan dette egner oss:
Passende med at vi har en plan (backlog) og tidsfrister (sprint) for hva vi vil ha ferdig til de ulike innleveringene.
Større mulighet til å legge inn endringer når det er nødvendig, gir oss bedre fleksibilitet
Får fordelt roller til alle de ulike i teamet slik at alle har noe å jobbe med.

Hvilke tilpasninger vi har gjort:
Som et studentprosjekt har vi alle flere emner og prosjekter vi må jobbe med samtidig. Så derfor har vi funnet tid i timeplanen våre der vi kan jobbe et par timer 2-3 ganger i uka.
Oppfølgingen vil ikke kunne bli gjort hver dag slik som man kanskje ville fått i arbeidslivet. Derfor vil det bli ved hvert møte, selv om Scrum egentlig går ut på å ha daglige møter.

Møter og scrum-aktiviteter

Vi vil ha tre møter i uken hvor de ulike scrum-aktivitetene tar sted. Hver mandag kl.12 vil vi ha et møte hvor vi enten skal gjennom en daily scrum eller sprint planning. Om dette er mandagen etter en innlevering vil vi foreta oss en sprint planning hvor vi fordeler og prioriterer oppgaver fra backlogen. Om vi er i en pågående sprint vil mandagsmøtet være en daily scrum hvor vi vurderer sprinten og åpner for innspill.

Torsdager kl. 10 vil også være en daily scrum om vi er i en pågående sprint. Om dette er torsdagen før innlevering vil vi gjennomføre en sprint review og retrospective. Her vil vi vurdere hva som gikk bra og hva som ikke gikk bra ved forrige sprint.

Hver fredag kl.12 skal vi gå igjennom innlevering og gjøre ferdig eventuelle utestående oppgaver. Normalt vil vi anta at alt skal være ferdig til dette møtet.

Kommunikasjon utenom møtene vil foregå på Discord. Møtene vil også foregå på Discord mtp smittesituasjon i Bergen.

Deling og oppbevaring av dokumenter via Github og Google Docs.

##Deloppgave 3: Få oversikt over forventet produkt

En kort beskrivelse av det overordnede målet for applikasjonen:

Målet for applikasjonen er å kunne spille RoboRally digitalt, da i hvert fall over LAN. Funksjonaliteten vi ønsker i første omgang vil være å få et spill som funker og har grunnreglene i boks. Det vil si at man skal kunne få ut kort, announce power down og spille gjennom en runde av spillet. Dette inkluderer alle de forskjellige fasene som finner sted i løpet av en runde.
I løpet av runde skal hver spiller få utdelt kort alt etter hvor mye skade han har tatt, legge fem av kortene som fungerer som “kommandoer” til hvordan roboten skal bevege seg og til slutt kjøre disse “kommandoene”.
Når det kommer til spillebrettet vil vi i hovedsak starte med å bare designe et fullt funksjonelt spillbrett. Det vil si at vi vil ha med conveyor belts, lasere, reparering, oppgraderinger, skade, at roboter blir ødelagte og at en spiller vinner. Vi tenker at det er viktigere å ha et fullstendig brett, enn mange ufullstendige brett. Dersom alt fungerer som det skal, og tiden strekker til er det mulighet for å implementere flere baner, men det er ikke en prioritet.

En liste over brukerhistorier til systemet basert på MVP-kravene:
Se deloppgave 4 for brukerhistorier.
For hver brukerhistorie, skal dere ha akseptansekriterier og arbeidsoppgaver, samt beskrivelse av hvilke krav brukerhistorien oppfyller (dette lager dere kun for historier dere er ferdige med, holder på med, eller skal til å begynne med)
En prioritert liste over hvilke brukerhistorier dere vil ha med i første iterasjon (altså frem til levering av denne oppgaven, se deloppgave 4 for forslag).
(Frivillig) Krav til MVP er gitt i neste deloppgave. Dersom dere ønsker å utvide den

##Deloppgave 4: Kode

Se Github repository for kode.
Se deloppgave 2 for prosjektmetodikk.

Brukerhistorier:

Som spiller ønsker jeg å kunne se spillbrettet slik at jeg lettere kan få en oversikt over hva som skjer i spillet.
Gitt at jeg er en spiller som skal spille spillet:
Må ha UI så spilleren kan se brettet
Må ha grafikk for de ulike elementene i spillet
Brettet blir ikke borte dersom en hendelse skjer

Som spiller ønsker jeg å kunne se brikkene på spillbrettet slik at jeg kan se hvor brikkene er til enhver tid.
Gitt at jeg er spiller som styrer en robot vil jeg kunne se brikkene på brettet så skal jeg kan spille
Brikkene bugger seg ikke dersom en hendelse skjer (eks. sånn som skjer nå med win og hull at en brikke blir “hengende” igjen)

Som spiller ønsker jeg å kunne flytte roboten slik at jeg kan spille.
Gitt at jeg er spiller vil jeg bruke WASD så jeg kan flytte roboten
Tastene ikke beveger feil vei
Beveger seg med WASD og ikke f.eks. piltaster

Som spiller ønsker jeg å se når brikkene besøker flaggene slik at jeg kan se hvem som går mot seier.
Gitt at jeg er spiller vil jeg få beskjed dersom noen besøker et flagg så jeg kan legge spillet mitt etter det
Printes melding i konsoll dersom noen besøker et flagg eller
Printes melding på et felt i samme grensesnitt som brettet
Får opp grensesnitt dersom noen besøker et flagg (unødvendig hvis ingen vinner)

Som spiller ønsker jeg å få beskjed dersom noen vinner slik at jeg vet at spillet er over.
Gitt jeg er spiller vil jeg vite at spillet er over så jeg kan avslutte aktiviteten
Få opp grensesnitt dersom noen vinner
Printes melding i konsoll dersom noen vinner
Printes melding i et felt i samme grensesnitt som brettet

Møter:
Fredag 05/02
Før møtet:
Ingenting, siden første møtet.
Møtereferat:
Bestemte gruppenavn, roller og fikk satt opp repository på github. Vetle lærte oss hvordan vi skulle bruke ulike branches og pushe til de. Ble enige om å møtes på mandag der vi skulle snakke om punktene vi ble enige om (se mandagsmøtet).

Mandag 08/02
Før møtet:
Skrive spesifikasjon for reglene, bli enige om det vi faktisk skal kode slik at det ikke blir uenigheter/forvirring.
Lage project board (eget board for hver enkelt oblig eller ett for alle?)
Prøve å få krysset av mest mulig av punktene 1-5 av MVP.
Lage klasseoppsett.
Møtereferat:
Møte varte fra 12.15 til 15.10, og alle på gruppen var til stede
Vi startet møte med å gå over det som var gjort over helgen. Her passet vi på at alle hadde den nyeste versjonen av koden. Vi gikk også kort over Git setupet vårt. Det vil si Project Board, hvordan lage nye branches, hvordan vi merger prosjektene og TravisCi
Store deler av møtet gikk til å jobbe gjennom store deler av deloppgave 1-3. Her ble vi stort sett ferdig med deloppgave 1 og 2.
Vi ble enige om å møte igjen på Torsdag kl.10, og potensielt prøve å kjøre møter hver mandag, torsdag og fredag. For øyeblikket er alt digitalt grunnet lockdown.
Til slutt diskuterte vi problemer vi enda ikke hadde løst, lagde en liste over de, og fordelte ut litt arbeidsoppgaver slik at vi vet både hva vi skal gjøre til torsdag og på torsdag. (Dette står nederst i Oblig 1 dokumentet)

Torsdag 11/2
Før møtet:
Sette seg inn i brukerhistorier og akseptansekriterier.
Møtereferat:
Møtet startet 12.10
Vi startet møtet med å få en liten oversikt over endringene som var gjort i koden, og på brukerhistoriene.
Deretter skrev vi oppsummeringen for obligen, siden vi var så godt som ferdig med alle deloppgavene. Her oppsummerte vi det som gikk bra, det som gikk dårlig og diskuterte nye verktøy vi kunne ta i bruk til neste oblig.

Oppsummering:

Hva gikk bra:
Samarbeidet bra, kommunikasjonen god.
Vi var flinke på å organisere, og stille opp på møter. Dette resulterer i at vi er effektive og får ting gjort.
Gruppen satte tidlig opp forskjellige bruker verktøy som Google Docs og Project Boards, som gjorde det enkelt å greit å holde styr på arbeidsprosessen til deg selv og de andre på gruppen.

Hva kunne gått bedre:
Brukerhistorier og akseptansekriterier kan nok vært bedre formulert og spesifisert. Siden dette er første gangen vi har skrevet slike så vil de aldri bli perfekte.
Noe skjev commits-fordeling grunnet foreløpig lite prosjekt med få filer. Dette vil nok løse seg når prosjektet blir større og alle kan jobbe med hver sin oppgave. En annen grunn er at prosjekteringsarbeidet har vært i større fokus enn programmeringsoppgaven.
Møtene kunne sikkert blitt bedre dersom vi hadde hatt mulighet til å møte fysisk.

Nye aktiviteter/verktøy til neste oblig:
Skrive og legge ut punkter for hva vi har tenkt til å gå gjennom på møtene på forhånd (møteplan) slik at alle kan gjøre seg tanker om det.