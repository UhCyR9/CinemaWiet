# **Specyfikacja**
Projekt został zrealizowany za pomocą Spring boot w wersji 2.7.6. Zastosowaliśmy wersję 17 javy. Skład grupy: Piotr Ryczek, Marcel Spryszyński, Adam Ćwikła  
# **O projekcie**
Celem projektu dla “milestone one”  w repozytorium CinemaWiet jest stworzenie aplikacji dla kina z możliwością dodawania “Usera” do bazy danych. Aplikacja posiada w pełni funkcjonalny graphic user interface z możliwością dodawania nowego użytkownika
# **Aplikacja:**
Omówimy teraz klasy znajdującę się w folderze user

1) w folderze config znajduje się klasa UserConfig, służy ona do testowania oraz jeśli baza danych nie posiada, żadnego użytkownika dodaje nowego z rolą admin
2) w folderze contoller znajduje się klasa UserController odpowiada wywołanie dodawania użytkownika z klasy UserService oraz wywołanie listowania użytkowników z klasy UserService
3) w folderze model znajduje się trzy klasy
   1) User - klasa służąca do tworzenia Usera
   2) UserRequest - pobiera dane z formularza dodawania z GUI
   3) UserRole - klasa enum rodzaje ról w aplikacji
4) w folderze reposority znajduje się interfejs UserRepository dziedziczący po JpaRepository, które zawiera interfejsy API dla podstawowego CRUD
5) w folderze service znajduje się klasa UserService odpowiada ona za dodawanie nowego użytkownika do bazy danych, oraz listowania wszystkich użytkowników

W folderze utils zostały utworzone walidację po stronie aplikacji odnośnie imienia, nazwiska oraz emaila

W folderze view.controller znajduje się klasa odpowiedzialna za wyświetlanie greaficznego interfejsu

# **Baza Danych:**
Baza danych została zrealizowana w PostgreSql. Udało nam się uzyskać bazę danych w chmurzę, dzięki temu nie potrzeba załączać plików z nią związanych w repozytorium. Do tego celu użyliśmy strony ElephantSql <https://www.elephantsql.com/>. Skorzystaliśmy z darmowej wersji tej strony i utworzyliśmy nową bazę. 

W bazie danych na czas “milestone one” znajdują się cztery tabelę:

1. **films** - tabela obejmująca filmy, które będą wyświetlane w kinie. Posiada pola związane z datą puszczania seansu w kinie (*dateFrom*,*datTo*) od kiedy do kiedy film będzie wyświetlany. Oczywiście posiada także pola odnoszące się do nazwy filmu (*filmName*) czasu trwania (*duration*) oraz jego kategorii (category)
1. **halls** - tabela zawiera identyfikator dla każdej sali unikalny oraz ilość miejsc (*numberOfSeats*)
1. **users** - tutaj dodajemy naszych użytkowników wprowadzając ich imię (name), nazwisko (*secondName*), email (*emailAddress*) oraz rolę (*role*). Rola jest to funkcja jaką użytkownik prowadzi w kinie np. admin.
1. **screening** - jest to tabela pozwalająca nam obsługiwać wyświetlanie filmu w danej sali. Posiada foreign hays do halls oraz films. Jej pole *screeningDate* odpowiada za datę oraz godzinę w której film będzie się wyświetlać. Dzięki tej tabeli jesteśmy w stanie obsłużyć salę dodając do niej wiele filmów. Dodatkową kilka sal może wyświetlać ten sam film o tym samym czasie. 

# **Wykres UML:**
![](uml.png?raw=true)
<br /> Rys. 1 wykres uml

# ** MILESTONE 2**

# ** Menu główne **
Menu główne zostało zrealizowane w MainViewController. Zawiera ono przyciski odpowiedzialne do przejścia do kolejnych widoków (w zależności od uprawnień pokazane będzie więcej bądź mniej przejść).

![](MainView.png?raw=true)
<br /> Rys. 2 okno menu głownego aplikacji


# **Autentykacja:**
Autentykacja została wprowadzona wykorzystując wzorzec singleton. Klasą, która przechowuje informacje o aktualnie zalogowanym użytkowniku jest UserContext z pakietu user.

# **Logowanie i role w systemie:**
1. **admin** - 
   1. **mail**: admin@admin.com
   1. **hasło**: admin
   1. Posiada dostęp do wszystkich 3 aktualnie zaimplementowanych widoków czyli user, screening oraz film view
2. **manager** - 
   1. **mail**: man@man.com
   1. **hasło**: manager
   1. Posiada dostęp do film oraz user view
3. **employee** - 
   1. **mail**: user@user.com
   2. **hasło**: user
   3. Posiada dostęp wyłącznie do screning view

![](LoginView.png?raw=true)
<br /> Rys 3. okno logowania

# **Widok Filmów**
Za pomocą FilmViewController zostało zrealizowane okno wyświetlania filmów dostępnych w kinie.
Jest wyświetlana lista pokazująca filmy znajdujące się w bazie danych wraz z ich tytułem, rodzajem kina oraz czasem trwania seansu.  Dodatkowo w górnej części okna widać ikonę odpowiednią za wyświetlanie filmów polecanych przez kino. Każdy film z listy jesteśmy sami w stanie dodać do takiej rekomendacji klikając w jego plakat. 
Istnieje również opcja dodania filmu poprzez otowrzenie formularza.

![](FilmView.png?raw=true)
<br /> Rys 4. okno listy filmów

# ** Widok Seansów **
Za pomocą ScreeningViewController  zostało zrealizowane okno do dodawania seansu danego filmu w wybranej sali. Została wprowadzona walidacja sprawdzająca czy o tej godzinie jest już jakiś film w tej sali, czy dane zostały poprawnie wprowadzone oraz czy dany seans jest wyświetlany w kinie o tej dacie. 

![](ScreeningView.png?raw=true)
<br /> Rys 5. okno seansów

# ** Widok użytkowników **
Widok użytkowników nie przeszedł zmian względem milestone one.

![](UserView.png?raw=true)
<br /> Rys 6. Widok użytkowników


Dodatkowo każde okno zawiera przycisk cofania do menu głównego.

# ** MILESTONE 3**

![](StatisticsView.png?raw=true)
<br /> Rys 7. Widok statystyk

Dodaliśmy widok statystyk, który pokazuje nam najpopulrniejsze filmy puszczane w kinie, oraz najpopularniejsze kategorie filmów.

![](ReservationView.png?raw=true)
<br /> Rys 8. Widok rezerwacji

Dodaliśmy widok rezerwacji, który pokazuje nam zarezerwowane miejsca w sali, oraz umożliwia nam zarezerwowanie nowego miejsca.

![](SendMailView.png?raw=true)
<br /> Rys 9. Widok wysyłania maili

Dodaliśmy widok wysyłania maili, który umożliwia nam wysłanie maila do wszystkich pracowników z informacją o nowych rekomendacjach filmów.


Ponadto, znormalizowaliśmy wielkość widoków oraz zmieniliśmy wygląd naszej aplikacji.

![](JsonView.png?raw=true)
<br /> Rys 10. Konfiguracja za pomocą json

Dodaliśmy również konfigurację sal poprzez plik json.




