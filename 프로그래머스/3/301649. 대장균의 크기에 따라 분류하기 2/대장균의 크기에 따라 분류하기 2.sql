-- 코드를 작성해주세요
select id,
    case
        when per >= 0.75 then 'LOW'
        when per >= 0.5 then 'MEDIUM'
        when per >= 0.25 then 'HIGH'
        else 'CRITICAL'
    end as colony_name
from (
    select id, percent_rank() over (order by size_of_colony desc) as per
    from ecoli_data
) as T
order by id;