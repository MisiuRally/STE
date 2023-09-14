 CREATE TABLE tournament_competitor
 (
     tournament_competitor_id     SERIAL               NOT NULL,
      tournament_id                 INT NOT NULL,
      competitor_id                 INT NOT NULL,
    CONSTRAINT tournament_competitor_tournament
                        FOREIGN KEY (tournament_id)
                              REFERENCES tournament (tournament_id),
     CONSTRAINT fk_tournament_competitor_competitor
                            FOREIGN KEY (competitor_id)
                                  REFERENCES competitor (competitor_id),

  PRIMARY KEY (tournament_competitor_id )

);