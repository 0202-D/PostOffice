CREATE TABLE history (
                         id BIGSERIAL PRIMARY KEY,
                         postal_delivery_id BIGINT REFERENCES postal_delivery(id),
                         post_office_id BIGINT REFERENCES post_office(id),
                         status VARCHAR(50),
                         local_date_time TIMESTAMP
);