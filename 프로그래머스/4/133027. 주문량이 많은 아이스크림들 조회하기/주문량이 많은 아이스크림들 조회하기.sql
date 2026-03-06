-- 코드를 입력하세요
SELECT t.flavor
from (
    select flavor, sum(total_order) as total_order
    from july
    group by flavor
) t
join first_half h on h.flavor= t.flavor
order by t.total_order + h.total_order desc limit 3;