import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class testgetdatabase {

    @Test
    public void testgetdatabase() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        int con = sqlSession.selectOne("getUserCount");
        System.out.println(con);
    }
}
