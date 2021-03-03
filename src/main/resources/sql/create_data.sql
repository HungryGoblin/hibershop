BEGIN;
INSERT INTO "product" (title, price)
VALUES ('Iphone X', 500),
       ('Iphone 11', 670),
       ('Iphone 12', 850);
INSERT INTO "customer" (name)
VALUES ('Duke Nukem'),
       ('Doom Guy'),
       ('Gordon Freeman');
INSERT INTO "custorder" (date, amount, product_id, customer_id)
VALUES (CURRENT_TIMESTAMP, 500, 1, 1),
       (CURRENT_TIMESTAMP, 600, 2, 1),
       (CURRENT_TIMESTAMP, 550, 2, 2),
       (CURRENT_TIMESTAMP, 700, 3, 3),
       (CURRENT_TIMESTAMP, 400, 2, 3);
COMMIT;