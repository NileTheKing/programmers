-- 코드를 입력하세요
with recursive time_table as (
    select 0 as hour
    union all
    select hour + 1 from time_table where hour < 23
)
SELECT t.hour, count(animal_id)
from time_table t
left join animal_outs a
on t.hour = hour(a.datetime)
group by t.hour
order by t.hour