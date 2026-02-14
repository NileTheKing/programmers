-- 코드를 입력하세요
SELECT truncate(price, -4) as price_group, count(*) as products
from product
group by truncate(price, -4)
order by price_group asc;