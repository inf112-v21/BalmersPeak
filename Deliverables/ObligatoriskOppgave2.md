# Deloppgave 1 - Prosjekt og prosjektstruktur

#### Hvordan fungerer rollene i teamet?
Vi synes rollene fungerer fint. Vi er fornøyde med både teamleder og kundekontakt sin innsats. Ingen oppdateringer av roller er nødvendig.

#### Trenger dere andre roller?
Nei, rollene fungerer nokså greit for øyeblikket
Teamleder: Innebærer å organisere teamet, ordne møter.
Kundekontakt: innebærer å ta kontakt med gruppeleder for oppklaringer om oppgaver og forventninger til produkt. 
Utvikler: Innebærer ingen store organisatoriske oppgaver, kun utvikling av prosjekt og kommunisering med andre utviklere, teamleder og kundekontakt.
 
#### Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
Vi synes scrum fungerer fint for oss ettersom det gjør at vi kan fordele og prioritere oppgaver på en fleksibel måte. Det vi ønsker å gjøre annerledes i neste sprint er å dele oppgavene i mindre deler.

#### Hvordan er gruppedynamikken og hvordan fungerer kommunikasjon?
Kommunikasjon foregår i hovedsak på Discord og nå på fysiske møter, noe som gjør det lettere å vise hva alle jobber med og koordinere hva som burde implementeres.
 
#### Kort retrospektiv
Kommunikasjon er fortsatt bra, men begynner nå med fysiske møter. Hadde litt for store oppgaver til å begynne med, så nå skal arbeidsoppgavene fordeles bedre. Skal også sørge for at alle pusher kode og ha jevnere fordeling av commits, noe vi tror kan forbedres med mindre oppgaver.
 
#### Bidrag til kodebasen
Vi har et relativt enkelt system på git, der alle har hver sin branch de jobber på. Denne blir så merget med development under sprinten og til master ved innlevering (release). Hovedsakelig er det den som har jobbet med GUI som har flest commits ettersom det er mest å sette seg inn i. Logikken har hittil ikke krevd altfor mye, og derav færre commits fra de som jobber med dette.
 
 
#### Bugs
Hadde et noen få bugs ved første innlevering. Hadde problemer med at spilleren sin texture når den flyttet seg utenfor brettet. Ble fikset etterpå. 
 
 
###Møter:
Mandag 15/2
Før møtet:
Legge planer for neste oblig
Se litt på de neste punktene på MVP listen
 
Møtereferat:
Møtet varte fra 12.00 til 14.00
Lagte brukerhistorier, og akseptansekrav for de neste punktene på MVP-en.
Deretter fordelte vi ut arbeidsoppgaver, og startet så smått på de.
Det blir lagt mest vekt på å få til multiplayer og å få kortene til å fungere.
Fullt oppmøte
Torsdag 17/2
Før møtet:
Planlegge litt mer på strukturen på selve prosjektet (abstrahering osv)
Dele med resten av gruppen det vi har gjort siden sist møte
Planlegge/dele hvordan vi skal løse arbeidsoppgavene
Møtereferat:
Planla litt hvordan strukturen skulle bli.
Sjekket ut Start menyen, og passet på at den ikke hadde noen bugs.
Diskuterte potensielle løsninger på multiplayer problemet.
Plana hvordan vi skulle sette opp GUI for kortene
Fullt oppmøte

Fredag 18/2
Før møtet:
Fortsette å jobbe med tildelte arbeidsoppgaver.
Møtereferat:
Vi ble enige om å droppe dette møtet, ettersom at alle var klar over hva de måtte jobbe med.

Mandag 22/2
Før møtet:
Sette opp skjellet for prosjektet, klasser, packages osv
Se på kort-GUI
Møtereferat:
Lagte planer for kort-GUI
Jobbet med prosjektet
Fullt oppmøte
Torsdag 25/2
Før møtet:
Begynne på dokumentet for obligatorisk oppgave 2
Fordele mindre omfattende oppgaver til hver
Møtereferat:
Begynte å skrive dokumentasjon for oblig2
Fordelte mindre oppgaver til hver i teamet
Ble enig om hvordan løse kort-GUI og multiplayer
Fullt oppmøte
Fredag 26/2
Før møtet:
Fortsette arbeid på prosjekt
Sette om GUI for kort
Møtereferat:
Ble ferdig med backend for kort, kan nå dele ut fem kort til spiller
Jobbet videre på noen av de mindre oppgavene, som funksjonalitet for tiles
Ble nesten ferdig med oblig2 dokumentet
Mandag 01/03
Før møtet:
Dele det vi har gjort siden sist møte
Jobbe videre med oppgavene våre
Pair programming?
Møtereferat:
Fikk til en SelectBox for å kunne velge kort på GUIen og flytte roboten ut i fra det.
Ellers jobbet vi bare videre med andre funksjonaliteter til oblig 3.
Torsdag 04/03
Før møtet:
Bli ferdig med oblig2 dokumentet
Få pushet alt til development
Møtereferat:
Ble ferdig med dokumentet 
Fikk pushet til development så nå er vi nesten klare til innlevering fredag.
Fredag 05/03
Før møtet
Merge alle branchene, og sjekke at de fungerer som de skal.
Rydde opp litt i oblig dokumenter vårt.
Møtereferat:
Vi fikk merget sammen det vi skulle, og fikk gjort koden klar for innlevering. Alle møtte opp

 
#### Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
- Del opp oppgavene i mindre deler. På den måten enklere å fordele oppgaver.
- Jevnere fordeling av commits
- Planlegge hva vi skal kode med enda tydeligere klassediagram slik at vi vet nøyaktig hva som skal implementeres.
 
 
 
## Deloppgave 2: Krav
Brukerhistorier & akseptansekriterier:
Som spiller ønsker jeg å kunne navigere i en meny slik at jeg kan velge de spillinnstillinger jeg vil ha
Gitt at jeg er en spiller som skal spille spillet:
Må meny være det første jeg ser
Jeg må ha forskjellige options å velge mellom

Arbeidsoppgaver  for å oppfylle dette:
En klasse som holder styr på volum og hvor volum kan økes/senkes
En meny-klasse som inneholder knapper for å spille, endre options og avslutte spillet

Som developer ønsker jeg at andre spillere kan spille mot hverandre samtidig
Gitt at jeg er developer som skal utvikle spillet:
Spillere må kunne se hverandre på brettet
Handlingene til hver spiller må utføres på samme brett

Arbeidsoppgaver for å oppfylle dette:
For at spillerne skal kunne se hverandre på brettet implementerer vi en networking-klasse som skal passe på at kommunikasjon mellom alle spillere kan utføres
For at alle handlinger skal foregå på samme brett må alle bevegelser som en spiller gjør kommuniseres via nettverk til alle medspillere, dette vil ligge i samme network klasse, som også kan motta meldinger om bevegelser andre spillere har gjort.


Som developer ønsker jeg at spillerne beveger seg med kort, så det er vanskeligere å vinne for hver spiller
Gitt at jeg er developer som skal utvikle spillet:
Må ha en kortstokk, med alle kortene i spillet
En spiller skal få ut antall kort fra kortstokken
Spilleren må kunne bevege seg ut fra hvilke kort den har fått



Arbeidsoppgaver for å oppfylle dette:
En Card-klasse som inneholder nødvendige verdier som prioritet og type(move, rotate, etc). 
Card-klassen deler ut 5 random kort fra en kortstokk.
For hver runde vil Card-klassen kommunisere til Player-klassen hvilke bevegelser roboten skal utføre.


Som spiller ønsker jeg å kunne se hvilke kort jeg har valgt, så jeg kan ha kontroll på hvilken rute roboten min skal ta hver runde
Gitt at jeg er en spiller som skal spille spillet:
Må ha en GUI for kort
Må kunne se kortene som er valgt for hver runde

Arbeidsoppgaver for å oppfylle dette:
I GameScreen-klassen vil nederste del av skjermen være tilgjengelig for å legge til kort som spilleren har fått utdelt, slik kan spiller se hvilken rute roboten vil ta.

Som spiller ønsker jeg å kunne se hvor masse damage roboten min har tatt, så det er enklere for meg å planlegge fremover
Gitt at jeg er en spiller som spiller spillet:
 Må kunne se antall damage taken i GUI
Må oppdatere seg hvis roboten tar mer skade

Arbeidsoppgaver for å oppfylle dette:
I GameScreen-klassen i samme vindu som kortene vises vil vi vise hvor mange liv og hvor mye damage roboten har.
Player-klassen vil kommunisere til GameScreen at den har skadet seg og dermed oppdatere GUI.
 
 
Forklar kort hvordan dere har prioritert oppgavene fremover:
Vi har i hovedsak prioritert oppgavene slik de er listet i MVP.
 
Har dere gjort justeringer på kravene som er med i MVP?
Vi har for øyeblikket hatt litt mer fokus på kortene og menyen til spillet, ettersom ingen av oss har god erfaring med å sette opp multiplayer, men vi har ellers ikke gjort noe endringer i MVP-en for øyeblikket.
 
Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
Som beskrevet i forrige oppgave så har vi prioritert cards og meny fremfor multiplayer, men har jobbet med alle punktene på MVP-en. 
Det vi har gjort siden sist er hovedsaklig bedre GUI og meny, samt logikk for kort og actors på brettet (f. eks Laser, Wall etc). Vi har også implementert et rammeverk for multiplayer og kommunikasjon mellom spillere.
Vi har fått implementert et lobby-system hvor en spiller kan velge å “hoste” eller “joine” et team, men fungerer foreløpig kun med én klient. Dette er nok en kjapp bug å fikse. Nå som kommunikasjon mellom spillere er opprettet vil det bli relativt enkelt å oppdatere spillere om andre spillere sine moves og aksjoner i neste sprint. 
Resten av MVP er oppfylt.
Husk å skrive hvilke bugs som finnes i de kravene dere har utført
Vi har enda noen grafiske bugs.
Kan ikke velge første kort i dropdown-meny. 
 
# Deloppgave 3: Kode
Se kode :)
 
 
