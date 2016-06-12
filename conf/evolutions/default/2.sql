# --- Sample dataset

# --- !Ups

insert into restaurant (id,name, address1, address2, latitude, longitude) values (1,'Pizza Heaven','Kungsgatan 1','111 43 Stockholm',59.336078,18.071807);
insert into restaurant (id,name, address1, address2, latitude, longitude) values (2,'Pizzeria Apan','Langholmgatan 34','117 33 Stockholm',59.315709,18.033507);

# --- !Downs

delete from restaurant;
