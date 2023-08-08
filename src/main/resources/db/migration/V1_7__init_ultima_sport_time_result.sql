CREATE TABLE time_result
(
    time_result_id     SERIAL               NOT NULL,
    time              VARCHAR(20)          NOT NULL,
	competitor_id				INT					NOT NULL,
   CONSTRAINT fk_result_of_tournament_competitor
                    FOREIGN KEY (competitor_id)
                        REFERENCES competitor (competitor_id),

 PRIMARY KEY (time_result_id)
);