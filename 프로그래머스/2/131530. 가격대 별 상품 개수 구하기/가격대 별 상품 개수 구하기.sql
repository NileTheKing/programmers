-- 코드를 입력하세요
SELECT floor(price / 10000) * 10000 as price_group, count(*) as products
from product
group by floor(price / 10000)
order by price_group;
#case when은 안됨...노가다니까 각바구니에 이름을 붙여줘야하는디..