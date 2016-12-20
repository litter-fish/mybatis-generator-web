package com.fish.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by yudin on 2016/12/19.
 */
@Data
public class ContextConfig {

    private String id;
    /**
     * 这个属性用来设置生成对象类型的默认值。 对象类型定义了MBG如何生成实体类。 对某些对象类型，MBG会给每一个表生成一个单独的实体类。 对另外一些对象类型，MBG会根据表结构生成不同的类。这个属性有以下可选值：
     * conditional 	这是默认值
     * 这个模型和hierarchical类似，除了如果那个单独的类将只包含一个字段，将不会生成一个单独的类。 因此,如果一个表的主键只有一个字段,那么不会为该字段生成单独的实体类,会将该字段合并到基本实体类中。
     * flat 	该模型为每一张表只生成一个实体类。这个实体类包含表中的所有字段。
     * hierarchical 	如果表有主键,那么该模型会产生一个单独的主键实体类,如果表还有BLOB字段， 则会为表生成一个包含所有BLOB字段的单独的实体类,然后为所有其他的字段生成一个单独的实体类。 MBG会在所有生成的实体类之间维护一个继承关系（注：BLOB类 继承 其他字段类 继承 主键类）。
     */
    private String defaultModelType;
    /**
     * 此属性用于指定生成的代码的运行时目标。 该属性支持这些特殊的值：
     * MyBatis3 	这是默认值
     * 使用这值的时候，MBG会生成兼容MyBatis 3.0或更高版本， 兼容JSE 5.0或更高版本的对象（例如Java模型类和Mapper接口会使用泛型）。 这些生成对象中的"by example"方法将支持几乎不受限制的动态的where子句。 另外，这些生成器生成的Java对象支持JSE 5.0特性，包含泛型和注解。
     * MyBatis3Simple 	这是默认值
     * 使用这值的时候，和上面的MyBatis3类似，但是不会有"by example"一类的方法，只有少量的动态SQL。
     * Ibatis2Java2 	使用这值的时候，MBG会生成兼容iBATIS 2.2.0或更高版本（除了iBATIS 3），还有Java2的所有层次。 这些生成对象中的"by example"方法将支持几乎不受限制的动态的where子句。 这些生成的对象不能100%和原生的Abator或其他的代码生成器兼容。
     * Ibatis2Java5 	使用这值的时候，MBG会生成兼容iBATIS 2.2.0或更高版本（除了iBATIS 3）， 兼容JSE 5.0或更高版本的对象（例如Java模型类和Dao类会使用泛型）。 这些生成对象中的"by example"方法将支持几乎不受限制的动态的where子句。 另外，这些生成器生成的Java对象支持JSE 5.0特性，包含泛型和注解。 这些生成的对象不能100%和原生的Abator或其他的代码生成器兼容。
     * <p>
     * 如果您想创建一个完全不同的代码生成器， 使用一个继承了org.mybatis.generator.api.IntrospectedTable类的权限定类名替换该值。 通过这个值，您可以创建您自己的代码生成器，然后插入到代码生成器引擎中。 查阅 扩展 MyBatis Generator 页面获取更多信息。
     */
    private String targetRuntime;
    /**
     * 使用这个值去指定一个继承了org.mybatis.generator.api.IntrospectedColumn类的权限定名称。 这可以修改代码生成器计算列信息时候的行为。
     * 查阅 扩展 MyBatis Generator 页面获取更多信息。
     */
    private String introspectedColumnImpl;

    private List<PropertyGenerator> property;

    private List<TableConfig> table;

    private JavaModelGenerator javaModelGenerator;

    private JavaClientGenerator javaClientGenerator;

    private SqlMapGenerator sqlMapGenerator;

    private JdbcConnection jdbcConnection;

    private JavaTypeResolver javaTypeResolver;

    private CommentGenerator commentGenerator;

}
