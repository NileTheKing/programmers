-- 코드를 작성해주세요
select item_id, item_name, rarity
from item_info
where item_id in (
    select t.item_id
    from item_info as i
    join item_tree as t
    on i.item_id = t.parent_item_id
    where i.rarity = 'rare'
)
order by item_id desc;