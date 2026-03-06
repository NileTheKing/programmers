-- 코드를 작성해주세요
select i2.item_id, i2.item_name, i2.rarity
from item_info i
join item_tree t on i.item_id = t.parent_item_id #이제 각 아이템은 다음 업그레이드 아이템이있음
join item_info i2 on t.item_id = i2.item_id
where i.rarity = 'rare'
order by i2.item_id desc;

#서브쿼리 조인으로 풀 수 있음.
#조인으로..