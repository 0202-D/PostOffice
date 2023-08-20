CREATE TABLE post_office
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(50),
    recipient_address VARCHAR(255)
);
CREATE TABLE postal_delivery
(
    id              SERIAL PRIMARY KEY,
    type            VARCHAR(25),
    index           VARCHAR(10),
    address         VARCHAR(255),
    recipients_name VARCHAR(50)
);
