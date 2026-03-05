-- 코드를 입력하세요
with recursive time_table as (
    select 0 as hour
    union all
    select hour + 1
    from time_table
    where hour < 23
)

SELECT t.hour as hour, count(i.animal_id) as count
from time_table t
left join animal_outs i on t.hour = hour(i.datetime)
group by t.hour
order by hour asc;