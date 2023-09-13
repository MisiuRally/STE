CREATE TABLE competitor
(
    competitor_id               SERIAL      NOT NULL,
    age_category                VARCHAR(32) NOT NULL,
    start_number                INT         NOT NULL,
    result               VARCHAR(32) ,
    average_speed             VARCHAR(5) ,
    place                       VARCHAR(5),
	person_id					INT			NOT NULL,
    tournament                  INT,

   CONSTRAINT fk_competitor_person
           FOREIGN KEY (person_id)
               REFERENCES person (person_id),
               CONSTRAINT tournament
                          FOREIGN KEY (tournament)
                              REFERENCES tournament (id),
    
    PRIMARY KEY (competitor_id )

);