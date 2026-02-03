-- 코드를 입력하세요
select a.flavor
from first_half as a
join icecream_info as b
on a.flavor = b.flavor
where total_order > 3000 and ingredient_type ='fruit_based'
order by total_order desc;