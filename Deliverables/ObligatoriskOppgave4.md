# Oblig 1 - Ballmer’s Peak

## Deloppgave 1: Team og prosjekt

### Møtereferat

Møte 09/04:

Før møtet:
Legge planen for siste oblig. Bli enige om hva som er viktigst å få med til siste innlevering.

Møtereferat:
Møtet varte i 2 timer hvor vi ble enige om at det viktigste var å prøve å få på plass gameloopen, skrive flere tester til prosjektet og få kodet funksjonalitet til game objects. Teammedlemmene fikk utdelt oppgaver i henhold til målene vi satte og jobbet videre med dem.

Møte 16/04:

Før Møtet:
Gå gjennom retteskjemaet til oblig 3 for å se hva vi kan gjøre bedre til siste innlevering.

Møtereferat:
Møtet varte i 1 og en halv time. Vi gikk gjennom retteskjemaet til oblig3 og ble enige om skrive flere tester. Startet også med å skrive oblig 4 dokumentet.

Møte 21/04:

Før møtet:
Prøve å bli ferdig med dokumentet til oblig 4 og få en oppdatering på hvor langt teammedlemmene har kommet på oppgavene sine slik at vi får en oversikt over hva vi rekker å gjøre til innleveringsfristen.

Møtereferat:
Møtet varte i 1 og en halv time, vi jobbet med innleveringsdokumentet til oblig 4 og ble nesten ferdig med det. Vi fikk oversikt over prosjektet slik at vi fikk fokusert på å få ferdig det vi hadde og sørge for at vi er ferdig med det og at spillet kjører uten å kræsje.

Møte 22/04:

Før møtet:
Bli ferdig med alt av kode som skal være med til siste innlevering og pushe alt til github og sørge for at alt vi har gjort funker som det skal og at spillet ikke kræsjer. Og fjerne alt av ubrukt kode og gjør prosjektet klar til innlevering.

Møtereferat:
Møtet varte i 2 timer. Gjorde oss ferdig med det vi holdt på med og pushet alt til github og merget den til master. Vi ble også ferdig med doumentet til oblig 4.

Møte 23/04:

Før møtet:
Få fullført det vi trenger for innlevering og levere hele prosjektet, som blant annet fikse ett par bugs og sørge for at testene kjører.

Møtereferat:
Møtet varte i 1 time. Fikset noen bugs og sørget for at testene kjører. Avsluttet prosjektet og fikk levert det vi hadde.

Alle teammedlemmene var til stede på alle møtene.

### Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?

Vi synes fortsatt rollene fungerer helt fint slik det har gjort hele semesteret egentlig. Alt funker bare bra så ikke nødvendig med oppdateringer.

### Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?

Vi føler de valgene vi har tatt er gode, vi har hatt konkrete mål med sprintene og fått utført det vi har satt som mål. Scrum har funket bra for oss og de hyppige møtene har gjort oss godt som et team.
Det vi kunne gjort bedre er at de som jobbet med elementer i spillet som til slutt skal fungere sammen kunne kommunisert bedre for å redusere hvor mye kode som må endres for å få det til å fungere.

### Gjør et retrospektiv hvor dere vurderer hvordan prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?

Vi syntes kommunikasjonen har vært jevnt over god, og vi har hatt hyppige møter for å rette opp eventuelle feil og vurdere pågående sprinter.
Det kunne vi kunne gjort bedre mtp sprinter er å sette mer konkrete overordnede mål til hvordan spillet skal fungere på slutten av en sprint. Det var heller målet å fullføre de mindre oppgavene vi hadde på project board. Å sette overordnede mål kunne kanskje hjulpet oss prioritere hvilke oppgaver som skulle fullføres.
Noe annet vi kunne gjort bedre er å estimere tiden en arbeidsoppgave tar, og derav delegere oppgavene litt bedre og kanskje dele de opp i mindre deler.
Det vi ville gjort annerledes om vi begynte på nytt er å legge en tydeligere plan for hvordan logikken i spillet skulle implementeres. Vi jobbet alle med forskjellige deler av spillet som board elements, player movement osv som ikke nødvendigvis var like enkelt å “koble sammen” da det var ferdigstilt.

### Hvordan fungerer gruppedynamikken og kommunikasjonen nå i forhold til i starten?

Gruppedynamikken har bare forbedret seg over tid ettersom vi er blitt mer kjent med hverandre og lært å jobbe i team. Kommunikasjonen er god og blitt mindre rotete

Project Board:

<img src="assets/images/projectboard.jpg"  width="500">

## Deloppgave 2: Krav


### Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang
- Vi har jobbet videre med gameloop og board elementer som skal utføres riktig. Vi har også jobbet med flere tester og manuelle tester.
- Man kan velge tre kort og robotene vil bevege seg etter disse
- All logikk for å vinne, gå på hull og wrenches er implementert, men ikke lagt inn i game loop og kommunisert over nettet til andre spillere

### Brukerhistorier
Som spiller ønsker jeg å ikke kunne gå videre dersom jeg møter en vegg

Gitt at jeg er en spiller som spiller spillet:
1. Må kunne se veggene
2. Ikke flytte forbi veggene

Arbeidsoppgaver for å oppfylle dette:
- GameScreen er det en shouldMove metode som sjekker om Direction til player og vegg er den samme. Den sjekker også om Direction til player er motsatt av veggen på tilen den skal flytte seg inn på. Hvis ja så kan ikke spilleren flytte seg videre.

Som developer ønsker jeg at når runden starter, vil alle brett elementene blir håndtert, og alle posisjoner til element vil bli oppdatert, og alle effekter bli kjørt

Gitt at jeg er en developer om developer spillet:
1. Alle brett elementer må bli håndtert
2. Alle posisjoner som har blitt endret må vises på brettet
3. Og alle effekter som skal bli gjort i rundet må bli gjort

Arbeidsoppgaver for å oppfylle dette:
- I Game klassen skal alle rundene bli implementert, alle trekkene som spillerne har gjort, skal kjøres i rett rekkefølge (etter prioritet), collision bli håndtert, viss spillerne mister liv eller helse, bør dette og bli oppdatert, og nye posisjoner må bli satt, viss dem har blitt endret

Som spiller ønsker jeg at viss jeg går i en annen spiller at den blir dyttet

Gitt jeg er en spiller som spiller spillet:
1. Hvis en robot blir truffet av en annen, skal den flyttes en framfor posisjonen roboten som traff den, i samme retning
2. Begge posisjonene til robotene må bli oppdatert

Arbeidsoppgaver for å oppfylle dette:
- I Board klassen, må move funksjonen ta til hensyn viss roboten som blir flyttet treffer en annen.

Som developer ønsker jeg at hvis en spiller står foran en pusher, at den skal bli dyttet i løpet av runden

Gitt jeg er en developer som utvikler spillet:
1. Hvis en spiller står foran en pusher, vil den i løpet av runden bli dyttet
2. Posisjonen til roboten må bli oppdatert

Arbeidsoppgaver for å oppfylle dette:
- En pusher klasse med all funksjonaliteten til en pusher. Og denne klassen må bli implementert i Board


Som developer ønsker jeg at hvis en spiller står på en conveyorbelt, så vil den blir dyttet

Gitt jeg er en developer som utvikler spillet:
1. Hvis en spiller er på en conveyorbelt vil den i løpet av runden bli dyttet
2. Posisjonen til roboten må bli oppdatert

Arbeidsoppgaver for å oppfylle dette:
- En conveyorbelt klasse med all funksjonaliteten til en conveyorbelt, må ta til hensyn de forskjellige typene av conveyors, og denne klassen må implementert i board



Bugs:

- Spillere som ikke er host tar ikke damage, selv om robots blir registrert og truffet
- Testene i GameScreenTest fungerer ikke når alle blir kjørt samtidig, men de fungerer når de blir kjørt inviduelt
- Hvis man blir dyttet ut av brettet av conveyors så crasher spillet
- De som joiner spillet tar ikke damage, bare host
- Når man tar damage eller blir fikset får/mister man 3 liv



Ujevn fordeling av commits:

Vi arbeider slik at alle lager sine egne branches, som de jobber med, og så merger til development branchen. Så tar et vilkårlig gruppemedlem å merger development til master, så alle sine bidrag vil ikke vises på master commits. Noen gruppemedlemmer har også hatt andre arbeidsoppgaver enn utvikling og har derfor hatt mer fokus på dette enn utvikling.


## Deloppgave 3: Produktleveranse og kodekvalitet

Se kode :)

### Manuelle tester:
For alle tester kjør Main

Gå i options
- Justere volum på options og sjekke at den faktisk justeres opp eller ned
  
Start et spill
- Klikk på kort og sjekk at roboten faktisk flyttes i samhandling med kortene som er blitt valgt

Sjekke at multiplayer fungerer
- For at de andre spillerne skal kunne connecte til host må enten hosten ha port-forwardet eller at de er på samme nettverk

For singleplayer
- Klikk på "play" så "host" så trykk på "start game" uten at noen andre har koblet seg til
