-- 코드를 입력하세요
SELECT i.animal_id, i.animal_type, i.name
from animal_ins i
join animal_outs o
on i.animal_id = o.animal_id
where i.sex_upon_intake like '%Intact%' and (o.SEX_UPON_OUTCOME like '%Spayed%' or o.SEX_UPON_OUTCOME like '%Neutered%')
order by i.animal_id;


# 중성화된 남자는 intact -> neutered, 여자는 intact -> sprayed
#그러면 좌우로 붙여서 intact에서 neutered or sprayed된 애들 몇마리인지