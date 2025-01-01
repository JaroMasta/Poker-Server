## Poker-Server
1. Przykład wygenerowany na bazie archetypu (moduł main):
   com.github.charlie-cityu.archetypes:docs-city-archetype-quickstart
   (A variation on the maven-archetype-quickstart with source set to 1.8,
    build to executable jar with dependencies and junit 4.12. File names have been changed
    to Main.java and MainTest.java. Directory structure remains consistent with the Maven
    standard.)
Cykl życia klienta serwera:
1. Wchodzi na serwer
2. Zostaje mu przypisana nowa instacja klasy clienthandler oraz player
3. wybiera grę z pośród gier czekających na graczy lub tworzy nową grę (GameSession)
3.1 Jeśli tworzy nową musi zdefiniowac jaki rodzaj gry
3.1.1 Jeśli nie ma takiej gry musi wybrac jeszcze raz powrót do 3.1
3.2 Wybiera ile graczy z przedziału dla danej gry (GameRules maxPlayers)
3.2.1 Jeśli wybrał złą liczbę graczy musi wybrac jeszcze raz powrót do 3.2
4. Czeka na resztę graczy
5. Gra się rozpoczyna //TODO logika gry jakie komunikaty może przesłać gracz
6. Gra się zakończyła, gracz wybiera czy chce wrócić do panelu dostępnych gier (instancja player zostaje taka sama) czy chce zakończyć grę
6.1 Gracz wybiera że chce zakończyć, wówczas wyświetla mu się komunikat o liczbie żetonów które uzyskał
7. Gracz wybiera że chce dołączyć do innej gry, powrót do punktu 3.


