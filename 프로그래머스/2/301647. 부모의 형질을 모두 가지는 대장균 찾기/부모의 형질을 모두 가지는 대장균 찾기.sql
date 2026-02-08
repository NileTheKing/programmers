-- 코드를 작성해주세요
select r.id, r.genotype, l.genotype as parent_genotype
from ecoli_data l
join ecoli_data r
on l.id = r.parent_id
where r.genotype & l.genotype = l.genotype
order by id asc;
#  join해서 하거나...
    # 11 101 .....그니까 101의 맨 마지막 두개가 켜져있어야하는데.. 이거 비트연산 어떻게하노 ㅋㅋ
#  select안에 select넣어서 재귀?