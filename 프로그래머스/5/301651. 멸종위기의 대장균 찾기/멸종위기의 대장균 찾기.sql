-- 코드를 작성해주세요

with recursive gentable as (
    select id, 1 as gen
    from ecoli_data where parent_id is null
    union all
    select e.id, gen + 1
    from gentable g join ecoli_data e on g.id = e.parent_id
)

select count(*) as count, gen as generation
from gentable g left join ecoli_data e on g.id = e.parent_id
where e.id is null
group by gen
order by gen asc;