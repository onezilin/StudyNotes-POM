package com.studynotes.demo02_redisTemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: 测试 ZSet 类型的操作
 */
@Slf4j
@SpringBootTest
public class Demo05_ZSetTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 测试zset类型
    @Test
    public void zsetTest() {
        // 需要使用typedtuple泛型来存储有分数和值的数据
        Set<ZSetOperations.TypedTuple> set1 = new HashSet<>();
        Set<ZSetOperations.TypedTuple> set2 = new HashSet<>();
        int j = 9;
        for (int i = 1; i <= 9; i++) {
            j--;
            Double score1 = Double.valueOf(i);
            String value1 = "x" + i;
            Double score2 = Double.valueOf(j);
            String value2 = "y" + j;
            // 若查询相同的值，虽然值会插入失败，但是会修改值对应的分数
            ZSetOperations.TypedTuple tYpedTuple1 = new DefaultTypedTuple(value1, score1);
            set1.add(tYpedTuple1);
            ZSetOperations.TypedTuple typedTuple2 = new DefaultTypedTuple(value2, score2);
            set2.add(typedTuple2);
        }
        BoundZSetOperations zkey3 = redisTemplate.boundZSetOps("zkey3");
        BoundZSetOperations zkey4 = redisTemplate.boundZSetOps("key4");
        zkey3.add(set1);
        zkey4.add(set2);
        // 有序集合都是先对集合的分数排序后再操作的
        // zcard zkey3
        System.out.println(zkey3.size());
        // zcount zkey3 3 6  求 3<=score<=6的元素的个数
        System.out.println(zkey3.count(3, 6));
        // zrange zkey3 1 5 返回排序后索引 1<=index<=5
        // zrange 根据索引查询
        System.out.println("range1：" + zkey3.range(1, 5));
        // zrange zkey3 1 5 withscores
        System.out.println("range2：" + zkey3.rangeWithScores(1, 5));
        // zrange zkey3 -1 5 withscores 返回的是 索引从结尾开始数到-1的绝对值, ？不过java这里貌似不行，找不到
        System.out.println("range3：" + zkey3.rangeWithScores(-1, 5));
        // zrange zkey3 0 -1 withscores 返回的是 索引从0开始数到结尾, ？不过java这里貌似不行，报错
        //        System.out.println("range4："+zkey3.rangeWithScores(0,-1));
        // zinterstore zkey 2 skey3 skey4
        zkey3.intersectAndStore("zkey4", "zkey5");
        System.out.println(redisTemplate.opsForZSet().range("zkey5", 0, -1));
        // zrangebylex zkey3 (x1 (x8
        // 如果x1和x8的分数相同，则取这两个值之间的数；如果x1和x8的分数不一致，则在x1到x8分数区间上，取每一个分数的值中位于x1到x8的值;
        // zrangebylex一般用于只有一个分数不同值的集合中搜索
        RedisZSetCommands.Range range = RedisZSetCommands.Range.range();
        range.lt("x8");
        range.gt("x1");
        System.out.println(zkey3.rangeByLex(range));
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit();
        limit.count(4);
        limit.offset(5);
        // zrangebylex zkey3 (x1 (x8 limit 4 5
        System.out.println(zkey3.rangeByLex(range, limit));
        // zrank zkey3 x4 按照分数排序后返回x4的索引
        System.out.println(zkey3.rank("x4"));
        // 好像没有zrembylex
        // zrem zkey3 x5 x6
        System.out.println(zkey3.remove("x5", "x6"));
        // zremrangebyrank zkey3 0 3 删除索引下标为0到3之间的值
        zkey3.removeRange(0, 3);
        // zremrangebyscore zkey3 0 3 删除分数为0到3的值
        zkey3.removeRangeByScore(0, 3);
        // 这里的分数从大到小排序
        // zrevrangebyscore zkey4 6 0  注意这里倒序分数是最大值和最小值的区间
        System.out.println(zkey4.reverseRangeByScore(6, 0));
        // zrevrange zkey4 0 6 获取的是索引下标0到6的区间
        System.out.println(zkey4.reverseRange(0, 6));
        // zincrby zkey3 11
        System.out.println(zkey3.incrementScore("x1", 11));
    }

}
