 CREATE TABLE tournament_competitor
 (
      tournament_competitor_id       SERIAL              NOT NULL,
      tournament_entity_id                 INT                 ,
      competitor_id                 INT                ,
    CONSTRAINT tournament_competitor_tournament
                        FOREIGN KEY (tournament_entity_id)
                              REFERENCES tournament (id),
     CONSTRAINT fk_tournament_competitor_competitor
                            FOREIGN KEY (competitor_id)
                                  REFERENCES competitor(competitor_id),

  PRIMARY KEY (tournament_competitor_id )

);