DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS competitor CASCADE;
DROP TABLE IF EXISTS organizer CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS result_of_tournament CASCADE;
DROP TABLE IF EXISTS tournament CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS competitor CASCADE;
DROP TABLE IF EXISTS organizer CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS result_of_tournament CASCADE;
DROP TABLE IF EXISTS tournament CASCADE;


CREATE TABLE address
(
    address_id  SERIAL      NOT NULL,
    country     VARCHAR(32) NOT NULL,
    city        VARCHAR(32) NOT NULL,
    postal_code VARCHAR(32) NOT NULL,
    street      VARCHAR(32) NOT NULL,
    PRIMARY KEY (address_id)
);

CREATE TABLE person
(
    person_id       SERIAL          NOT NULL,
    name            VARCHAR(20)     NOT NULL,
    surname         VARCHAR(32)     NOT NULL,
    date_of_birth   TIMESTAMP WITH TIME ZONE        NOT NULL,
    email           VARCHAR(32)     NOT NULL,
    phone           VARCHAR(32)     NOT NULL,
    sex             VARCHAR(32)     NOT NULL,
	address_id		INT				NOT NULL,
	PRIMARY KEY (person_id),
    UNIQUE (email),
    CONSTRAINT fk_person_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id)

);


CREATE TABLE competitor
(
    competitor_id               SERIAL      NOT NULL,
    age_category                VARCHAR(32) NOT NULL,
    start_number                INT         NOT NULL,
	person_id					INT			NOT NULL,
   CONSTRAINT fk_competitor_person
           FOREIGN KEY (person_id)
               REFERENCES person (person_id),
    PRIMARY KEY (competitor_id )
);


CREATE TABLE organizer
(
    organizer_id            SERIAL      NOT NULL,
    name_of_organizer       VARCHAR(32) NOT NULL,
    email                   VARCHAR(32) NOT NULL,
    phone                   VARCHAR(32) NOT NULL,
	address_id				INT			NOT NULL,
    PRIMARY KEY (organizer_id),
    UNIQUE (email),
    CONSTRAINT fk_organizer_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)
);

CREATE TABLE tournament
(
    tournament_id           SERIAL         NOT NULL,
    name_of_tournament      VARCHAR(32)    NOT NULL,
    number_of_start_plates  INT NOT NULL,
    sport_category          VARCHAR(64)    NOT NULL,
    start_of_tournament     TIMESTAMP WITH TIME ZONE NOT NULL,
    end_of_tournament       TIMESTAMP WITH TIME ZONE NOT NULL,
	organizer_id			INT     NOT NULL,
	competitor_id			INT		NOT NULL,
    CONSTRAINT fk_tournament_organizer
                        FOREIGN KEY (organizer_id)
                            REFERENCES organizer (organizer_id),
    CONSTRAINT fk_tournament_competitor
                            FOREIGN KEY (competitor_id)
                                REFERENCES competitor (competitor_id),
    PRIMARY KEY (tournament_id)
);


CREATE TABLE time_result
(
    time_result_id     SERIAL               NOT NULL,
    result               VARCHAR(20)          NOT NULL,
	competitor_id				INT					NOT NULL,
   CONSTRAINT fk_result_of_tournament_competitor
                    FOREIGN KEY (competitor_id)
                        REFERENCES competitor (competitor_id),

 PRIMARY KEY (time_result_id)
);

 CREATE TABLE time_result_tournament
 (
     time_result_tournament_id     SERIAL               NOT NULL,
      time_result_id  INT NOT NULL,
      tournament_id   INT NOT NULL,
    CONSTRAINT fk_time_result_tournament_time_result
                        FOREIGN KEY (time_result_id)
                              REFERENCES time_result (time_result_id),
     CONSTRAINT fk_time_result_tournament_tournament
                            FOREIGN KEY (tournament_id)
                                  REFERENCES tournament (tournament_id),

  PRIMARY KEY (time_result_tournament_id )

);



