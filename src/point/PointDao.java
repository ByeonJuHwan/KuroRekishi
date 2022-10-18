package point;

import java.util.List;

public interface PointDao {

    /**
     * 자신이 받은 점수의 총점과 몇명이 받았는지 알려준다.
     * @param id
     * @return 받은점수의 총점과 명수를 계산해서 리스트에 저장해서 알려준다. 첫번째 값이 받은 점수, 두번째 값이 명수이다.
     */
    List<Integer> getStarPoint(String id);
    
    /**
     * 자신이입력한 점수를 상대방 이름으로 저장한다.
     * @param point
     * @return 성공하면 1, 실패하면 0
     */
    int insertPoint(Point point);
    
    /**
     * 중복으로 점수를 주는것을 막기위해 아이디를 비교
     * @param id
     * @return
     */
    List<String> getGiveId(String id);
}
