-- 코드를 작성해주세요
with recursive gen_table as (
    select id, 1 as gen from ecoli_data where parent_id is null
    union all
    select e.id, gen + 1 from gen_table
    join ecoli_data e where gen_table.id = e.parent_id  
)

select count(g.id) as count, g.gen as generation
from gen_table g
left join ecoli_data e on e.parent_id = g.id
where e.id is null
#부모로 지정되어있지 않은 id들이 필요함
group by g.gen
order by generation asc;