package itaofei.jdbc.transaction.entrypoint;

import itaofei.jdbc.transaction.entry.Test1;
import itaofei.jdbc.transaction.entry.Test2;
import itaofei.jdbc.transaction.mapper.Test1Mapper;
import itaofei.jdbc.transaction.mapper.Test2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Application.
 *
 * @author itaofei@gmail.com
 * @since 1.0.0
 */
@Slf4j
@Configurable
@ImportResource({"classpath:spring-core.xml","classpath:spring-mybatis.xml"})
public class Application {

    @Autowired
    private Test1Mapper test1Mapper;
    @Autowired
    private Test2Mapper test2Mapper;

    private static ApplicationContext context;

    public static void main(String[] args) throws Exception {

        context =
                new AnnotationConfigApplicationContext(Application.class);

        Application application =
                context.getBean(Application.class);

        application.doTransaction1();

        application.doTransaction2();
    }

    @Transactional
    void doTransaction1(){

        Test1 test1 = new Test1();
        test1.setC1("aa");
        test1Mapper.insert(test1);

        Test2 test2 = new Test2();
        test2.setC1("aa");
        test2Mapper.insert(test2);
    }

    void doTransaction2() {

        TransactionTemplate tt =
                (TransactionTemplate) context.getBean("transactionTemplate");

        tt.execute(new TransactionCallback<Object>() {
            @Nullable
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {

                Test1 test1 = new Test1();
                test1.setC1("aa");
                test1Mapper.insert(test1);

                Test2 test2 = new Test2();
                test2.setC1("aaa");
                test2Mapper.insert(test2);
                return null;
            }
        });

    }
}
