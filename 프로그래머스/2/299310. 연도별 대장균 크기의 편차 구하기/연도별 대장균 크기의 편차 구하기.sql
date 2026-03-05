-- 코드를 작성해주세요
select year(DIFFERENTIATION_DATE) as year,
    max_size - size_of_colony as year_dev, id
from ecoli_data e
join (
    select year(DIFFERENTIATION_DATE) as year, max(SIZE_OF_COLONY) as max_size
    from ecoli_data
    group by year(DIFFERENTIATION_DATE)
) as T on year(e.DIFFERENTIATION_DATE)  = T.year
order by year asc, year_dev asc;