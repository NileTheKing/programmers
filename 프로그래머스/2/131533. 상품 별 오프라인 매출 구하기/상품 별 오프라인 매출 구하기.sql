-- 코드를 입력하세요
SELECT product_code, p.price * sum(s.sales_amount) as sales
from product p
join offline_sale s
on p.product_id = s.product_id
group by s.product_id
order by sales desc, p.product_code asc;