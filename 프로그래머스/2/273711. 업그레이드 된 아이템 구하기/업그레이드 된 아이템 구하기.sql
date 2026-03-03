-- 코드를 작성해주세요
select i.item_id, i.item_name, i.rarity
from item_info parent
join item_tree child on parent.item_id = child.parent_item_id #이제 자식들이 줄줄달림
join item_info i on child.item_id = i.item_id #자식들의 정보를 가져옴. 왜? 거기붙어있는 id로는 정보못뽐음
where parent.rarity = 'rare'
order by item_id desc;
#하려는게.. 자식이 업그레이드 된 아이템이잖아 그치
#부모의 아이템이 rare이고 ..그렇다면 걔 부모의 정보 뽑는거임.
#그렇다면 테이블조인 or 재귀인데..다음거만 뽑으니까 서브쿼리1번으로 가능하지 재귀도 당연히되는데 그건 비효울적.
#아무튼 parent item id를 item_info에서 출력하는것
#일단 생각나는 서브쿼리로 가보자 select로 가야겠지?