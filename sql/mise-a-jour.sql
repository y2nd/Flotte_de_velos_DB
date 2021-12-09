--déclencheur après l'ajout d'un emprunt
create or replace trigger AJOUT_EMPRUNT
AFTER insert on EMPRUNTS
for each row
begin
  update VELOS
  set NUMERO_STATION = :new.NUMERO_STATION_DEPART
  where NUMERO_REFERENCE = :new.NUMERO_REFERENCE;
  update ADHERENTS
  set SOLDE = (select SOLDE - PRIX_HORAIRE from VELOS where NUMERO_REFERENCE =:new.NUMERO_REFERENCE)
  where NUMERO_ADHERENT = :new.NUMERO_ADHERENT;
end;
/
show errors trigger AJOUT_EMPRUNT;

--déclencheur après l'ajout d'un emprunt
create or replace trigger UPDATE_EMPRUNT
after update of NUMERO_STATION_ARRIVEE on EMPRUNTS
for each row
begin
  update VELOS
  set NUMERO_STATION = :new.NUMERO_STATION_ARRIVEE
  where NUMERO_REFERENCE = :new.NUMERO_REFERENCE;
end;
/
show errors trigger UPDATE_EMPRUNT;

--déclencheur avant suppression d'un vélo
create or replace trigger DELETE_VELO
before delete on VELOS
for each row
begin
  update EMPRUNTS
  set NUMERO_REFERENCE = null
  where NUMERO_REFERENCE = :old.NUMERO_REFERENCE;
end;
/
show errors trigger DELETE_VELO;

--déclencheur avant suppression d'un adhérent
create or replace trigger DELETE_ADHERENT
before delete on ADHERENTS
for each row
begin
  update EMPRUNTS
  set NUMERO_ADHERENT = null
  where NUMERO_ADHERENT = :old.NUMERO_ADHERENT;
end;
/
show errors trigger DELETE_ADHERENT;

--déclencheur avant suppression d'un fournisseur
create or replace trigger DELETE_FOURNISSEUR
before delete on FOURNISSEURS
for each row
begin
  delete VELOS
  where NUMERO_FOURNISSEUR = :old.NUMERO_FOURNISSEUR;
end;
/
show errors trigger DELETE_FOURNISSEUR;