BEGIN;
INSERT INTO "product" (title, price)
VALUES ('Iphone X', 500),
       ('Iphone 11', 670),
       ('Iphone 12', 850);
INSERT INTO "customer" (name)
VALUES ('Duke Nukem'),
       ('Doom Guy'),
       ('Gordon Freeman');
INSERT INTO "order" (date, product_id, customer_id)
VALUES (CURRENT_TIMESTAMP, 1, 1),
       (CURRENT_TIMESTAMP, 2, 1),
       (CURRENT_TIMESTAMP, 2, 2),
       (CURRENT_TIMESTAMP, 3, 3),
       (CURRENT_TIMESTAMP, 2, 3);
COMMIT;