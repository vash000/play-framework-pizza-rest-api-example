# --- First database schema

# --- !Ups
---
create table restaurant (
  id                        bigint not null,
  name                      varchar(255) not null,
  address1                  varchar(255) not null,
  address2                  varchar(255) not null,
  latitude                  decimal(8,6) not null,
  longitude                 decimal(8,6) not null,
  constraint pk_restaurant primary key (id)
);


create sequence restaurant_seq start with 1000;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

--drop table if exists topping_to_menu;


drop table if exists restaurant;


SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists restaurant_seq;

