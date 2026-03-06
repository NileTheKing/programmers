-- 코드를 작성해주세요
select year(DIFFERENTIATION_DATE) as year, 
    t.best - e.size_of_colony as year_dev, id
from (
    select year(DIFFERENTIATION_DATE) as year, max(size_of_colony) as best
    from ecoli_data
    group by year
) t
join ecoli_data e on year(e.DIFFERENTIATION_DATE) = t.year
order by year asc, year_dev asc;