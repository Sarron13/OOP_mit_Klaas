# Simple CRUD Server Application

Server Client Application to manage your favorite Star Wars and Lord of the Rings characters
The application includes a REST-API and a frontpage.
The data ist stored on the hard drive and will we restored when server starts up again.

## Requirements
- Java JDK 16



## Installation

- Fist download this repository:  
```git clone https://github.com/Sarron13/OOP_mit_Klaas.git```
- change directory into jar dir  
  ```cd OOP_mit_Klaas/jar```
- Then run the jar archive with:   
```java -jar ./oop-project-0.0.1-SNAPSHOT.jar```
- Go to [localhost:8080](http://localhost:8080)

## Anleitung
- Einen neuen Character erstellen

Oben rechts auf **Character hinzufügen** klicken und Formular ausfüllen und abschicken. Es kann ein Star Wars oder
Herr der Ringe Character erstellen.

- Einen Character Löschen

In der Tabelle die Reihe des zu löschenden Character auf **Delete** klicken. Den nachfolgenden Dialog bestätigen.


- Einen Character updaten

In der Tabelle die Reihe des zu löschenden Character auf **Edit** klicken. Im Dialog den gewünschten Werte eintragen
und bestätigen.

- Detailansicht

In der Tabelle auf eine Reihe klicken.

- Characters filtern

Oben in der Suche den String nach dem in den Vor- und Nachnamen gefiltert wird.


## Aufbau des Projekts

- Frontend Website

Als Frontend haben wir uns eine Website ausgesucht, da diese sehr flexibel, zeitgemäß und schön gestaltet
werden kann. Bis auf Bootstrap benutzen wir auch keine weiteren Bibliotheken. 

- Backend mit Spring Rest

Da wir gerne als UI eine Website benutzen möchten, müssen wir unsere Daten aus der Datenbank
als über REST API zur Verfügung stellen. Um das Rad nicht neu zuerfinden haben wir uns nach
bekannten Bibliotheken umgeschaut. Schnell sind wir auf Spring Rest gestoßen, das uns alle 
Funktionen bot, die wir benötigten. 

Dazu zählten die Erzeugung verschiedener HTTP-Endpouints und die HTTP-CRUD Anfragen GET, POST,
UPDATE und DELETE.

Außerdem wird De-/Serialisierung der Java Objekte zu JSON bereits unterstützt müssen nur noch
konfiguriert werden.


