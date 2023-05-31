-- Einfügen von Beispieldaten in die Tabelle "Fahrzeugfarbe"
INSERT INTO "Fahrzeugfarbe" ("Farbname") VALUES
    ('Rot'),
    ('Blau'),
    ('Grün'),
    ('Gelb');

-- Einfügen von Beispieldaten in die Tabelle "Hersteller"
INSERT INTO "Hersteller" ("Name") VALUES
    ('BMW'),
    ('Mercedes-Benz'),
    ('Audi'),
    ('Volkswagen');

-- Einfügen von Beispieldaten in die Tabelle "Fahrzeugtyp"
INSERT INTO "Fahrzeugtyp" ("Bezeichnung") VALUES
    ('Limousine'),
    ('SUV'),
    ('Kombi'),
    ('Cabrio');

-- Einfügen von Beispieldaten in die Tabelle "Fahrzeugmodell"
INSERT INTO "Fahrzeugmodell" ("Hersteller_ID", "Fahrzeugtyp_ID") VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4);

-- Einfügen von Beispieldaten in die Tabelle "Anrede"
INSERT INTO "Anrede" ("Anredewort") VALUES
    ('Herr'),
    ('Frau'),
    ('Dr.');

-- Einfügen von Beispieldaten in die Tabelle "Adresse"
INSERT INTO "Adresse" ("Straße", "Stadt", "Postleitzahl", "Hausnummer") VALUES
    ('Musterstraße 1', 'Musterstadt', '12345', '12'),
    ('Beispielweg 2', 'Beispielstadt', '67890', '34');

-- Einfügen von Beispieldaten in die Tabelle "Mitarbeiter"
INSERT INTO "Mitarbeiter" ("Vorname", "Nachname", "Adresse_ID", "Anrede_ID", "Lohn", "BeschäftigungsStart", "Verfügbar") VALUES
    ('Max', 'Mustermann', 1, 1, 3000.0, '2020-01-01', 1),
    ('Erika', 'Musterfrau', 2, 2, 3500.0, '2019-05-15', 1);

-- Einfügen von Beispieldaten in die Tabelle "Kunde"
INSERT INTO "Kunde" ("Vorname", "Nachname", "Adresse_ID", "Ansprechpartner_ID", "Anrede_ID") VALUES
    ('Klaus', 'Kundig', 1, 1, 1),
    ('Sabine', 'Kundenfrau', 2, 2, 2);

-- Einfügen von Beispieldaten in die Tabelle "Fahrzeug"
INSERT INTO "Fahrzeug" ("Modell_ID", "Kaufpreis", "Mietpreis", "IstVermietet", "MietKunde_ID", "IstVerkauft", "KaufKunde_ID", "LetzterTÜV", "AnzVorherigeBesitzer", "Kilometerstand") VALUES
    (1, 20000.0, 50.0, 0, NULL, 1, 2, '2022-01-01', 1, 50000),
    (2, 30000.0, 60.0, 1, 1, 0, NULL,    '2023-05-15', 2, 40000),
    (3, 25000.0, 55.0, 0, NULL, 0, NULL, '2022-11-30', 0, 10000),
    (4, 35000.0, 70.0, 1, 2, 0, NULL, '2023-03-10', 2, 20000);

-- Einfügen von Beispieldaten in die Tabelle "HatFarben"
INSERT INTO "HatFarben" ("Fahrzeug_ID", "Farb_ID") VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (3, 3),
    (4, 2),
    (4, 3);

-- Einfügen von Beispieldaten in die Tabelle "HatAnsprechpartner"
INSERT INTO "HatAnsprechpartner" ("Kunde_ID", "Mitarbeiter_ID") VALUES
    (1, 1),
    (1, 2),
    (2, 2);

