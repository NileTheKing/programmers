select p.id, count(c.id) as child_count
from ecoli_data p
left join ecoli_data c on p.id = c.parent_id
group by p.id
order by id asc;