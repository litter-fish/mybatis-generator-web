package com.fish.bean;

import com.fish.annotation.Need;
import lombok.Data;

/**
 * Created by yudin on 2016/12/18.
 */
@Data
public class TableConfig {

    @Need("true")
    private Boolean enableInsert;// 	指定是否生成Insert语句。  默认值是 true。
    @Need("true")
    private Boolean enableSelectByPrimaryKey;// 	指定是否生成通过主键查询的语句。 无论这个怎么设置，当表不存在主键的时候，不会生成这个语句。默认值是 true。
    @Need("true")
    private Boolean enableSelectByExample;// 	指定是否生成通过Example查询的语句。 这个语句支持运行时生成多种不同条件的动态查询。 默认值是 true。
    @Need("true")
    private Boolean enableUpdateByPrimaryKey;// 	指定是否生成通过主键更新的语句。 无论这个怎么设置，当表不存在主键的时候，不会生成这个语句。 默认值是 true。
    @Need("true")
    private Boolean enableDeleteByPrimaryKey;// 	指定是否生成通过主键删除的语句。 无论这个怎么设置，当表不存在主键的时候，不会生成这个语句。 默认值是 true。
    @Need("true")
    private Boolean enableDeleteByExample;// 	指定是否生成通过Example删除的语句。 这个语句支持运行时生成多种不同的条件动态删除。 默认值是 true。
    @Need("true")
    private Boolean enableCountByExample;// 	指定是否生成通过Example查询总数的语句。 这个语句将返回满足Example条件的数据总数。 默认值是 true。
    @Need("true")
    private Boolean enableUpdateByExample;// 	指定是否生成通过Example更新的语句。 这个语句将更新满足Example条件的数据。 如果设置为True,UpdateByExampleSelective语句也会生成。 这个语句只会更新那些参数中值不为null的的列。默认值是 true。
    @Need("true")
    private String selectByPrimaryKeyQueryId;// 	这个值会以"'<value>' as QUERYID"这种形式被添加到通过主键查询的语句的select列中。 这可能对在运行时的DBA跟踪工具中标记查询有用。 如果您使用这个值，您需要为MBG生成的每一个查询指定一个唯一的id。
    @Need("true")
    private String selectByExampleQueryId;// 	这个值会以"'<value>' as QUERYID"这种形式被添加到通过Example查询的语句的select列中。 这可能对在运行时的DBA跟踪工具中标记查询有用。 如果您使用这个值，您需要为MBG生成的每一个查询指定一个唯一的id。
    /**如果您需要，这个值可以用来重写默认的模型类型。 如果没有指定，MBG将会生成基于上下文默认模型类型的实体对象。 模型类型定义了MBG如何生成实体类。 有一些模型类型MGB会为每个表生成一个单独的实体类。 另外一些模型，MGB会根据表结构生成不同的一些类。 这个属性有以下可选值：
    conditional;// 	这个模型和hierarchical类似，除了如果那个单独的类将只包含一个字段，将不会生成一个单独的类。 因此,如果一个表的主键只有一个字段,那么不会为该字段生成单独的实体类,会将该字段合并到基本实体类中。
    flat 	该模型为每一张表只生成一个实体类。这个实体类包含表中的所有字段。
    hierarchical 	如果表有主键,那么该模型会产生一个单独的主键实体类,如果表还有BLOB字段， 则会为表生成一个包含所有BLOB字段的单独的实体类,然后为所有其他的字段生成一个单独的实体类。 MBG会在所有生成的实体类之间维护一个继承关系（注：BLOB类 继承 其他字段类 继承 主键类）。
    */
    @Need("true")
    private String  modelType;//
    @Need("true")
    private Boolean escapeWildcards;
    /**
    这个属性表示当查询列，是否对schema和表名中的SQL通配符 ('_' and '%') 进行转义。 对于某些驱动当schema或表名中包含SQL通配符时（例如，一个表名是MY_TABLE，有一些驱动需要将下划线进行转义）是必须的。
    默认值是 false.*/
    @Need("true")
    private Boolean delimitIdentifiers;
    /*这个属性表示当查询表并且在生成的SQL中分隔标识符时，是否使用指定的确切的值。 有关更多详细信息，请参见上面的详述。

    分隔符在 <context> 元素上指定。

    默认值是 false。除非 catalog, schema 或 tableName 属性值包含空白时，是 true.*/
    @Need("true")
    private Boolean delimitAllColumns;
    /*	指示是否给生成SQL中所有的列名添加分隔符。 这是一种给每个列添加<columnOverride>来指定列需要被分隔的替代方式。 这对类似PostgreSQL这种使用小写标识符的数据库很有用。

    分隔符在 <context> 元素上指定。

    默认值是 false.*/
}
