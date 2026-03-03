-- 코드를 작성해주세요
select item_id, item_name, rarity
from item_info
where item_id in ( #내 id를 부모로 가지고 있는 item_id들 출력
        select t.item_id
        from item_tree t #t는 id 업글후id
        join item_info i on t.parent_item_id = i.item_id #i가 자식..업글전
        where i.rarity = 'rare'
    )
order  by item_id desc;
#하려는게.. 자식이 업그레이드 된 아이템이잖아 그치
#부모의 아이템이 rare이고 ..그렇다면 걔 부모의 정보 뽑는거임.
#그렇다면 테이블조인 or 재귀인데..다음거만 뽑으니까 서브쿼리1번으로 가능하지 재귀도 당연히되는데 그건 비효울적.
#아무튼 parent item id를 item_info에서 출력하는것
#일단 생각나는 서브쿼리로 가보자 select로 가야겠지?