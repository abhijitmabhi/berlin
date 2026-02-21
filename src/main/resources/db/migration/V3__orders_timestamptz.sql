alter table orders
  alter column order_date_time
  type timestamptz
  using order_date_time at time zone 'UTC';
