-- 코드를 작성해주세요
select g3.id
from ecoli_data g1
join ecoli_data g2 on g1.id = g2.parent_id #이제 g1은 자기자식을 줄줄 달고있음
join ecoli_data g3 on g2.id = g3.parent_id #이제 g2는 자기 자식을 줄줄 달고있음.
where g1.parent_id is null
order by id asc;