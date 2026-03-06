select date_format(SALES_DATE, '%Y-%m-%d') as sales_date,PRODUCT_ID, USER_ID,SALES_AMOUNT
from online_sale
where sales_date like '2022-03%'
union all
select date_format(SALES_DATE, '%Y-%m-%d') as sales_date, PRODUCT_ID, null as user_id,SALES_AMOUNT
from offline_sale
where sales_date like '2022-03%'
order by sales_date asc, product_id asc, user_id asc;