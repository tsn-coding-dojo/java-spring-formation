--liquibase formatted sql
--changeset formateur:1
CREATE TABLE IF NOT EXISTS todo (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	status VARCHAR(255)
);