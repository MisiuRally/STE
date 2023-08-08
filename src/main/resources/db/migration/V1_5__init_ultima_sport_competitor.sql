CREATE TABLE competitor
(
    competitor_id               SERIAL      NOT NULL,
    age_category                VARCHAR(32) NOT NULL,
    start_number                INT         NOT NULL,
    result               VARCHAR(32) ,
	person_id					INT			NOT NULL,

   CONSTRAINT fk_competitor_person
           FOREIGN KEY (person_id)
               REFERENCES person (person_id),
    
    PRIMARY KEY (competitor_id )

);