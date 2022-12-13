# **Specyfikacja**
Projekt został zrealizowany za pomocą Spring boot w wersji 2.7.6. Zastosowaliśmy wersję 17 javy. Skład grupy: Piotr Ryczek, Marcel Spryszyński, Adam Ćwikła  
# **O projekcie**
Celem projektu dla “milestone one”  w repozytorium CinemaWiet jest stworzenie aplikacji dla kina z możliwością dodawania “Usera” do bazy danych. Aplikacja posiada w pełni funkcjonalny graphic user interface z możliwością dodawania nowego użytkownika
# **Aplikacja:**
Omówimy teraz klasy znajdującę się w folderze user

1) w folderze config znajduje się klasa UserConfig, służy ona do testowania oraz jeśli baza danych nie posiada, żadnego użytkownika dodaje nowego z rolą admin
1) w folderze contoller znajduje się klasa UserController odpowiada wywołanie dodawania użytkownika z klasy UserService 
1) w folderze model znajduje się klasa 
1) w folderze reposority znajduje się klasa UserRepository
1) w folderze service znajduje się klasa UserService odpowiada ona za dodawanie nowego użytkownika do bazy danych, oraz listowania wszystkich użytkowników
# **Baza Danych:**
Baza danych została zrealizowana w PostgreSql. Udało nam się uzyskać bazę danych w chmurzę, dzięki temu nie potrzeba załączać plików z nią związanych w repozytorium. Do tego celu użyliśmy strony ElephantSql <https://www.elephantsql.com/>. Skorzystaliśmy z darmowej wersji tej strony i utworzyliśmy nową bazę. 

W bazie danych na czas “milestone one” znajdują się cztery tabelę:

1. **films** - tabela obejmująca filmy, które będą wyświetlane w kinie. Posiada pola związane z datą puszczania seansu w kinie (*dateFrom*,*datTo*) od kiedy do kiedy film będzie wyświetlany. Oczywiście posiada także pola odnoszące się do nazwy filmu (*filmName*) czasu trwania (*duration*) oraz jego kategorii (category)
1. **halls** - tabela zawiera identyfikator dla każdej sali unikalny oraz ilość miejsc (*numberOfSeats*)
1. **users** - tutaj dodajemy naszych użytkowników wprowadzając ich imię (name), nazwisko (*secondName*), email (*emailAddress*) oraz rolę (*role*). Rola jest to funkcja jaką użytkownik prowadzi w kinie np. admin.
1. **screening** - jest to tabela pozwalająca nam obsługiwać wyświetlanie filmu w danej sali. Posiada foreign hays do halls oraz films. Jej pole *screeningDate* odpowiada za datę oraz godzinę w której film będzie się wyświetlać. Dzięki tej tabeli jesteśmy w stanie obsłużyć salę dodając do niej wiele filmów. Dodatkową kilka sal może wyświetlać ten sam film o tym samym czasie. 

# **Wykres UML:**
![](uml.png?raw=true)

