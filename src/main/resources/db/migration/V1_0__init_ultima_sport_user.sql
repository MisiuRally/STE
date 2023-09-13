CREATE TABLE users
(
    user_id                 SERIAL      NOT NULL,
    user_name                VARCHAR(32) NOT NULL,
    password                VARCHAR(82)         NOT NULL,
    active                  BOOLEAN       NOT NULL,

    PRIMARY KEY (user_id )

);

CREATE TABLE role
(
    role_id  SERIAL          NOT NULL,
    role     VARCHAR(20)     NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE user_role
(
user_id   INT				NOT NULL,
role_id     INT				NOT NULL,
PRIMARY KEY (user_id,role_id ),
CONSTRAINT fk_users_role_user
            FOREIGN KEY (user_id)
                REFERENCES users (user_id),
CONSTRAINT fk_users_role_role
            FOREIGN KEY (role_id)
                REFERENCES role (role_id)
);