CREATE TABLE tournament
(
    id                      SERIAL         NOT NULL,
    name_of_tournament      VARCHAR(32)    NOT NULL,
    number_of_start_plates  INT NOT NULL,
    sport_category          VARCHAR(32)    NOT NULL,
    start_of_tournament     VARCHAR(32) NOT NULL,
    end_of_tournament       VARCHAR(32) NOT NULL,
    distance                INT NOT NULL,
    buy_in                  INT     ,
    start_of_competitor     VARCHAR(32),
	organizer_id			INT     NOT NULL,


     CONSTRAINT fk_tournament_organizer
                        FOREIGN KEY (organizer_id)
                            REFERENCES organizer (organizer_id),


    PRIMARY KEY (id)
);