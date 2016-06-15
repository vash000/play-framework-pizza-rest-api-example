# --- Sample dataset

# --- !Ups

insert into restaurant (id,name, address1, address2, latitude, longitude) values (1,'Pizza Heaven','Kungsgatan 1','111 43 Stockholm',59.336078,18.071807);
insert into restaurant (id,name, address1, address2, latitude, longitude) values (2,'Pizzeria Apan','Langholmgatan 34','117 33 Stockholm',59.315709,18.033507);

insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (1,2,79,'SEK',3,'Pizza','Vesuvius');
insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (2,2,79,'SEK',1,'Pizza','Hawaii');
insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (3,2,89,'SEK',2,'Pizza','Parma');
insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (4,2,10,'SEK',null,'Dryck','Coca-cola, 33cl');
insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (5,2,10,'SEK',null, 'Dryck','Loka citron, 33cl');
insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (6,2, 0,'SEK',null, 'Tillbehör','Pizzasallad');
insert into restaurant_menu_item (id,restaurant_id,amount,currency,rank,category,name) values (7,2,10,'SEK',null, 'Tillbehör','Pizzasallad');

# --- !Downs

delete from restaurant;
delete from product;
delete from restaurant_menu_item;
