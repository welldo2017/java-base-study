package com.welldo.zero.a16_maven;

/**
 * 依赖的传递
 * 上面我们创建的maven-chat02中依赖了spring-web，
 * 而我们只引入了spring-web依赖，而spring-web又依赖了spring-beans、spring-core、spring-jcl，
 * 这3个依赖也被自动加进来了，这种叫做依赖的传递。
 *
 * 不过上面我们说的scope元素的值会对这种传递依赖会有影响。
 *
 *
 * 假设A依赖于B，B依赖于C，
 * 我们说A对于B是第一直接依赖，B对于C是第二直接依赖，
 * 而A对于C是传递性依赖，
 * 而第一直接依赖的scope和第二直接依赖的scope决定了传递依赖的范围，即决定了A对于C的scope的值。
 *
 *
 * 下面我们用表格来列一下这种依赖的效果，
 * 表格最左边一列表示第一直接依赖（即A->B的scope的值）,而表格中的第一行表示第二直接依赖（即B->C的scope的值），行列交叉的值显示的是A对于C最后产生的依赖效果。
 *
 *      \   |compile     test    provided    runtime
 * ---------|--------------------------------------------
 * compile  |compile     -       -           runtime
 * test	    |test    	-	    -	        test
 * provided	|provided	-	    provided	provided
 * runtime	|runtime	    -	    -	        runtime
 * -----------------------------------------------------
 * 解释一下：
 *
 * 比如A->B的scope是compile，而B->C的scope是test，那么按照上面表格中，对应第2行第3列的值-，那么A对于C是没有依赖的，A对C的依赖没有从B->C传递过来，所以A中是无法使用C的
 * 比如A->B的scope是compile，而B->C的scope是runtime，那么按照上面表格中，对应第2行第5列的值为runtime，那么A对于C是的依赖范围是runtime，表示A只有在运行的时候C才会被添加到A的classpath中，即对A进行运行打包的时候，C会被打包到A的包中
 * 大家仔细看一下，上面的表格是有规律的，当B->C依赖是compile的时候（表中第2列），那么A->C的依赖范围和A->B的sope是一样的；当B->C的依赖是test时（表中第3列），那么B->C的依赖无法传递给A；当B->C的依赖是provided（表第4列），只传递A->C的scope为provided的情况，其他情况B->C的依赖无法传递给A；当B->C的依赖是runtime（表第5列），那么C按照B->C的scope传递给A
 * 上面表格大家多看几遍，理解理解
 *
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven1_4 {
}
