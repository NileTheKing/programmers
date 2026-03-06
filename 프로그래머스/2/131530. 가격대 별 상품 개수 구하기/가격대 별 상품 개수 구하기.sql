-- 코드를 입력하세요
SELECT t.price_group, count(*) as products
from (
    select product_id, floor(price / 10000) * 10000 as price_group
    from product
) as t
join product p on t.product_id = p.product_id
group by t.price_group
order by price_group asc;