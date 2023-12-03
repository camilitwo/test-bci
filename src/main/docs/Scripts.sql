CREATE TABLE PHONE (
                       id VARCHAR(36) NOT NULL,
                       number VARCHAR(255),
                       citycode VARCHAR(255),
                       contrycode VARCHAR(255),
                       PRIMARY KEY (id)
);


CREATE TABLE USER (
                      id VARCHAR(36) NOT NULL,
                      created DATE,
                      lastLogin DATE,
                      isActive BOOLEAN,
                      name VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255),
                      token VARCHAR(255),
                      PRIMARY KEY (id)
);

