-- 코드를 입력하세요
SELECT h.flavor
from first_half h
join (
    select flavor, sum(total_order) as total_order
    from july
    group by flavor
) as t on h.flavor = t.flavor
order by (h.total_order + t.total_order) desc limit 3;
