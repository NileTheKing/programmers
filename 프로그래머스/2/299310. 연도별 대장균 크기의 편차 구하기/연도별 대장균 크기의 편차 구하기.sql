-- 코드를 작성해주세요
select sub.year, sub.max_size - e.size_of_colony as year_dev, e.id
from ecoli_data e
join (
    select year(DIFFERENTIATION_DATE) as year, max(size_of_colony) as max_size
    from ecoli_data
    group by year(DIFFERENTIATION_DATE)
) as sub
on year(e.DIFFERENTIATION_DATE) = sub.year
order by sub.year asc, year_dev asc;