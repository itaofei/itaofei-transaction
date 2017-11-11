package itaofei.jdbc.transaction.mapper;

import itaofei.jdbc.transaction.entry.Test2;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description.
 *
 * @author itaofei@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface Test2Mapper {

    void insert(Test2 test2);
}
