CREATE TABLE organizer
(
    organizer_id            SERIAL      NOT NULL,
    name_of_organizer       VARCHAR(32) NOT NULL,
    email                   VARCHAR(32) NOT NULL,
    phone                   VARCHAR(32) NOT NULL,
	address_id				INT			NOT NULL,
    PRIMARY KEY (organizer_id),
--    UNIQUE (email),
    CONSTRAINT fk_organizer_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)
);