-- 코드를 작성해주세요
select year(YM) as year, round(avg(pm_val1),2) as PM10, round(avg(pm_val2),2) as `PM2.5`
from air_pollution
where location1 = '경기도' and location2 = '수원'
group by year(ym)
order by year asc;