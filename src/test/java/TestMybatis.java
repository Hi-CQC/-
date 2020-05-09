import com.youfan.entity.Person;
import com.youfan.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public SqlSessionFactory getfactory() throws IOException{
        String filepath = "SqlMappingConfig.xml";
        InputStream in = Resources.getResourceAsStream(filepath);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return sqlSessionFactory;
    }

    @Test
    public void testInsert() throws IOException {
        SqlSessionFactory factory = this.getfactory();
        SqlSession sqlSession = factory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person();
        person.setAddress("上海");
        person.setAge(15);
        person.setBirthday("05-04");
        person.setName("cqc");
        personMapper.insertPerson(person);
        System.out.println(person.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testquerybyid() throws IOException {
        SqlSessionFactory factory = this.getfactory();
        SqlSession sqlSession = factory.openSession();
        Person person = sqlSession.selectOne("test1.querypersonbyid", 2);
        System.out.println(person.getName());
        sqlSession.close();
    }

    @Test
    public void testquerybyname() throws IOException {
        SqlSessionFactory factory = this.getfactory();
        SqlSession sqlSession = factory.openSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> list = mapper.queryPersonByName("youfan");

        for (Person person : list) {
            System.out.println(person);
        }

        sqlSession.close();
    }
}
