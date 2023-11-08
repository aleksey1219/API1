-- liquibase formatted sql
-- changeset aglotov:1
create index facultyIndexNameOrColor on faculty (color, name) ;