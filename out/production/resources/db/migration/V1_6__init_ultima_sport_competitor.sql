CREATE TABLE competitor
(
    competitor_id               SERIAL      NOT NULL,
    age_category                VARCHAR(32) NOT NULL,
    start_number                INT         NOT NULL,
	person_id					INT			NOT NULL,
    tournament_id               INT        ,
   CONSTRAINT fk_competitor_person
           FOREIGN KEY (person_id)
               REFERENCES person (person_id),
    CONSTRAINT fk_competitor_tournament
               FOREIGN KEY (tournament_id)
                   REFERENCES tournament (tournament_id),
    PRIMARY KEY (competitor_id )
);