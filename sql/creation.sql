-- ============================================================
--          CREATION DES TABLES
-- ============================================================
create table FOURNISSEURS(
    NUMERO_FOURNISSEUR number(3) primary key not null,
    NOM_FOURNISSEUR char(20),
    ADRESSE_FOURNISSEUR char(100),
    VILLE_FOURNISSEUR char(20)
);

create table COMMUNES(
    NUMERO_COMMUNE number(4) primary key not null,
    NOM_COMMUNE char(20)
);

create table ADHERENTS(
    NUMERO_ADHERENT number(4) primary key not null,
    NOM_ADHERENT char(20),
    PRENOM_ADHERENT char(20),
    DATE_ADHESION date,
    SOLDE number(6,2) not null
);

create table STATIONS(
    NUMERO_STATION number(4) primary key not null,
    ADRESSE_STATION char(100),
    NB_BORNES number(4),
    NUMERO_COMMUNE number(4) not null,
    foreign key (NUMERO_COMMUNE) references COMMUNES(NUMERO_COMMUNE)
);

create table VELOS(
    NUMERO_REFERENCE number(6) primary key not null,
    MODEL char(30),
    DATE_MISE_SERVICE date,
    KILOMETRAGE number(6),
    ETAT char(20),
    PRIX_HORAIRE number(3,1),
    NIVEAU_DE_BATTERIE number(3),
    NUMERO_FOURNISSEUR number(3) not null,
    NUMERO_STATION number(4),
    foreign key (NUMERO_FOURNISSEUR) references FOURNISSEURS(NUMERO_FOURNISSEUR),
    foreign key (NUMERO_STATION) references STATIONS(NUMERO_STATION)
);

create table EMPRUNTS(
    NUMERO_EMPRUNT number(10) primary key not null,
    DATE_DE_DEBUT date,
    DATE_DE_FIN date,
    DISTANCE_PARCOURUE number(5),
    NUMERO_REFERENCE number(6), 
    NUMERO_STATION_DEPART number(4) not null, 
    NUMERO_STATION_ARRIVEE number(4), 
    NUMERO_ADHERENT number(4), 
    foreign key (NUMERO_REFERENCE) references VELOS(NUMERO_REFERENCE),
    foreign key (NUMERO_STATION_DEPART) references STATIONS(NUMERO_STATION),
    foreign key (NUMERO_STATION_ARRIVEE) references STATIONS(NUMERO_STATION),
    foreign key (NUMERO_ADHERENT) references ADHERENTS(NUMERO_ADHERENT)
);


create table DISTANCES(
    NUMERO_STATION_DEPART number(4) not null,
    NUMERO_STATION_ARRIVEE number(4) not null,
    DISTANCE number(6),
    foreign key (NUMERO_STATION_DEPART) references STATIONS(NUMERO_STATION),
    foreign key (NUMERO_STATION_ARRIVEE) references STATIONS(NUMERO_STATION),
    primary key (NUMERO_STATION_DEPART, NUMERO_STATION_ARRIVEE)
);

-- ============================================================
--          CREATION DES VUES
-- ============================================================

--Pour respecter le shéma conceptuel. distance(A,B)=distance(B,A)
create or replace view VIEW_DISTANCES as
(select * from DISTANCES
union select NUMERO_STATION_ARRIVEE,NUMERO_STATION_DEPART,DISTANCE
from DISTANCES)
with read only;



alter table STATIONS 
    add constraint CK_BORNE check(NB_BORNES >= 0);