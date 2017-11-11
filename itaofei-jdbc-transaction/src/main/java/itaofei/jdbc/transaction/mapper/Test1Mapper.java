package itaofei.jdbc.transaction.mapper;

import itaofei.jdbc.transaction.entry.Test1;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description.
 *
 * @author itaofei@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface Test1Mapper {

    void insert(Test1 test1);
}
