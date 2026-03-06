-- 코드를 입력하세요
with recursive time as (
    select 0 as hour
    union all
    select hour + 1 from time where hour < 23
)

SELECT t.hour as hour , count(animal_id) as count
from time t 
left join animal_outs o on t.hour = hour(o.datetime)
group by hour
order by hour;