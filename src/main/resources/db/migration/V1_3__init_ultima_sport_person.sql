CREATE TABLE person
(
    person_id       SERIAL          NOT NULL,
    name            VARCHAR(20)     NOT NULL,
    surname         VARCHAR(32)     NOT NULL,
    date_of_birth   VARCHAR(32)    NOT NULL,
    email           VARCHAR(32)     NOT NULL,
    phone           VARCHAR(32)     NOT NULL,
    sex             VARCHAR(32)     NOT NULL,
	address_id		INT				NOT NULL,
	PRIMARY KEY (person_id),
--    UNIQUE (email),
    CONSTRAINT fk_person_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id)

);