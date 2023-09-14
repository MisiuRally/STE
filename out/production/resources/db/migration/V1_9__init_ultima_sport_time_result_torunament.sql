 CREATE TABLE time_result_tournament
 (
     time_result_tournament_id     SERIAL               NOT NULL,
      time_result_id  INT NOT NULL,
      tournament_id   INT NOT NULL,
    CONSTRAINT fk_time_result_tournament
                        FOREIGN KEY (time_result_id)
                              REFERENCES time_result (time_result_id),
     CONSTRAINT fk_time_result_tournament_tournament
                            FOREIGN KEY (tournament_id)
                                  REFERENCES tournament (tournament_id),

  PRIMARY KEY (time_result_tournament_id )

);