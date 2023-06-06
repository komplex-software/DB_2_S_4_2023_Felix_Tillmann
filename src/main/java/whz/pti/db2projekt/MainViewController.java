// TODO: Eingaben löschen nach operation
// TODO: SQL fehler sollten dem nutzer mitgeteilt werden

package whz.pti.db2projekt;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import whz.pti.db2projekt.model.*;

import java.security.Permission;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class MainViewController {
    // ------------- Adresse --------------
    @FXML
    private ComboBox<Integer> adresse_id;
    @FXML
    private TextField adresse_straße;
    @FXML
    private TextField adresse_stadt;
    @FXML
    private TextField adresse_postleitzahl;
    @FXML
    private TextField adresse_hausnummer;


    @FXML
    private Button adresse_speichern;
    @FXML
    private Button adresse_löschen;
    @FXML
    private Button adresse_anlegen;
    @FXML
    private TableView adresse_anzeige;
    // ------------- Adresse --------------
    // -------------- Anrede --------------
    @FXML
    private ComboBox<Integer> anrede_id;
    @FXML
    private TextField anrede_anredewort;

    @FXML
    private Button anrede_speichern;
    @FXML
    private Button anrede_löschen;
    @FXML
    private Button anrede_anlegen;
    @FXML
    private TableView anrede_anzeige;
    // -------------- Anrede --------------
    // ------------ Fahrzeuge -------------
    @FXML
    private ComboBox<Integer> fahrzeug_id;
    @FXML
    private TextField fahrzeug_kaufpreis;
    @FXML
    private TextField fahrzeug_mietpreis;
    @FXML
    private ComboBox<Integer> fahrzeug_kaufkunde;
    @FXML
    private ComboBox<Integer> fahrzeug_mietkunde;
    @FXML
    private TextField fahrzeug_letzterTuev;
    @FXML
    private TextField fahrzeug_anzVorherigeBesitzer;
    @FXML
    private TextField fahrzeug_kilometerstand;

    @FXML
    private ComboBox<Integer> fahrzeug_modell;
    @FXML
    private CheckBox fahrzeug_istVermietet;
    @FXML
    private CheckBox fahrzeug_istVerkauft;
    @FXML
    private Button fahrzeug_speichern;
    @FXML
    private Button fahrzeug_löschen;
    @FXML
    private Button fahrzeug_anlegen;
    @FXML
    private TableView fahrzeug_anzeige;
    // ------------ Fahrzeuge -------------
    // ---------- Fahrzeugfarbe -----------
    @FXML
    private ComboBox<Integer> fahrzeugfarbe_id;
    @FXML
    private TextField fahrzeugfarbe_farbname;

    @FXML
    private Button fahrzeugfarbe_speichern;
    @FXML
    private Button fahrzeugfarbe_löschen;
    @FXML
    private Button fahrzeugfarbe_anlegen;
    @FXML
    private TableView fahrzeugfarbe_anzeige;
    // ---------- Fahrzeugfarbe -----------
    // ---------- Fahrzeugmodell ----------
    @FXML
    private ComboBox<Integer> fahrzeugmodell_id;
    @FXML
    private ComboBox<Integer> fahrzeugmodell_hersteller;
    @FXML
    private ComboBox<Integer> fahrzeugmodell_fahrzeugtyp;

    @FXML
    private Button fahrzeugmodell_speichern;
    @FXML
    private Button fahrzeugmodell_löschen;
    @FXML
    private Button fahrzeugmodell_anlegen;
    @FXML
    private TableView fahrzeugmodell_anzeige;
    // ---------- Fahrzeugmodell ----------
    // ----------- Fahrzeugtyp ------------
    @FXML
    private ComboBox<Integer> fahrzeugtyp_id;
    @FXML
    private TextField fahrzeugtyp_bezeichnung;

    @FXML
    private Button fahrzeugtyp_speichern;
    @FXML
    private Button fahrzeugtyp_löschen;
    @FXML
    private Button fahrzeugtyp_anlegen;
    @FXML
    private TableView fahrzeugtyp_anzeige;
    // ----------- Fahrzeugtyp ------------
    // -------- HatAnsprechpartner --------
    @FXML
    private ComboBox hatAnsprechpartner_fahrzeug;
    @FXML
    private ComboBox hatAnsprechpartner_farbe;

    @FXML
    private Button hatAnsprechpartner_speichern;
    @FXML
    private Button hatAnsprechpartner_löschen;
    @FXML
    private Button hatAnsprechpartner_anlegen;
    @FXML
    private TableView hatAnsprechpartner_anzeige;
    // -------- HatAnsprechpartner --------
    // ------------ HatFarben -------------
    @FXML
    private ComboBox hatFarben_fahrzeug;
    @FXML
    private ComboBox hatFarben_farbe;

    @FXML
    private Button hatFarben_speichern;
    @FXML
    private Button hatFarben_löschen;
    @FXML
    private Button hatFarben_anlegen;
    @FXML
    private TableView hatFarben_anzeige;
    // ------------ HatFarben -------------
    // ------------ Hersteller -------------
    @FXML
    private ComboBox<Integer> hersteller_id;
    @FXML
    private TextField hersteller_name;

    @FXML
    private Button hersteller_speichern;
    @FXML
    private Button hersteller_löschen;
    @FXML
    private Button hersteller_anlegen;
    @FXML
    private TableView hersteller_anzeige;
    // ------------ Hersteller -------------
    // --------------- Kunde ---------------
    @FXML
    private ComboBox<Integer> kunde_id;
    @FXML
    private TextField kunde_vorname;
    @FXML
    private TextField kunde_nachname;

    @FXML
    private ComboBox<Integer> kunde_anrede;
    @FXML
    private ComboBox<Integer> kunde_adresse;
    @FXML
    private ComboBox<Integer> kunde_ansprechpartner;

    @FXML
    private Button kunde_speichern;
    @FXML
    private Button kunde_löschen;
    @FXML
    private Button kunde_anlegen;
    @FXML
    private TableView kunde_anzeige;
    // --------------- Kunde ---------------
    // ------------ Mitarbeiter ------------
    @FXML
    private ComboBox<Integer> mitarbeiter_id;
    @FXML
    private TextField mitarbeiter_vorname;
    @FXML
    private TextField mitarbeiter_nachname;
    @FXML
    private TextField mitarbeiter_lohn;
    @FXML
    private TextField mitarbeiter_beschaeftigungsstart;
    @FXML
    private CheckBox mitarbeiter_verfuegbarkeit;

    @FXML
    private ComboBox<Integer> mitarbeiter_anrede;
    @FXML
    private ComboBox<Integer> mitarbeiter_adresse;

    @FXML
    private Button mitarbeiter_speichern;
    @FXML
    private Button mitarbeiter_anlegen;
    @FXML
    private Button mitarbeiter_löschen;

    @FXML
    private TableView mitarbeiter_anzeige;
    // ------------ Mitarbeiter ------------

    @FXML
    TabPane tabPane;
    @FXML
    Tab hatAnsprechpartner_reiter;
    @FXML
    Tab hatFarben_reiter;


    private Connection connection;
    private UserPermissions permissions = UserPermissions.READ; // read ist standard

    @FXML
    private void initialize() {

        hatFarben_reiter.setDisable(true);
        hatAnsprechpartner_reiter.setDisable(true);

        tabPane.getTabs().remove(hatFarben_reiter);
        tabPane.getTabs().remove(hatAnsprechpartner_reiter);


        // Speicher Button Adresse
        adresse_speichern.setOnMouseClicked(event -> {
            Integer selected = adresse_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Adresse adr = Adresse.getAdresseList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            adr.setStrasse(adresse_straße.getText());
            adr.setStadt(adresse_stadt.getText());
            adr.setPostleitzahl(adresse_postleitzahl.getText());
            adr.setHausnummer(adresse_hausnummer.getText());
            try {
                updateAdressen(adr);
                Adresse.clearList();
                loadAdressen(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Erstellen Button Adresse
        adresse_anlegen.setOnMouseClicked(event -> {
            Adresse adr = new Adresse(
                    -1,
                    adresse_straße.getText(),
                    adresse_stadt.getText(),
                    adresse_postleitzahl.getText(),
                    adresse_hausnummer.getText()
            );
            try {
                createAdresse(adr);
                Adresse.clearList();
                loadAdressen(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Löschen Button Adresse
        adresse_löschen.setOnMouseClicked(event -> {
            Integer selected = adresse_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Adresse adr = Adresse.getAdresseList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            try {
                deleteAdresse(adr.getId());
                Adresse.clearList();
                loadAdressen(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Speichern Button Kunde
        kunde_speichern.setOnMouseClicked(event -> {
            Integer selected = kunde_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Kunde kunde = Kunde.getKundeList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);

            if (kunde != null) {
                kunde.setVorname(kunde_vorname.getText());
                kunde.setNachname(kunde_nachname.getText());
                kunde.setAdresse_id(Integer.parseInt(kunde_adresse.getSelectionModel().getSelectedItem().toString()));
                kunde.setAnsprechpartner_id(kunde_ansprechpartner.getSelectionModel().getSelectedItem().intValue());
                kunde.setAnrede_id(kunde_anrede.getSelectionModel().getSelectedItem());

                try {
                    updateKunde(kunde);
                    Kunde.clearList();
                    loadKunden(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Erstellen Button Kunde
        kunde_anlegen.setOnMouseClicked(event -> {
            Kunde kunde = new Kunde(
                    -1,
                    kunde_vorname.getText(),
                    kunde_nachname.getText(),
                    Adresse.getAdresseList().stream().filter(
                            addr -> addr.getId() == kunde_adresse.getSelectionModel().getSelectedItem().intValue()
                    ).collect(Collectors.toList()).get(0).getId(),
                    kunde_ansprechpartner.getSelectionModel().getSelectedItem(),
                    kunde_anrede.getSelectionModel().getSelectedItem()
            );

            try {
                createKunde(kunde);
                Kunde.clearList();
                loadKunden(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Löschen Button Kunde
        kunde_löschen.setOnMouseClicked(event -> {
            Integer selected = kunde_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Kunde kunde = Kunde.getKundeList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);

            if (kunde != null) {
                try {
                    deleteKunde(kunde.getId());
                    Kunde.clearList();
                    loadKunden(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Speichern Button Hersteller
        hersteller_speichern.setOnMouseClicked(event -> {
            Integer selected = hersteller_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Hersteller hersteller = Hersteller.getHerstellerList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);

            if (hersteller != null) {
                hersteller.setName(hersteller_name.getText());

                try {
                    updateHersteller(hersteller);
                    Hersteller.clearList();
                    loadHersteller(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Erstellen Button Hersteller
        hersteller_anlegen.setOnMouseClicked(event -> {
            Hersteller hersteller = new Hersteller(
                    -1,
                    hersteller_name.getText()
            );

            try {
                createHersteller(hersteller);
                Hersteller.clearList();
                loadHersteller(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Löschen Button Hersteller
        hersteller_löschen.setOnMouseClicked(event -> {
            Integer selected = hersteller_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Hersteller hersteller = Hersteller.getHerstellerList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);

            if (hersteller != null) {
                try {
                    deleteHersteller(hersteller.getId());
                    Hersteller.clearList();
                    loadHersteller(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Speichern Button Fahrzeug
        // FIXME: kaufkunde und mietkunde wird nicht übernommen
        fahrzeug_speichern.setOnMouseClicked(event -> {
            Integer selected = fahrzeug_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeug fahrzeug = Fahrzeug.getFahrzeugList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);

            if (fahrzeug != null) {
                fahrzeug.setModell_id(fahrzeug_modell.getSelectionModel().getSelectedItem());
                fahrzeug.setKaufpreis(Float.parseFloat(fahrzeug_kaufpreis.getText()));
                fahrzeug.setMietpreis(Float.parseFloat(fahrzeug_mietpreis.getText()));
                fahrzeug.setIstVermietet(fahrzeug_istVermietet.isSelected());
                fahrzeug.setMietKunde_id(fahrzeug_mietkunde.getSelectionModel().getSelectedItem() != null ? fahrzeug_mietkunde.getSelectionModel().getSelectedItem() : -1);
                fahrzeug.setIstVerkauft(fahrzeug_istVerkauft.isSelected());
                fahrzeug.setKaufKunde_id(fahrzeug_kaufkunde.getSelectionModel().getSelectedItem() != null ? fahrzeug_kaufkunde.getSelectionModel().getSelectedItem() : -1);
                fahrzeug.setLetzterTuev(Date.valueOf(fahrzeug_letzterTuev.getText()));
                fahrzeug.setAnzVorherigeBesitzer(Integer.parseInt(fahrzeug_anzVorherigeBesitzer.getText()));
                fahrzeug.setKilometerstand(Integer.parseInt(fahrzeug_kilometerstand.getText()));

                try {
                    updateFahrzeuge(fahrzeug);
                    Fahrzeug.clearList();
                    loadFahrzeuge(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Erstellen Button Fahrzeug
        fahrzeug_anlegen.setOnMouseClicked(event -> {
            Fahrzeug fahrzeug = new Fahrzeug(
                    -1,
                    fahrzeug_modell.getSelectionModel().getSelectedItem(),
                    Float.parseFloat(fahrzeug_kaufpreis.getText()),
                    Float.parseFloat(fahrzeug_mietpreis.getText()),
                    fahrzeug_istVermietet.isSelected(),
                    fahrzeug_mietkunde.getSelectionModel().getSelectedItem(),
                    fahrzeug_istVerkauft.isSelected(),
                    fahrzeug_kaufkunde.getSelectionModel().getSelectedItem(),
                    Date.valueOf(fahrzeug_letzterTuev.getText()),
                    Integer.parseInt(fahrzeug_anzVorherigeBesitzer.getText()),
                    Integer.parseInt(fahrzeug_kilometerstand.getText())
            );

            try {
                createFahrzeug(fahrzeug);
                Fahrzeug.clearList();
                loadFahrzeuge(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

// Löschen Button Fahrzeug
        fahrzeug_löschen.setOnMouseClicked(event -> {
            Integer selected = fahrzeug_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeug fahrzeug = Fahrzeug.getFahrzeugList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);

            if (fahrzeug != null) {
                try {
                    deleteFahrzeug(fahrzeug.getId());
                    Fahrzeug.clearList();
                    loadFahrzeuge(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Speichern Button Mitarbeiter
        mitarbeiter_speichern.setOnMouseClicked(event -> {
            Integer selected = mitarbeiter_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Mitarbeiter mitarbeiter = Mitarbeiter.getMitarbeiterList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (mitarbeiter != null) {
                mitarbeiter.setVorname(mitarbeiter_vorname.getText());
                mitarbeiter.setNachname(mitarbeiter_nachname.getText());
                mitarbeiter.setAdresse_id(mitarbeiter_adresse.getSelectionModel().getSelectedItem());
                mitarbeiter.setAnrede_id(mitarbeiter_anrede.getSelectionModel().getSelectedItem());
                mitarbeiter.setLohn(Float.parseFloat(mitarbeiter_lohn.getText()));
                mitarbeiter.setBeschaeftigungsstart(Date.valueOf(mitarbeiter_beschaeftigungsstart.getText()));
                mitarbeiter.setVerfuegbar(mitarbeiter_verfuegbarkeit.isSelected());

                try {
                    updateMitarbeiter(mitarbeiter);
                    Mitarbeiter.clearList();
                    loadMitarbeiter(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Erstellen Button Mitarbeiter
        mitarbeiter_anlegen.setOnMouseClicked(event -> {
            Mitarbeiter mitarbeiter = new Mitarbeiter(
                    -1,
                    mitarbeiter_vorname.getText(),
                    mitarbeiter_nachname.getText(),
                    mitarbeiter_adresse.getSelectionModel().getSelectedItem(),
                    mitarbeiter_anrede.getSelectionModel().getSelectedItem(),
                    Float.parseFloat(mitarbeiter_lohn.getText()),
                    Date.valueOf(mitarbeiter_beschaeftigungsstart.getText()),
                    mitarbeiter_verfuegbarkeit.isSelected()
            );
            try {
                createMitarbeiter(mitarbeiter);
                Mitarbeiter.clearList();
                loadMitarbeiter(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

// Löschen Button Mitarbeiter
        mitarbeiter_löschen.setOnMouseClicked(event -> {
            Integer selected = mitarbeiter_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Mitarbeiter mitarbeiter = Mitarbeiter.getMitarbeiterList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (mitarbeiter != null) {
                try {
                    deleteMitarbeiter(mitarbeiter.getId());
                    Mitarbeiter.clearList();
                    loadMitarbeiter(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Speichern Button Anrede
        anrede_speichern.setOnMouseClicked(event -> {
            Integer selected = anrede_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Anrede anrede = Anrede.getAnredeList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (anrede != null) {
                anrede.setAnredewort(anrede_anredewort.getText());

                try {
                    updateAnreden(anrede);
                    Anrede.clearList();
                    loadAnreden(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Erstellen Button Anrede
        anrede_anlegen.setOnMouseClicked(event -> {
            Anrede anrede = new Anrede(
                    -1,
                    anrede_anredewort.getText()
            );
            try {
                createAnrede(anrede);
                Anrede.clearList();
                loadAnreden(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

// Löschen Button Anrede
        anrede_löschen.setOnMouseClicked(event -> {
            Integer selected = anrede_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Anrede anrede = Anrede.getAnredeList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (anrede != null) {
                try {
                    deleteAnrede(anrede.getId());
                    Anrede.clearList();
                    loadAnreden(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        // Speichern Button Fahrzeugfarbe
        fahrzeugfarbe_speichern.setOnMouseClicked(event -> {
            Integer selected = fahrzeugfarbe_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugfarbe fahrzeugfarbe = Fahrzeugfarbe.getFarbeList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (fahrzeugfarbe != null) {
                fahrzeugfarbe.setFarbname(fahrzeugfarbe_farbname.getText());

                try {
                    updateFahrzeugfarben(fahrzeugfarbe);
                    Fahrzeugfarbe.clearList();
                    loadFahrzeugfarben(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Erstellen Button Fahrzeugfarbe
        fahrzeugfarbe_anlegen.setOnMouseClicked(event -> {
            Fahrzeugfarbe fahrzeugfarbe = new Fahrzeugfarbe(
                    -1,
                    fahrzeugfarbe_farbname.getText()
            );
            try {
                createFahrzeugfarbe(fahrzeugfarbe);
                Fahrzeugfarbe.clearList();
                loadFahrzeugfarben(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

// Löschen Button Fahrzeugfarbe
        fahrzeugfarbe_löschen.setOnMouseClicked(event -> {
            Integer selected = fahrzeugfarbe_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugfarbe fahrzeugfarbe = Fahrzeugfarbe.getFarbeList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (fahrzeugfarbe != null) {
                try {
                    deleteFahrzeugfarbe(fahrzeugfarbe.getId());
                    Fahrzeugfarbe.clearList();
                    loadFahrzeugfarben(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Speichern Button Fahrzeugmodell
        fahrzeugmodell_speichern.setOnMouseClicked(event -> {
            Integer selected = fahrzeugmodell_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugmodell fahrzeugmodell = Fahrzeugmodell.getModellList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (fahrzeugmodell != null) {
                fahrzeugmodell.setHersteller_id(fahrzeugmodell_hersteller.getSelectionModel().getSelectedItem());
                fahrzeugmodell.setFahrzeugtyp_id(fahrzeugmodell_fahrzeugtyp.getSelectionModel().getSelectedItem());

                try {
                    updateFahrzeugmodelle(fahrzeugmodell);
                    Fahrzeugmodell.clearList();
                    loadFahrzeugmodelle(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Erstellen Button Fahrzeugmodell
        fahrzeugmodell_anlegen.setOnMouseClicked(event -> {
            Fahrzeugmodell fahrzeugmodell = new Fahrzeugmodell(
                    -1,
                    fahrzeugmodell_hersteller.getSelectionModel().getSelectedItem(),
                    fahrzeugmodell_fahrzeugtyp.getSelectionModel().getSelectedItem()
            );
            try {
                createFahrzeugmodell(fahrzeugmodell);
                Fahrzeugmodell.clearList();
                loadFahrzeugmodelle(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

// Löschen Button Fahrzeugmodell
        fahrzeugmodell_löschen.setOnMouseClicked(event -> {
            Integer selected = fahrzeugmodell_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugmodell fahrzeugmodell = Fahrzeugmodell.getModellList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (fahrzeugmodell != null) {
                try {
                    deleteFahrzeugmodell(fahrzeugmodell.getId());
                    Fahrzeugmodell.clearList();
                    loadFahrzeugmodelle(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        // Speichern Button Fahrzeugtyp
        fahrzeugtyp_speichern.setOnMouseClicked(event -> {
            Integer selected = fahrzeugtyp_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugtyp fahrzeugtyp = Fahrzeugtyp.getTypList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (fahrzeugtyp != null) {
                fahrzeugtyp.setBezeichnung(fahrzeugtyp_bezeichnung.getText());

                try {
                    updateFahrzeugtypen(fahrzeugtyp);
                    Fahrzeugtyp.clearList();
                    loadFahrzeugtypen(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Erstellen Button Fahrzeugtyp
        fahrzeugtyp_anlegen.setOnMouseClicked(event -> {
            Fahrzeugtyp fahrzeugtyp = new Fahrzeugtyp(
                    -1,
                    fahrzeugtyp_bezeichnung.getText()
            );
            try {
                createFahrzeugtyp(fahrzeugtyp);
                Fahrzeugtyp.clearList();
                loadFahrzeugtypen(connection);
                showTableFacade();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

// Löschen Button Fahrzeugtyp
        fahrzeugtyp_löschen.setOnMouseClicked(event -> {
            Integer selected = fahrzeugtyp_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugtyp fahrzeugtyp = Fahrzeugtyp.getTypList().stream()
                    .filter(x -> x.getId() == selected)
                    .findFirst()
                    .orElse(null);
            if (fahrzeugtyp != null) {
                try {
                    deleteFahrzeugtyp(fahrzeugtyp.getId());
                    Fahrzeugtyp.clearList();
                    loadFahrzeugtypen(connection);
                    showTableFacade();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        }

    public void loadConnection() {

        try {

            // Laden der Daten
            loadMitarbeiter(connection);
            loadKunden(connection);
            loadAdressen(connection);
            loadAnreden(connection);
            loadFahrzeuge(connection);
            loadFahrzeugfarben(connection);
            loadFahrzeugmodelle(connection);
            loadFahrzeugtypen(connection);
            loadHatAnsprechpartner(connection);
            loadHatFarbe(connection);
            loadHersteller(connection);

            // Testen der Daten
            Mitarbeiter.printCount();
            Kunde.printCount();
            Adresse.printCount();
            Anrede.printCount();
            Fahrzeug.printCount();
            Fahrzeugfarbe.printCount();
            Fahrzeugmodell.printCount();
            Fahrzeugtyp.printCount();
            HatAnsprechpartner.printCount();
            HatFarben.printCount();
            Hersteller.printCount();

            /*
            // Testen update
            Mitarbeiter m1 = Mitarbeiter.getMitarbeiterList().get(0);
            m1.setVorname("TEST");
            updateMitarbeiter(m1);

            Hersteller h1 = Hersteller.getHerstellerList().get(0);
            h1.setName("TEST");
            updateHersteller(h1);

            Adresse a1 = Adresse.getAdresseList().get(0);
            a1.setStadt("TEST");
            updateAdressen(a1);

            Anrede an1 = Anrede.getAnredeList().get(0);
            an1.setAnredewort("ens");
            updateAnreden(an1);

            Fahrzeug f1 = Fahrzeug.getFahrzeugList().get(0);
            f1.setAnzVorherigeBesitzer(420);
            updateFahrzeuge(f1);

            Fahrzeugfarbe ff1 = Fahrzeugfarbe.getFarbeList().get(0);
            ff1.setFarbname("ANTRAZIT");
            updateFahrzeugfarben(ff1);

            Fahrzeugmodell fm1 = Fahrzeugmodell.getModellList().get(0);
            fm1.setFahrzeugtyp_id(1);
            updateFahrzeugmodelle(fm1);

            Fahrzeugtyp ft1 = Fahrzeugtyp.getTypList().get(0);
            ft1.setBezeichnung("BRUMM-BRUMM");
            updateFahrzeugtypen(ft1);

            HatAnsprechpartner ha1 = HatAnsprechpartner.getHatAnsprechpartnerList().get(0);
            ha1.setMitarbeiter_id(1);
            updateHatAnsprechpartner(ha1);

            HatFarben hf = HatFarben.getHatFarbenList().get(0);
            hf.setFarb_id(1);
            updateHatFarbe(hf);

            Kunde k1 = Kunde.getKundeList().get(0);
            k1.setVorname("DÜNSCH");
            updateKunde(k1);

            // Testen create und Delete
            Kunde kNew = new Kunde(-1, "Vorname", "Nachname", 1, 1,1);
            createKunde(kNew);
            Kunde.clearList();
            loadKunden(connection);
            //deleteKunde(1);

            Mitarbeiter mNew = new Mitarbeiter(-1, "Vorname", "Nachname", 1, 1,100f, new Date(1999,1,1),true );
            createMitarbeiter(mNew);
            Mitarbeiter.clearList();
            loadMitarbeiter(connection);
            //deleteMitarbeiter(1);

            Adresse addrNew = new Adresse(-1, "strasse", "stadt", "0190", "45a");
            createAdresse(addrNew);
            Adresse.clearList();
            loadAdressen(connection);
            //deleteAdresse(1);

            Anrede anrNew = new Anrede(-1, "wort");
            createAnrede(anrNew);
            Anrede.clearList();
            loadAnreden(connection);
            //deleteAnrede(1);

            Fahrzeug fNew = new Fahrzeug(-1, 1, 100f,100f ,false,-1,false,-1,new Date(1999,1,1),1,1);
            createFahrzeug(fNew);
            Fahrzeug.clearList();
            loadFahrzeuge(connection);
            //deleteFahrzeug(1);

            Fahrzeugfarbe ffNew = new Fahrzeugfarbe(-1, "farbe");
            createFahrzeugfarbe(ffNew);
            Fahrzeugfarbe.clearList();
            loadFahrzeugfarben(connection);
            //deleteFahrzeugfarbe(1);

            Fahrzeugmodell fmNew = new Fahrzeugmodell(-1, 1, 1);
            createFahrzeugmodell(fmNew);
            Fahrzeugmodell.clearList();
            loadFahrzeugmodelle(connection);
            //deleteFahrzeugmodell(1);

            Fahrzeugtyp ftNew = new Fahrzeugtyp(-1, "bezeich");
            createFahrzeugtyp(ftNew);
            Fahrzeugtyp.clearList();
            loadFahrzeugtypen(connection);
            //deleteFahrzeugtyp(1);

            HatAnsprechpartner haNew = new HatAnsprechpartner(1, 1);
            createHatAnsprechpartner(haNew);
            HatAnsprechpartner.clearList();
            loadHatAnsprechpartner(connection);
            //deleteHatAnsprechpartner(1,1);

            HatFarben hfNew = new HatFarben(1,1);
            createHatFarbe(hfNew);
            HatFarben.clearList();
            loadHatFarbe(connection);
            //deleteHatFarbe(1,1);

            Hersteller hNew = new Hersteller(-1,"Name");
            createHersteller(hNew);
            Hersteller.clearList();
            loadHersteller(connection);
            //deleteHersteller(1);
        */

            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showTableFacade();
        setAcces(this.permissions);
        System.out.println(permissions.toString());
    }

    private void showTableFacade(){
        showAdresse();
        showAnrede();
        showFahrzeug();
        showFahrzeugfarbe();
        showFahrzeugmodell();
        showFahrzeugtyp();          // DEBUG
        showAnsprechpartner();
        showHatFarben();
        showHersteller();
        showKunde();
        showMitarbeiter();
    }

    private void loadMitarbeiter(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "select * from  Mitarbeiter;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Mitarbeiter newMitarbeiter = new Mitarbeiter(
                    rs.getInt("ID"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getInt("Adresse_ID"),
                    rs.getInt("Anrede_ID"),
                    rs.getFloat("Lohn"),
                    rs.getDate("BeschäftigungsStart"),
                    rs.getBoolean("Verfügbar")
            );
            Mitarbeiter.addMitarbeiter(newMitarbeiter);
        }
    }

    private void loadKunden(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Kunde;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Kunde newKunde = new Kunde(
                    rs.getInt("id"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getInt("adresse_id"),
                    rs.getInt("ansprechpartner_id"),
                    rs.getInt("anrede_id")
            );
            Kunde.addKunde(newKunde);
        }
    }

    private void loadAdressen(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Adresse;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Adresse newAdresse = new Adresse(
                    rs.getInt("id"),
                    rs.getString("straße"),
                    rs.getString("stadt"),
                    rs.getString("postleitzahl"),
                    rs.getString("hausnummer")
            );
            Adresse.addAdresse(newAdresse);
        }
    }

    private void loadAnreden(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Anrede;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Anrede newAnrede = new Anrede(
                    rs.getInt("id"),
                    rs.getString("anredewort")
            );
            Anrede.addAnrede(newAnrede);
        }
    }
    private void loadFahrzeuge(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeug;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int mietKundeId = rs.getInt("mietKunde_id");
            int kaufKundeId = rs.getInt("kaufKunde_id");

            // Check if mietKunde_id is null and set to -1
            if (rs.wasNull()) {
                mietKundeId = -1;
            }

            // Check if kaufKunde_id is null and set to -1
            if (rs.wasNull()) {
                kaufKundeId = -1;
            }

            Fahrzeug newFahrzeug = new Fahrzeug(
                    rs.getInt("id"),
                    rs.getInt("modell_id"),
                    rs.getFloat("kaufpreis"),
                    rs.getFloat("mietpreis"),
                    rs.getBoolean("istVermietet"),
                    mietKundeId,
                    rs.getBoolean("istVerkauft"),
                    kaufKundeId,
                    rs.getDate("letzterTÜV"),
                    rs.getInt("anzVorherigeBesitzer"),
                    rs.getInt("kilometerstand")
            );
            Fahrzeug.addFahrzeug(newFahrzeug);
        }
    }

    private void loadFahrzeugfarben(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeugfarbe;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeugfarbe newFahrzeugfarbe = new Fahrzeugfarbe(
                    rs.getInt("id"),
                    rs.getString("farbname")
            );
            Fahrzeugfarbe.addFarbe(newFahrzeugfarbe);
        }
    }

    private void loadFahrzeugmodelle(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeugmodell;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeugmodell newFahrzeugmodell = new Fahrzeugmodell(
                    rs.getInt("id"),
                    rs.getInt("hersteller_id"),
                    rs.getInt("fahrzeugtyp_id")
            );
            Fahrzeugmodell.addModell(newFahrzeugmodell);
        }
    }
    private void loadFahrzeugtypen(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeugtyp;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeugtyp newFahrzeugtyp = new Fahrzeugtyp(
                    rs.getInt("id"),
                    rs.getString("bezeichnung")
            );
            Fahrzeugtyp.addFahrzeugtyp(newFahrzeugtyp);
        }
    }
    private void loadHatAnsprechpartner(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM HatAnsprechpartner;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            HatAnsprechpartner newHatAnsprechpartner = new HatAnsprechpartner(
                    rs.getInt("kunde_id"),
                    rs.getInt("mitarbeiter_id")
            );
            HatAnsprechpartner.addHatAnsprechpartner(newHatAnsprechpartner);
        }
    }

    private void loadHatFarbe(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM HatFarben;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            HatFarben newHatFarben = new HatFarben(
                    rs.getInt("fahrzeug_id"),
                    rs.getInt("farb_id")
            );
            HatFarben.addHatFarbe(newHatFarben);
        }
    }

    private void loadHersteller(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Hersteller;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Hersteller newHersteller = new Hersteller(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            Hersteller.addHersteller(newHersteller);
        }
    }

    private void updateHersteller(Hersteller hersteller) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Hersteller SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hersteller.getName());
            preparedStatement.setInt(2, hersteller.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hersteller mit ID " + hersteller.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Hersteller mit ID " + hersteller.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Mitarbeiter SET Vorname = ?, Nachname = ?, Adresse_ID = ?, Anrede_ID = ?, Lohn = ?, BeschäftigungsStart = ?, Verfügbar = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mitarbeiter.getVorname());
            preparedStatement.setString(2, mitarbeiter.getNachname());
            preparedStatement.setInt(3, mitarbeiter.getAdresse_id());
            preparedStatement.setInt(4, mitarbeiter.getAnrede_id());
            preparedStatement.setFloat(5, mitarbeiter.getLohn());
            preparedStatement.setDate(6, mitarbeiter.getBeschaeftigungsstart());
            preparedStatement.setBoolean(7, mitarbeiter.isVerfuegbar());
            preparedStatement.setInt(8, mitarbeiter.getId());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mitarbeiter mit ID " + mitarbeiter.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Mitarbeiter mit ID " + mitarbeiter.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateAdressen(Adresse adresse) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Adresse SET straße = ?, stadt = ?, postleitzahl = ?, hausnummer = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adresse.getStrasse());
            preparedStatement.setString(2, adresse.getStadt());
            preparedStatement.setString(3, adresse.getPostleitzahl());
            preparedStatement.setString(4, adresse.getHausnummer());
            preparedStatement.setInt(5, adresse.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adresse mit ID " + adresse.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Adresse mit ID " + adresse.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateAnreden(Anrede anrede) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Anrede SET anredewort = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, anrede.getAnredewort());
            preparedStatement.setInt(2, anrede.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Anrede mit ID " + anrede.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Anrede mit ID " + anrede.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    private void updateFahrzeuge(Fahrzeug fahrzeug) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Fahrzeug SET modell_id = ?, kaufpreis = ?, mietpreis = ?, istVermietet = ?, mietKunde_id = ?, istVerkauft = ?, kaufKunde_id = ?, letzterTÜV = ?, anzVorherigeBesitzer = ?, kilometerstand = ? WHERE id = ?";


            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeug.getModell_id());
                preparedStatement.setFloat(2, fahrzeug.getKaufpreis());
                preparedStatement.setFloat(3, fahrzeug.getMietpreis());
                preparedStatement.setBoolean(4, fahrzeug.isIstVermietet());
                if (fahrzeug.getMietKunde_id() != -1)
                    preparedStatement.setInt(5, fahrzeug.getMietKunde_id());
                else
                    preparedStatement.setNull(5, 1);
                preparedStatement.setBoolean(6, fahrzeug.isIstVerkauft());
                if (fahrzeug.getKaufKunde_id() != -1)
                    preparedStatement.setInt(7, fahrzeug.getKaufKunde_id());
                else
                    preparedStatement.setNull(7, 1);
                preparedStatement.setDate(8, fahrzeug.getLetzterTuev());
                preparedStatement.setInt(9, fahrzeug.getAnzVorherigeBesitzer());
                preparedStatement.setInt(10, fahrzeug.getKilometerstand());
                preparedStatement.setInt(11, fahrzeug.getId());


                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Fahrzeug mit ID " + fahrzeug.getId() + " erfolgreich aktualisiert.");
                } else {
                    System.out.println("Fahrzeug mit ID " + fahrzeug.getId() + " konnte nicht gefunden werden.");
                }



        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateFahrzeugfarben(Fahrzeugfarbe fahrzeugfarbe) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Fahrzeugfarbe SET farbname = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fahrzeugfarbe.getFarbname());
            preparedStatement.setInt(2, fahrzeugfarbe.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugfarbe mit ID " + fahrzeugfarbe.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Fahrzeugfarbe mit ID " + fahrzeugfarbe.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateFahrzeugmodelle(Fahrzeugmodell fahrzeugmodell) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Fahrzeugmodell SET hersteller_id = ?, fahrzeugtyp_id = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugmodell.getHersteller_id());
            preparedStatement.setInt(2, fahrzeugmodell.getFahrzeugtyp_id());
            preparedStatement.setInt(3, fahrzeugmodell.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugmodell with ID " + fahrzeugmodell.getId() + " successfully updated.");
            } else {
                System.out.println("Fahrzeugmodell with ID " + fahrzeugmodell.getId() + " could not be found.");
            }
        } finally {
            // Release resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateFahrzeugtypen(Fahrzeugtyp fahrzeugtyp) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Fahrzeugtyp SET bezeichnung = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fahrzeugtyp.getBezeichnung());
            preparedStatement.setInt(2, fahrzeugtyp.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugtyp mit ID " + fahrzeugtyp.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Fahrzeugtyp mit ID " + fahrzeugtyp.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateHatAnsprechpartner(HatAnsprechpartner hatAnsprechpartner) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE HatAnsprechpartner SET mitarbeiter_id = ? WHERE kunde_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hatAnsprechpartner.getMitarbeiter_id());
            preparedStatement.setInt(2, hatAnsprechpartner.getKunde_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("HatAnsprechpartner für Kunde mit ID " + hatAnsprechpartner.getKunde_id() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("HatAnsprechpartner für Kunde mit ID " + hatAnsprechpartner.getKunde_id() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateHatFarbe(HatFarben hatFarben) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE HatFarben SET farb_id = ? WHERE fahrzeug_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hatFarben.getFarb_id());
            preparedStatement.setInt(2, hatFarben.getFahrzeug_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("HatFarben für Fahrzeug mit ID " + hatFarben.getFahrzeug_id() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("HatFarben für Fahrzeug mit ID " + hatFarben.getFahrzeug_id() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    private void updateKunde(Kunde kunde) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Kunde SET Vorname = ?, Nachname = ?, adresse_id = ?, ansprechpartner_id = ?, anrede_id = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, kunde.getVorname());
            preparedStatement.setString(2, kunde.getNachname());
            preparedStatement.setInt(3, kunde.getAdresse_id());
            preparedStatement.setInt(4, kunde.getAnsprechpartner_id());
            preparedStatement.setInt(5, kunde.getAnrede_id());
            preparedStatement.setInt(6, kunde.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kunde mit ID " + kunde.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Kunde mit ID " + kunde.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void deleteKunde(int kundeId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM Kunde WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kundeId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kunde mit ID " + kundeId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Kunde mit ID " + kundeId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    /**
     *
     *  CREATE METHODEN IGNORIEREN DIE ID
     *  diese kann einfach als -1 übergeben werden, Kungen Objekte müssen eine ID haben, slebst wenn diese noch nicht in der DB sing
     * @param kunde
     * @throws SQLException
     */
    private void createKunde(Kunde kunde) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO Kunde (Vorname, Nachname, adresse_id, ansprechpartner_id, anrede_id) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, kunde.getVorname());
            preparedStatement.setString(2, kunde.getNachname());
            preparedStatement.setInt(3, kunde.getAdresse_id());
            preparedStatement.setInt(4, kunde.getAnsprechpartner_id());
            preparedStatement.setInt(5, kunde.getAnrede_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neuer Kunde erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des neuen Kunden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    private void createMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Mitarbeiter (Vorname, Nachname, Adresse_ID, Anrede_ID, Lohn, BeschäftigungsStart, Verfügbar) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mitarbeiter.getVorname());
            preparedStatement.setString(2, mitarbeiter.getNachname());
            preparedStatement.setInt(3, mitarbeiter.getAdresse_id());
            preparedStatement.setInt(4, mitarbeiter.getAnrede_id());
            preparedStatement.setFloat(5, mitarbeiter.getLohn());
            preparedStatement.setDate(6, mitarbeiter.getBeschaeftigungsstart());
            preparedStatement.setBoolean(7, mitarbeiter.isVerfuegbar());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neuer Mitarbeiter erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des neuen Mitarbeiters.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    private void deleteMitarbeiter(int mitarbeiterId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM Mitarbeiter WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, mitarbeiterId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mitarbeiter mit ID " + mitarbeiterId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Mitarbeiter mit ID " + mitarbeiterId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createAdresse(Adresse adresse) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO Adresse (straße, stadt, postleitzahl, hausnummer) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adresse.getStrasse());
            preparedStatement.setString(2, adresse.getStadt());
            preparedStatement.setString(3, adresse.getPostleitzahl());
            preparedStatement.setString(4, adresse.getHausnummer());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neue Adresse erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen der neuen Adresse.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void deleteAdresse(int adresseId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM Adresse WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, adresseId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adresse mit ID " + adresseId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Adresse mit ID " + adresseId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createAnrede(Anrede anrede) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO Anrede (anredewort) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, anrede.getAnredewort());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neue Anrede erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen der neuen Anrede.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    private void deleteAnrede(int anredeId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM Anrede WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, anredeId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Anrede mit ID " + anredeId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Anrede mit ID " + anredeId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createFahrzeug(Fahrzeug fahrzeug) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Fahrzeug (modell_id, kaufpreis, mietpreis, istVermietet, mietKunde_id, istVerkauft, kaufKunde_id, letzterTÜV, anzVorherigeBesitzer, kilometerstand) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeug.getModell_id());
            preparedStatement.setFloat(2, fahrzeug.getKaufpreis());
            preparedStatement.setFloat(3, fahrzeug.getMietpreis());
            preparedStatement.setBoolean(4, fahrzeug.isIstVermietet());
            if (fahrzeug.getMietKunde_id() != -1)
                preparedStatement.setInt(5, fahrzeug.getMietKunde_id());
            else preparedStatement.setNull(5,1);
            preparedStatement.setBoolean(6, fahrzeug.isIstVerkauft());
            if (fahrzeug.getKaufKunde_id() != -1)
                preparedStatement.setInt(7, fahrzeug.getKaufKunde_id());
            else preparedStatement.setNull(7,1);
            preparedStatement.setDate(8, new java.sql.Date(fahrzeug.getLetzterTuev().getTime()));
            preparedStatement.setInt(9, fahrzeug.getAnzVorherigeBesitzer());
            preparedStatement.setInt(10, fahrzeug.getKilometerstand());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neues Fahrzeug erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des neuen Fahrzeugs.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void deleteFahrzeug(int fahrzeugId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "DELETE FROM Fahrzeug WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeug mit ID " + fahrzeugId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Fahrzeug mit ID " + fahrzeugId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createFahrzeugfarbe(Fahrzeugfarbe fahrzeugfarbe) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Fahrzeugfarbe (farbname) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fahrzeugfarbe.getFarbname());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neue Fahrzeugfarbe erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen der neuen Fahrzeugfarbe.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void deleteFahrzeugfarbe(int fahrzeugfarbeId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "DELETE FROM Fahrzeugfarbe WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugfarbeId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugfarbe mit ID " + fahrzeugfarbeId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Fahrzeugfarbe mit ID " + fahrzeugfarbeId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    private void createFahrzeugmodell(Fahrzeugmodell fahrzeugmodell) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO Fahrzeugmodell (hersteller_id, fahrzeugtyp_id) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugmodell.getHersteller_id());
            preparedStatement.setInt(2, fahrzeugmodell.getFahrzeugtyp_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neues Fahrzeugmodell erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des neuen Fahrzeugmodells.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void deleteFahrzeugmodell(int fahrzeugmodellId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM Fahrzeugmodell WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugmodellId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugmodell mit ID " + fahrzeugmodellId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Fahrzeugmodell mit ID " + fahrzeugmodellId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createFahrzeugtyp(Fahrzeugtyp fahrzeugtyp) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO Fahrzeugtyp (bezeichnung) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fahrzeugtyp.getBezeichnung());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neuer Fahrzeugtyp erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des neuen Fahrzeugtyps.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    private void deleteFahrzeugtyp(int fahrzeugtypId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "DELETE FROM Fahrzeugtyp WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugtypId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugtyp mit ID " + fahrzeugtypId + " erfolgreich gelöscht.");
            } else {
                System.out.println("Fahrzeugtyp mit ID " + fahrzeugtypId + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createHatAnsprechpartner(HatAnsprechpartner hatAnsprechpartner) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO HatAnsprechpartner (kunde_id, mitarbeiter_id) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hatAnsprechpartner.getKunde_id());
            preparedStatement.setInt(2, hatAnsprechpartner.getMitarbeiter_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neue Beziehung HatAnsprechpartner erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen der neuen Beziehung HatAnsprechpartner.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

    }
    private void deleteHatAnsprechpartner(int kundeId, int mitarbeiterId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM HatAnsprechpartner WHERE kunde_id = ? AND mitarbeiter_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kundeId);
            preparedStatement.setInt(2, mitarbeiterId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Beziehung HatAnsprechpartner erfolgreich gelöscht.");
            } else {
                System.out.println("Beziehung HatAnsprechpartner konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void createHatFarbe(HatFarben hatFarbe) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "INSERT INTO HatFarben (fahrzeug_id, farb_id) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hatFarbe.getFahrzeug_id());
            preparedStatement.setInt(2, hatFarbe.getFarb_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neue Beziehung HatFarben erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen der neuen Beziehung HatFarben.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void deleteHatFarbe(int fahrzeugId, int farbId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM HatFarben WHERE fahrzeug_id = ? AND farb_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugId);
            preparedStatement.setInt(2, farbId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Beziehung HatFarben erfolgreich gelöscht.");
            } else {
                System.out.println("Beziehung HatFarben konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    private void createHersteller(Hersteller hersteller) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Hersteller (name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hersteller.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Neuer Hersteller erfolgreich erstellt.");
            } else {
                System.out.println("Fehler beim Erstellen des neuen Herstellers.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    private void deleteHersteller(int herstellerId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "DELETE FROM Hersteller WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, herstellerId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hersteller erfolgreich gelöscht.");
            } else {
                System.out.println("Hersteller konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }







    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }


    private void showAdresse() {
        TableView<Adresse> tableView = adresse_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Adresse, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Adresse, String> strasseColumn = new TableColumn<>("Straße");
        TableColumn<Adresse, String> stadtColumn = new TableColumn<>("Stadt");
        TableColumn<Adresse, String> postleitzahlColumn = new TableColumn<>("Postleitzahl");
        TableColumn<Adresse, String> hausnummerColumn = new TableColumn<>("Hausnummer");

        tableView.getColumns().addAll(idColumn, strasseColumn, stadtColumn, postleitzahlColumn, hausnummerColumn);

        ArrayList<Adresse> adresseList = Adresse.getAdresseList();

        for (Adresse adresse : adresseList) {
            TableRow<Adresse> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            strasseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStrasse()));
            stadtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStadt()));
            postleitzahlColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostleitzahl()));
            hausnummerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHausnummer()));

            tableView.getItems().add(adresse);
        }

        // Selecter
        adresse_id.getItems().clear();
        for(Adresse adr: Adresse.getAdresseList()) {
            adresse_id.getItems().add(adr.getId());
        }
        adresse_id.setOnAction(e -> {
            Integer selected = adresse_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Adresse adr = Adresse.getAdresseList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            adresse_straße.setText(adr.getStrasse());
            adresse_stadt.setText(adr.getStadt());
            adresse_postleitzahl.setText(adr.getPostleitzahl());
            adresse_hausnummer.setText(adr.getHausnummer());
        });
    }

    private void showAnrede() {
        TableView<Anrede> tableView = anrede_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Anrede, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Anrede, String> anredewortColumn = new TableColumn<>("Anredewort");

        tableView.getColumns().addAll(idColumn, anredewortColumn);

        ArrayList<Anrede> anredeList = Anrede.getAnredeList();

        for (Anrede anrede : anredeList) {
            TableRow<Anrede> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            anredewortColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAnredewort()));

            tableView.getItems().add(anrede);
        }

        // Selecter
        anrede_id.getItems().clear();
        for(Anrede anred: Anrede.getAnredeList()) {
            anrede_id.getItems().add(anred.getId());
        }
        anrede_id.setOnAction(e -> {
            Integer selected = anrede_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Anrede anred = Anrede.getAnredeList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            anrede_anredewort.setText(anred.getAnredewort());
        });
    }

    private void showFahrzeug() {
        TableView<Fahrzeug> tableView = fahrzeug_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Fahrzeug, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Fahrzeug, String> modellColumn = new TableColumn<>("Modell");
        TableColumn<Fahrzeug, Double> kaufpreisColumn = new TableColumn<>("Kaufpreis");
        TableColumn<Fahrzeug, Double> mietpreisColumn = new TableColumn<>("Mietpreis");
        TableColumn<Fahrzeug, Boolean> istVermietetColumn = new TableColumn<>("Ist vermietet");
        TableColumn<Fahrzeug, String> mietKundeColumn = new TableColumn<>("Mietkunde");
        TableColumn<Fahrzeug, Boolean> istVerkauftColumn = new TableColumn<>("Ist verkauft");
        TableColumn<Fahrzeug, String> kaufKundeColumn = new TableColumn<>("Kaufkunde");
        TableColumn<Fahrzeug, String> letzterTuevColumn = new TableColumn<>("Letzter TÜV");
        TableColumn<Fahrzeug, Integer> anzVorherigeBesitzerColumn = new TableColumn<>("Anzahl vorherige Besitzer");
        TableColumn<Fahrzeug, Integer> kilometerstandColumn = new TableColumn<>("Kilometerstand");

        tableView.getColumns().addAll(idColumn, modellColumn, kaufpreisColumn, mietpreisColumn, istVermietetColumn, mietKundeColumn, istVerkauftColumn, kaufKundeColumn, letzterTuevColumn, anzVorherigeBesitzerColumn, kilometerstandColumn);

        ArrayList<Fahrzeug> fahrzeugList = Fahrzeug.getFahrzeugList();

        for (Fahrzeug fahrzeug : fahrzeugList) {
            TableRow<Fahrzeug> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            modellColumn.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getModell_id()));
            kaufpreisColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getKaufpreis()).asObject());
            mietpreisColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMietpreis()).asObject());
            istVermietetColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isIstVermietet()).asObject());

            mietKundeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getMietKundeNameString()));
            istVerkauftColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isIstVerkauft()).asObject());

            kaufKundeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getKaufKundeNameString()));
            letzterTuevColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLetzterTuev().toString()));
            anzVorherigeBesitzerColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnzVorherigeBesitzer()).asObject());
            kilometerstandColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKilometerstand()).asObject());

            tableView.getItems().add(fahrzeug);
        }

        fahrzeug_id.getItems().clear();
        for(Fahrzeug fahrz: Fahrzeug.getFahrzeugList()) {
            fahrzeug_id.getItems().add(fahrz.getId());
        }
        fahrzeug_id.setOnAction(event -> {
            Integer selected = fahrzeug_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeug fahrz = Fahrzeug.getFahrzeugList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            // Set combo box modell
            fahrzeug_modell.getItems().clear();
            Fahrzeugmodell.getModellList().forEach(modell -> fahrzeug_modell.getItems().add(modell.getId()));
            int selectedModellId = fahrz.getModell_id();
            ObservableList<Integer> items = fahrzeug_modell.getItems();
            int index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) == selectedModellId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                fahrzeug_modell.getSelectionModel().select(index);
            }

            fahrzeug_kaufpreis.setText(Float.toString(fahrz.getKaufpreis()));
            fahrzeug_mietpreis.setText(Float.toString(fahrz.getMietpreis()));
            fahrzeug_istVermietet.setSelected(fahrz.isIstVermietet());
            fahrzeug_istVerkauft.setSelected(fahrz.isIstVerkauft());

            fahrzeug_mietkunde.getItems().clear();
            fahrzeug_mietkunde.getItems().add(-1);
            Kunde.getKundeList().forEach(kunde -> fahrzeug_mietkunde.getItems().add(kunde.getId()));
            int selectedId = fahrz.getMietKunde_id();
            ObservableList<Integer> itemsMiet = fahrzeug_mietkunde.getItems();
            index = -1;
            for (int i = 0; i < itemsMiet.size(); i++) {
                if (itemsMiet.get(i) == selectedId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                fahrzeug_mietkunde.getSelectionModel().select(index);
            }

            fahrzeug_kaufkunde.getItems().clear();
            fahrzeug_kaufkunde.getItems().add(-1);
            Kunde.getKundeList().forEach(kunde -> fahrzeug_kaufkunde.getItems().add(kunde.getId()));
            selectedId = fahrz.getKaufKunde_id();
            ObservableList<Integer> itemsKauf = fahrzeug_kaufkunde.getItems();
            index = -1;
            for (int i = 0; i < itemsKauf.size(); i++) {
                if (itemsKauf.get(i) == selectedId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                fahrzeug_kaufkunde.getSelectionModel().select(index);
            }
            fahrzeug_letzterTuev.setText(fahrz.getLetzterTuev().toString());
            fahrzeug_anzVorherigeBesitzer.setText(""+fahrz.getAnzVorherigeBesitzer());
            fahrzeug_kilometerstand.setText(""+fahrz.getKilometerstand());
        });
    }

    private void showFahrzeugfarbe() {
        TableView<Fahrzeugfarbe> tableView = fahrzeugfarbe_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Fahrzeugfarbe, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Fahrzeugfarbe, String> farbnameColumn = new TableColumn<>("Farbname");

        tableView.getColumns().addAll(idColumn, farbnameColumn);

        ArrayList<Fahrzeugfarbe> farbList = Fahrzeugfarbe.getFarbeList();

        for (Fahrzeugfarbe farbe : farbList) {
            TableRow<Fahrzeugfarbe> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            farbnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFarbname()));

            tableView.getItems().add(farbe);
        }

        fahrzeugfarbe_id.getItems().clear();
        for(Fahrzeugfarbe fahrzfarb: Fahrzeugfarbe.getFarbeList()) {
            fahrzeugfarbe_id.getItems().add(fahrzfarb.getId());
        }
        fahrzeugfarbe_id.setOnAction(event -> {
            Integer selected = fahrzeugfarbe_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugfarbe fahrzfarb = Fahrzeugfarbe.getFarbeList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            fahrzeugfarbe_farbname.setText(fahrzfarb.getFarbname());
        });
    }

    private void showFahrzeugmodell() {
        TableView<Fahrzeugmodell> tableView = fahrzeugmodell_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Fahrzeugmodell, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Fahrzeugmodell, Integer> herstellerIdColumn = new TableColumn<>("Hersteller");
        TableColumn<Fahrzeugmodell, Integer> fahrzeugtypIdColumn = new TableColumn<>("Fahrzeugtyp");

        tableView.getColumns().addAll(idColumn, herstellerIdColumn, fahrzeugtypIdColumn);

        ArrayList<Fahrzeugmodell> modellList = Fahrzeugmodell.getModellList();

        for (Fahrzeugmodell modell : modellList) {
            TableRow<Fahrzeugmodell> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            // TODO: hersteller name statt id
            herstellerIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHersteller_id()).asObject());
            // TODO: fahrzeugtyp name statt id
            fahrzeugtypIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFahrzeugtyp_id()).asObject());

            tableView.getItems().add(modell);
        }

        fahrzeugmodell_id.getItems().clear();
        for(Fahrzeugmodell fahrzmod: Fahrzeugmodell.getModellList()) {
            fahrzeugmodell_id.getItems().add(fahrzmod.getId());
        }
        fahrzeugmodell_id.setOnAction(event -> {
            Integer selected = fahrzeugmodell_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugmodell fahrzmod = Fahrzeugmodell.getModellList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            fahrzeugmodell_fahrzeugtyp.getItems().clear();
            Fahrzeugtyp.getTypList().forEach(typ -> fahrzeugmodell_fahrzeugtyp.getItems().add(typ.getId()));
            int selectedFahrzeugtypId = fahrzmod.getFahrzeugtyp_id();
            ObservableList<Integer> items = fahrzeugmodell_fahrzeugtyp.getItems();
            int index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) == selectedFahrzeugtypId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                fahrzeugmodell_fahrzeugtyp.getSelectionModel().select(index);
            }

            fahrzeugmodell_hersteller.getItems().clear();
            Hersteller.getHerstellerList().forEach(hersteller -> fahrzeugmodell_hersteller.getItems().add(hersteller.getId()));
            int selectedHerstellerId = fahrzmod.getHersteller_id();
            ObservableList<Integer> itemsModell = fahrzeugmodell_hersteller.getItems();
            index = -1;
            for (int i = 0; i < itemsModell.size(); i++) {
                if (itemsModell.get(i) == selectedHerstellerId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                fahrzeugmodell_hersteller.getSelectionModel().select(index);
            }

        });
    }

    private void showFahrzeugtyp() {
        TableView<Fahrzeugtyp> tableView = fahrzeugtyp_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Fahrzeugtyp, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Fahrzeugtyp, String> bezeichnungColumn = new TableColumn<>("Bezeichnung");

        tableView.getColumns().addAll(idColumn, bezeichnungColumn);

        ArrayList<Fahrzeugtyp> typList = Fahrzeugtyp.getTypList();

        for (Fahrzeugtyp typ : typList) {
            TableRow<Fahrzeugtyp> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            bezeichnungColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBezeichnung()));

            tableView.getItems().add(typ);
        }

        fahrzeugtyp_id.getItems().clear();
        for(Fahrzeugtyp fahrztyp: Fahrzeugtyp.getTypList()) {
            fahrzeugtyp_id.getItems().add(fahrztyp.getId());
        }
        fahrzeugtyp_id.setOnAction(event -> {
            Integer selected = fahrzeugtyp_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Fahrzeugtyp fahrztyp = Fahrzeugtyp.getTypList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            fahrzeugtyp_bezeichnung.setText(fahrztyp.getBezeichnung());
        });
    }

    private void showAnsprechpartner() {
        TableView<HatAnsprechpartner> tableView = hatAnsprechpartner_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<HatAnsprechpartner, Integer> kundeIdColumn = new TableColumn<>("Kunde ID");
        TableColumn<HatAnsprechpartner, Integer> mitarbeiterIdColumn = new TableColumn<>("Mitarbeiter ID");

        tableView.getColumns().addAll(kundeIdColumn, mitarbeiterIdColumn);

        ArrayList<HatAnsprechpartner> ansprechpartnerList = HatAnsprechpartner.getHatAnsprechpartnerList();

        for (HatAnsprechpartner ansprechpartner : ansprechpartnerList) {
            TableRow<HatAnsprechpartner> row = new TableRow<>();

            kundeIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKunde_id()).asObject());
            mitarbeiterIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMitarbeiter_id()).asObject());

            tableView.getItems().add(ansprechpartner);
        }

        // TODO
    }

    private void showHatFarben() {
        TableView<HatFarben> tableView = hatFarben_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<HatFarben, Integer> fahrzeugIdColumn = new TableColumn<>("Fahrzeug ID");
        TableColumn<HatFarben, Integer> farbIdColumn = new TableColumn<>("Farb ID");

        tableView.getColumns().addAll(fahrzeugIdColumn, farbIdColumn);

        ArrayList<HatFarben> hatFarbenList = HatFarben.getHatFarbenList();

        for (HatFarben hatFarben : hatFarbenList) {
            TableRow<HatFarben> row = new TableRow<>();

            fahrzeugIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFahrzeug_id()).asObject());
            farbIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFarb_id()).asObject());

            tableView.getItems().add(hatFarben);
        }

        // TODO
    }

    private void showHersteller() {
        TableView<Hersteller> tableView = hersteller_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Hersteller, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Hersteller, String> nameColumn = new TableColumn<>("Name");

        tableView.getColumns().addAll(idColumn, nameColumn);

        ArrayList<Hersteller> herstellerList = Hersteller.getHerstellerList();

        for (Hersteller hersteller : herstellerList) {
            TableRow<Hersteller> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

            tableView.getItems().add(hersteller);
        }

        hersteller_id.getItems().clear();
        for(Hersteller herstell: Hersteller.getHerstellerList()) {
            hersteller_id.getItems().add(herstell.getId());
        }
        hersteller_id.setOnAction(event -> {
            Integer selected = hersteller_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Hersteller herstell = Hersteller.getHerstellerList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            hersteller_name.setText(herstell.getName());
        });
    }

    private void showKunde() {
        TableView<Kunde> tableView = kunde_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Kunde, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Kunde, String> vornameColumn = new TableColumn<>("Vorname");
        TableColumn<Kunde, String> nachnameColumn = new TableColumn<>("Nachname");
        TableColumn<Kunde, Integer> adresseIdColumn = new TableColumn<>("Adresse ID");
        TableColumn<Kunde, Integer> ansprechpartnerIdColumn = new TableColumn<>("Ansprechpartner ID");
        TableColumn<Kunde, Integer> anredeIdColumn = new TableColumn<>("Anrede ID");

        tableView.getColumns().addAll(idColumn, vornameColumn, nachnameColumn, adresseIdColumn, ansprechpartnerIdColumn, anredeIdColumn);

        ArrayList<Kunde> kundeList = Kunde.getKundeList();

        for (Kunde kunde : kundeList) {
            TableRow<Kunde> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            vornameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVorname()));
            nachnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNachname()));
            adresseIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAdresse_id()).asObject());
            ansprechpartnerIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnsprechpartner_id()).asObject());
            anredeIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnrede_id()).asObject());

            tableView.getItems().add(kunde);
        }

        kunde_id.getItems().clear();
        for(Kunde kund: Kunde.getKundeList()) {
            kunde_id.getItems().add(kund.getId());
        }
        kunde_id.setOnAction(event -> {
            Integer selected = kunde_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Kunde kund = Kunde.getKundeList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            kunde_anrede.getItems().clear();
            Anrede.getAnredeList().forEach(anred -> kunde_anrede.getItems().add(anred.getId()));
            int selectedAnredeId = kund.getAnrede_id();
            ObservableList<Integer> items = kunde_anrede.getItems();
            int index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) == selectedAnredeId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                kunde_anrede.getSelectionModel().select(index);
            }

            kunde_vorname.setText(kund.getVorname());
            kunde_nachname.setText(kund.getNachname());
            kunde_adresse.getItems().clear();
            Adresse.getAdresseList().forEach(addr -> kunde_adresse.getItems().add(addr.getId()));
            int selectedAdresseId = kund.getAdresse_id();
            ObservableList<Integer> itemsAdress = kunde_adresse.getItems();
            index = -1;
            for (int i = 0; i < itemsAdress.size(); i++) {
                if (itemsAdress.get(i) == selectedAdresseId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                kunde_adresse.getSelectionModel().select(index);
            }

            kunde_ansprechpartner.getItems().clear();
            Mitarbeiter.getMitarbeiterList().forEach(mitarb -> kunde_ansprechpartner.getItems().add(mitarb.getId()));
            int selectedAnsprechpartnerId = kund.getAnsprechpartner_id();
            ObservableList<Integer> itemsAnsp = kunde_ansprechpartner.getItems();
            index = -1;
            for (int i = 0; i < itemsAnsp.size(); i++) {
                if (itemsAnsp.get(i) == selectedAnsprechpartnerId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                kunde_ansprechpartner.getSelectionModel().select(index);
            }

        });
    }

    private void showMitarbeiter() {
        TableView<Mitarbeiter> tableView = mitarbeiter_anzeige;
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Mitarbeiter, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Mitarbeiter, String> vornameColumn = new TableColumn<>("Vorname");
        TableColumn<Mitarbeiter, String> nachnameColumn = new TableColumn<>("Nachname");
        TableColumn<Mitarbeiter, Integer> adresseIdColumn = new TableColumn<>("Adresse ID");
        TableColumn<Mitarbeiter, Double> lohnColumn = new TableColumn<>("Lohn");
        TableColumn<Mitarbeiter, String> beschaeftigungsstartColumn = new TableColumn<>("Beschäftigungsstart");
        TableColumn<Mitarbeiter, Boolean> verfuegbarColumn = new TableColumn<>("Verfügbar");
        TableColumn<Mitarbeiter, Integer> anredeIdColumn = new TableColumn<>("Anrede ID");

        tableView.getColumns().addAll(idColumn, vornameColumn, nachnameColumn, adresseIdColumn, lohnColumn, beschaeftigungsstartColumn, verfuegbarColumn, anredeIdColumn);

        ArrayList<Mitarbeiter> mitarbeiterList = Mitarbeiter.getMitarbeiterList();

        for (Mitarbeiter mitarbeiter : mitarbeiterList) {
            TableRow<Mitarbeiter> row = new TableRow<>();

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            vornameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVorname()));
            nachnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNachname()));
            adresseIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAdresse_id()).asObject());
            lohnColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getLohn()).asObject());
            beschaeftigungsstartColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBeschaeftigungsstart().toString()));
            verfuegbarColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isVerfuegbar()).asObject());
            anredeIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnrede_id()).asObject());

            tableView.getItems().add(mitarbeiter);
        }

        mitarbeiter_id.getItems().clear();
        for(Mitarbeiter mitarb: Mitarbeiter.getMitarbeiterList()) {
            mitarbeiter_id.getItems().add(mitarb.getId());
        }
        mitarbeiter_id.setOnAction(event -> {
            Integer selected = mitarbeiter_id.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            Mitarbeiter mitarb = Mitarbeiter.getMitarbeiterList().stream()
                    .filter(x -> x.getId() == selected)
                    .collect(Collectors.toList())
                    .get(0);
            mitarbeiter_adresse.getItems().clear();
            Adresse.getAdresseList().forEach(addr -> mitarbeiter_adresse.getItems().add(addr.getId()));
            int selectedAdresseId = mitarb.getAdresse_id();
            ObservableList<Integer> items = mitarbeiter_adresse.getItems();
            int index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) == selectedAdresseId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                mitarbeiter_adresse.getSelectionModel().select(index);
            }

            mitarbeiter_anrede.getItems().clear();
            Anrede.getAnredeList().forEach(anred -> mitarbeiter_anrede.getItems().add(anred.getId()));
            int selectedAnredeId = mitarb.getAnrede_id();
            ObservableList<Integer> itemsAnred = mitarbeiter_anrede.getItems();
            index = -1;
            for (int i = 0; i < itemsAnred.size(); i++) {
                if (itemsAnred.get(i) == selectedAnredeId) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                mitarbeiter_anrede.getSelectionModel().select(index);
            }

            mitarbeiter_beschaeftigungsstart.setText(mitarb.getBeschaeftigungsstart().toString());
            mitarbeiter_vorname.setText(mitarb.getVorname());
            mitarbeiter_nachname.setText(mitarb.getNachname());
            mitarbeiter_lohn.setText(Float.toString(mitarb.getLohn()));
            mitarbeiter_verfuegbarkeit.setSelected(mitarb.isVerfuegbar());
        } );
    }
    public void setAcces(UserPermissions permission){

        löschenButtonFassade(false);
        speichernButtonFassade(false);
        anlegenButtonFassade(false);

        switch (permission) {
            case READ:
                löschenButtonFassade(true);
                speichernButtonFassade(true);
                anlegenButtonFassade(true);
                break;
            case READWRITE:
                löschenButtonFassade(true);
                speichernButtonFassade(false);
                anlegenButtonFassade(false);
                break;
            case ADMIN:
                löschenButtonFassade(false);
                speichernButtonFassade(false);
                anlegenButtonFassade(false);
                break;
        }
    }

    private void löschenButtonFassade(Boolean buul){
        adresse_löschen.setDisable(buul);
        anrede_löschen.setDisable(buul);
        fahrzeug_löschen.setDisable(buul);
        fahrzeugfarbe_löschen.setDisable(buul);
        fahrzeugmodell_löschen.setDisable(buul);
        fahrzeugtyp_löschen.setDisable(buul);
        hatAnsprechpartner_löschen.setDisable(buul);
        hatFarben_löschen.setDisable(buul);
        hersteller_löschen.setDisable(buul);
        kunde_löschen.setDisable(buul);
        mitarbeiter_löschen.setDisable(buul);
    }

    private void speichernButtonFassade(Boolean buul){
        adresse_speichern.setDisable(buul);
        anrede_speichern.setDisable(buul);
        fahrzeug_speichern.setDisable(buul);
        fahrzeugfarbe_speichern.setDisable(buul);
        fahrzeugmodell_speichern.setDisable(buul);
        fahrzeugtyp_speichern.setDisable(buul);
        hatAnsprechpartner_speichern.setDisable(buul);
        hatFarben_speichern.setDisable(buul);
        hersteller_speichern.setDisable(buul);
        kunde_speichern.setDisable(buul);
        mitarbeiter_speichern.setDisable(buul);
    }

    private void anlegenButtonFassade(Boolean buul){
        adresse_anlegen.setDisable(buul);
        anrede_anlegen.setDisable(buul);
        fahrzeug_anlegen.setDisable(buul);
        fahrzeugfarbe_anlegen.setDisable(buul);
        fahrzeugmodell_anlegen.setDisable(buul);
        fahrzeugtyp_anlegen.setDisable(buul);
        hatAnsprechpartner_anlegen.setDisable(buul);
        hatFarben_anlegen.setDisable(buul);
        hersteller_anlegen.setDisable(buul);
        kunde_anlegen.setDisable(buul);
        mitarbeiter_anlegen.setDisable(buul);
    }
}
