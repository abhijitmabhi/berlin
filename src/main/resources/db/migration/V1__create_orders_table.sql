create table if not exists orders (
  id serial primary key,
  title varchar(255) not null,
  description text,
  order_date_time timestamp not null default now()
);
