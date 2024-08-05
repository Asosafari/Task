

CREATE TABLE user (
    id varchar(36) not null,
    username VARCHAR(255) NOT NULL UNIQUE ,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL,
    token_created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version INT,
    PRIMARY KEY (id)
)engine=InnoDB;