# Fitness aplikácia
Semestrálny projekt kurzu Extrémne programovanie

## Návrh projektu
Navrhovaná aplikácia bude slúžiť športovcom, ktorí si chcú zaznamenávať informácie o svojom tele - výška, váha, obvody svalov atď. v pravidelných intervaloch, napríklad týždenne. Po zadaní hlavných parametrov bude vedieť aplikácia vypočítať telesné BMI ako aj hodnoty podielu svalov, tuku, kostí a vody. Používateľ si bude môcť pravidelne vytvárať záznam s týmito hodnotami. Aplikácia bude vedieť robiť reporty, prepočítavať nárast/pokles rôznych veličín za zvolené obdobie. Aplikácia bude bežať na počítači, pričom k jej používaniu nebude potrebné internetové pripojenie. Navrhovaná aplikácia je určená pre jedného používateľa a tak nebude potrebné vykonávať proces prihlásenia sa.


## Zoznam činností programu:

### Používateľské rozhranie:
 - Vystavenie GUI obrazovky pre zadanie informácií o svojom tele a veku - 12h
 - Vystavenie GUI obrazovky pre vytvorenie záznamu o svojej aktuálnej hmotnosti a príjme kalórií - 12h
 - Vystavenie GUI obrazovky pre zobrazenie štatistického prehľadu pokroku - 12h

### Backend funkcionalita:
 - Uloženie používateľského vstupu do JSON súboru - 4h
 - Čítanie dát používateľa z JSON súboru - 2h
 - Mechanizmus na výpočet BMI - 2h
 - Mechanizmus na výpočet podielu tuku, svalov, vody, kostí - 4h
 - Výpočet prehľadu za týždeň / mesiac, vytváranie štatistiky - 24h


## Technická špecifikácia

 - **Jazyk:** Java 11
 - **Operačný systém:** MS Windows
 - **Cieľové prostredie:** Desktop aplikácia
 - **Vývojové prostredie:** IntelliJ Idea / Eclipse
 - **Knižnice pre unit testy:** junit, mockito
 - **Používateľské rozhranie:** Java FX
 - **Github repozitár:** https://github.com/kanauro/Fitness-Aplication-Schwarz
 - **Persistencia dát:** JSON štruktúra
