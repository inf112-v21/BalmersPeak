
#Deloppgave 1 - Prosjekt og prosjektstruktur

###NB!
Go to game funker ikke lengre, for å spille trykker du på play, så kan du enten hoste eller joine lobby, for å spille singleplayer, bare trykker du host så start game

###Hvordan fungerer rollene i teamet?
Vi synes rollene fungerer fint. Vi er fornøyde med både teamleder og kundekontakt sin innsats. Ingen oppdateringer av roller er nødvendig.

###Trenger dere andre roller?
Nei, rollene fungerer nokså greit for øyeblikket
Teamleder: Innebærer å organisere teamet, ordne møter.
Kundekontakt: innebærer å ta kontakt med gruppeleder for oppklaringer om oppgaver og forventninger til produkt.
Utvikler: Innebærer ingen store organisatoriske oppgaver, kun utvikling av prosjekt og kommunisering med andre utviklere, teamleder og kundekontakt.

###Erfaringer team-messig og prosjektmetodikk
Vi synes vi har valgt riktig prosjektmetodikk. Sprintene og at man møtes hyppig passer oss veldig godt.

###Kort retrospektiv
Siden sist innlevering har vi nå fordelt mindre oppgaver og synes dette fungerer fint. Er enklere for alle å pushe kode. Har hatt mindre tid til å jobbe med prosjektet grunnet andre obligatoriske oppgaver og prosjekter, så har ikke fått hatt like mange møter. Skal kjøre på med flere møter i de siste dagene før innlevering

###Forbedringer å følge opp til neste sprint
Fortsatt litt skjevfordeling i arbeidet, men dette kommer mer av at noen oppgaver og implementasjoner er mer krevende enn andre. Likevel prøve å få det bedre til neste
Som et resultat av arbeidsmengde i andre fag har vi ikke vært like god på kommunikasjon som oblig 2, så prøve å få det like bra igjen til neste
Ha større fokus på å skrive tester

###Hvordan er gruppedynamikken og hvordan fungerer kommunikasjon?
Kommunikasjon foregår i hovedsak på Discord og på fysiske møter. I starten etter oblig 2 var det litt rot i møtene mtp. innleveringer i andre fag, men etter hvert har det kommet seg igjen.


###Møter:
Møte 08/03
Før møtet:
Planlegge presentasjon.
Lage flere issues.

Møtereferat:
Lagt presentasjon.
Lagte flere issues og fordele de innad i gruppen.

Møte 15/03
Før møtet:
Lage nye issues?
Dele det vi har gjort siden sist.

Møtereferat
Alle fortsatte å jobbe med de arbeidsoppgavene vi ble utdelt

Møte 18/03
Før møtet:
Få skrevet det obligatorisk dokumentet til oblig 3.
Jobbe videre med de tildelte arbeidsoppgavene.

Møtereferat:
Begynte å jobbe med dokumentet til oblig 3 og kom godt i gang med det.

Møte 19/03
Før møtet:
Fortsette arbeidet med dokumentet til oblig 3.

Møtereferat:
Fortsatte arbeidet med dokumentet og jobbet videre med arbeidsoppgavene våre.

Møte  22/03
Før møtet:
komme så langt som mulig på dokumentet til oblig 3.
Fortsette med arbeidsoppgavene.

møtereferat:
Jobbet med dokumentet og kom ca. halvveis på det.
Fortsatte med å få funksjonalitet på de ulike objektene på brettet og oppdatert gui på kortene.

Møte 25/03
Før møtet:
Få fullført dokumentet til oblig 3.
komme så langt som mulig på de utdelte oppgavene.

Møtereferat:
Fikk nesten fullført dokumentet til oblig 3, mangler bare litt finpuss.
Fikk laget en del funksjonalitet til objektene på brettet og en del tester til det.

Møte 26.03
Før møtet:
Finpusse dokumentet til oblig 3.
Merge development til master of fullføre innleveringen.

Møtereferat:
Fikk levert oblig 3.

Fullt oppmøte på alle møtene.


##Deloppgave 2: Krav

###Hvilken krav har dere prioritert og hvor langt har dere kommet fra det dere gjorde forrige gang?

Vi har jobbet mer med LAN, kort GUI og funksjonalitet siden sist. Den originale MVP-en begynner å se noenlunde ferdig ut, og vi har derfor begynt å takle de forskjellige brett elementene. Det vil si å få ting som gears, samlebånd, lasere, hull, vegger, osv til å fungere. For øyeblikket er det viktigste at de forskjellige brett elementene fungerer som de skal. Det vil si at vi ikke laget selve game loopen. Det blir mest sannsynlig en MVP for neste oblig.

###Funksjonalitet
Vi har laget en bedre GUI, du kan nå se kortene, helsepoeng og liv på skjermen. GUI-en oppdaterer seg etter når f.eks spilleren mister helse, eller mister liv. Har og implementert multiplayer, slik at flere spillere kan spille på samme brett samtidig. Har og laget funksjonalitet for flere tiles enn sist innlevering, og laget mer tester. Spill-logikken er nesten helt på plass, bortsett fra brett-elementer. Har også nå implementert rundebasert logikk, slik at hver spilleren velger 5 kort, så blir robotene beveget etter dem med høyest prioritet.





###Brukerhistorier:

Som spiller ønsker jeg at robotene tar skade når de går på en laser på spillbrettet slik at jeg vet at robotene tar skade hvis de er i en rute hvor de blir truffet av en laser.
Gitt at jeg er en spiller som skal spille spillet:
Må kunne se laser.
Må ta skade hvis robot blir truffet av laser.
Arbeidsoppgaver for å oppfylle dette:
Lage en laser klasse.
Implementere funksjonalitet til laser i board klassen slik at robotene tar skade når de blir truffet av laser.

Som spiller ønsker jeg at robotene roterer når de går på et tannhjul slik at jeg kan planlegge en rute hvor jeg bruker tannhjul når jeg spiller.
Gitt at jeg er en spiller som skal spille spillet:
Må kunne se tannhjulet.
Må rotere når en robot er på tannhjulet.
Arbeidsoppgaver for å oppfylle dette:
Lage en gear klasse.
implementere funksjonalitet til gear i board board klassen slik at robotene roteres når de er på tannhjulene.

Som spiller ønsker jeg at robotene mister et liv når de faller ned i et hull slik at  jeg vet at robotene mister ett liv når de går i et hull.
Gitt at jeg er en spiller som skal spille spillet:
Må kunne se hullet
Må miste et liv når en robot går i hullet
Arbeidsoppgaver for å oppfylle dette:
Lage en hole klasse som henter ut posisjonen til hullet.
Implementere funksjonalitet til hole slik at robotene mister ett liv når de går i hullet.

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
I GameScreen-klassen i samme vindu som kortene vises vil vi vise hvor mye damage roboten har.
Player-klassen vil kommunisere til GameScreen at den har skadet seg og dermed oppdatere GUI.

Som spiller ønsker jeg å kunne se hvor mange liv jeg har igjen, så det er enklere for meg å planlegge fremover
gitt at jeg er en spiller som spiller spillet:
Må kunne se hvor mange liv jeg har i GUI
Må oppdatere seg hvis jeg mister et liv
Arbeidsoppgaver for å oppfylle dette:
I GameScreen-klassen i samme vindu som kortene og helsepoeng, vil vi også vise antall liv spilleren har igjen
Player-klassen vil kommunisere til GameScreen at den har skadet seg og dermed oppdatere GUI.

Som spiller ønsker jeg å ikke kunne gå videre dersom jeg møter en vegg
gitt at jeg er en spiller som spiller spillet:
Må kunne se veggene
Ikke flytte forbi veggene
Arbeidsoppgaver for å oppfylle dette:
I GameScreen er det en shouldMove metode som sjekker om Direction til player og vegg er den samme. Hvis ja så kan ikke spilleren flytte seg videre.


###Bugs:
Har vi noen bugs vi kjenner til?
Med noen skjermer, så vil GUIen sine elementer bli plassert feil. Det fins fortsatt tilfeller der veggene kan blir gått gjennom, eller spilleren sitter seg fast. Når spilleren går går på en conveyor og den flytter deg, vil den bare oppdatere spilleren på GUI-en, og ikke (x,y) verdiene

Veggene funker bare dersom man står i tilen veggen er i. Hvis en vegg står i North vil man kunne flytte inn i den tilen fra North.




###Manual testing
Skriv handlemove() i render og ta vekk Gdx.input.setInputProcessor(stage) i show(), da kan du bevege spiller men WSAD.






#Deloppgave 3: Produktleveranse og kodekvalitet

se kode :)
