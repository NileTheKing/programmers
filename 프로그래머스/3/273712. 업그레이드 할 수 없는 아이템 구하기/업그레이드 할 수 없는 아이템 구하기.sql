-- 코드를 작성해주세요
# 누군가의 parent_id로 있으면 안되는것.그러면..도화지를 하나 더 파거나 재귀돌리거나 흠.
# 말로 먼저 할 줄 알아야함

select i.item_id, i.item_name, i.rarity
from item_info i
join item_tree t on i.item_id = t.item_id
where i.item_id not in (
    select parent_item_id
    from item_tree
    where parent_item_id is not null
)
order by item_id desc;

