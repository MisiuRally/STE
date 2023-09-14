CREATE TABLE tournament
(
    tournament_id           SERIAL         NOT NULL,
    name_of_tournament      VARCHAR(32)    NOT NULL,
    number_of_start_plates  INT NOT NULL,
    sport_category          VARCHAR(32)    NOT NULL,
    start_of_tournament     TIMESTAMP WITH TIME ZONE NOT NULL,
    end_of_tournament       TIMESTAMP WITH TIME ZONE NOT NULL,
	organizer_id			INT     NOT NULL,



   CONSTRAINT fk_tournament_organizer
                        FOREIGN KEY (organizer_id)
                            REFERENCES organizer (organizer_id),


    PRIMARY KEY (tournament_id)
);