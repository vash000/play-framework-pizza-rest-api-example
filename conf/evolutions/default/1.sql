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

create table restaurant_menu_item (
  id                        bigint not null,
  currency                  varchar(3)  not null,
  amount                    decimal(10,2) not null,
  name                      varchar(255) not null,
  category                  varchar(255) not null, --it might be a rel
  rank                      int
  restaurant_id             bigint not null,
  constraint pk_restaurant_menu_item primary key (id)
);

alter table restaurant_menu_item add constraint fk_restaurant_menu_restaurant_item_1 foreign key (restaurant_id) references restaurant (id) on delete restrict on update restrict;
create index ix_restaurant_menu_item_1 on restaurant_menu_item (restaurant_id);



create sequence restaurant_seq start with 1000;
create sequence restaurant_menu_item_seq start with 1000;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

--drop table if exists topping_to_menu;


drop table if exists restaurant;
drop table if exists restaurant_menu_item;


SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists restaurant_seq;
drop sequence if exists restaurant_menu_item_seq;

