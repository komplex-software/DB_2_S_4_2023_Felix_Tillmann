-- Aktualisierte Form der Autohaendler DB für MS-SQL
DROP DATABASE IF EXISTS Autohaendler;
CREATE DATABASE Autohaendler;
USE Autohaendler;

DROP TABLE IF EXISTS "Fahrzeugfarbe";
DROP TABLE IF EXISTS "Hersteller";
DROP TABLE IF EXISTS "Fahrzeugtyp";
DROP TABLE IF EXISTS "Fahrzeugmodell";
DROP TABLE IF EXISTS "Anrede";
DROP TABLE IF EXISTS "Adresse";
DROP TABLE IF EXISTS "Kunde";
DROP TABLE IF EXISTS "Fahrzeug";
DROP TABLE IF EXISTS "Mitarbeiter";

CREATE TABLE
    "Fahrzeugfarbe" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Farbname" TEXT NOT NULL,
        PRIMARY KEY ("ID")
    );

CREATE TABLE
    "Hersteller" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Name" TEXT NOT NULL,
        PRIMARY KEY ("ID")
    );

CREATE TABLE
    "Fahrzeugtyp" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Bezeichnung" TEXT NOT NULL,
        PRIMARY KEY ("ID")
    );

CREATE TABLE
    "Fahrzeugmodell" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Hersteller_ID" INT NOT NULL,
        "Fahrzeugtyp_ID" INT NOT NULL,  -- n:1
        PRIMARY KEY ("ID"),
        FOREIGN KEY ("Hersteller_ID") REFERENCES "Hersteller" ("ID"),
        FOREIGN KEY ("Fahrzeugtyp_ID") REFERENCES "Fahrzeugtyp" ("ID")
    );

CREATE TABLE
    "Anrede" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Anredewort" TEXT NOT NULL,
        PRIMARY KEY ("ID")
    );

CREATE TABLE
    "Adresse" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Straße" TEXT NOT NULL,
        "Stadt" TEXT NOT NULL,
        "Postleitzahl" TEXT NOT NULL,
        "Hausnummer" TEXT NOT NULL,
        PRIMARY KEY ("ID")
    );

CREATE TABLE
    "Mitarbeiter" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Vorname" TEXT NOT NULL,
        "Nachname" TEXT NOT NULL,
        "Adresse_ID" INT NOT NULL, -- n:1
        "Anrede_ID" INT NOT NULL, -- n:1
        "Lohn" FLOAT NOT NULL,
        "BeschäftigungsStart" DATE NOT NULL,
        "Verfügbar" BIT NOT NULL,
        PRIMARY KEY ("ID"),
        FOREIGN KEY ("Adresse_ID") REFERENCES "Adresse" ("ID"),
        FOREIGN KEY ("Anrede_ID") REFERENCES "Anrede" ("ID"),
        CHECK ( "Lohn" >= 0 )
    );

CREATE TABLE
    "Kunde" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Vorname" TEXT NOT NULL,
        "Nachname" TEXT NOT NULL,
        "Adresse_ID" INT NOT NULL, -- n:1
        "Ansprechparter_ID" INT NOT NULL,
        "Anrede_ID" INT NOT NULL, -- n:1
        PRIMARY KEY ("ID"),
        FOREIGN KEY ("Adresse_ID") REFERENCES "Adresse" ("ID"),
        FOREIGN KEY ("Ansprechparter_ID") REFERENCES "Mitarbeiter" ("ID"),
        FOREIGN KEY ("Anrede_ID") REFERENCES "Anrede" ("ID")
    );

CREATE TABLE
    "Fahrzeug" (
        "ID" INT NOT NULL IDENTITY(1,1),
        "Modell_ID" INT NOT NULL,
        "Kaufpreis" FLOAT NOT NULL,
        "Mietpreis" FLOAT NOT NULL,
        "IstVermietet" BIT NOT NULL,
        "MietKunde_ID" INT, -- Darf Null sein
        "IstVerkauft" BIT NOT NULL,
        "KaufKunde_ID" INT, -- Darf Null sein
        "LetzterTÜV" DATE NOT NULL,
        "AnzVorherigeBesitzer" INT NOT NULL,
        "Kilometerstand" INT NOT NULL,
        PRIMARY KEY ("ID"),
        FOREIGN KEY ("Modell_ID") REFERENCES "Fahrzeugmodell" ("ID"),
        FOREIGN KEY ("MietKunde_ID") REFERENCES "Kunde" ("ID"),
        FOREIGN KEY ("KaufKunde_ID") REFERENCES "Kunde" ("ID"),
        CHECK ( "AnzVorherigeBesitzer" >= 0 ),
        CHECK ( "Kilometerstand" >= 0 )
    );

-- n:m
-- Eine Farbe kann von mehreren Fahrzeugen genutzt werden,
-- Es gibt Fahrzeuge, die mehrere Farben haben
CREATE TABLE
    "HatFarben" (
        "Fahrzeug_ID" INT NOT NULL,
        "Farb_ID" INT NOT NULL,
        FOREIGN KEY ("Fahrzeug_ID") REFERENCES "Fahrzeug" ("ID"),
        FOREIGN KEY ("Farb_ID") REFERENCES "Fahrzeugfarbe" ("ID")
    );

-- n:m
-- Ein Kunde kann mehrere Ansprechpartner haben,
-- Ein Mitarbeiter kann ebenso für mehrere Kunden zuständig sein
CREATE TABLE
    "HatAnsprechpartner" (
        "Kunde_ID" INT NOT NULL,
        "Mitarbeiter_ID" INT NOT NULL,
        FOREIGN KEY ("Kunde_ID") REFERENCES "Kunde" ("ID"),
        FOREIGN KEY ("Mitarbeiter_ID") REFERENCES "Mitarbeiter" ("ID")
    )
