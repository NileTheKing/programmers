-- 코드를 작성해주세요
select id, 
    case
        when t.per <= 0.25 then 'CRITICAL'
        when t.per <= 0.5 then 'HIGH'
        when t.per <= 0.75 then 'MEDIUM'
        else 'LOW'
    end as colony_name
from (
    select id, percent_rank() over (order by size_of_colony desc) as per
    from ecoli_data
) as t
order by id asc;