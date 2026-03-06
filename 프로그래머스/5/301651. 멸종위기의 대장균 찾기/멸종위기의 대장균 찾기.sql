-- 코드를 작성해주세요
with recursive gen_table as (
    select id, 1 as gen
    from ecoli_data
    where parent_id is null
    
    union all

    select e.id, g.gen + 1
    from ecoli_data e join gen_table g on e.parent_id = g.id
)


select count(*) as count, g.gen as generation
from gen_table g left join ecoli_data e on g.id = e.parent_id
where e.id is null
group by generation
order by generation asc;