package com.hu.ssm.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hutiantian
 * @create 2018/5/7 15:42
 * @since 1.0.0
 */
@Slf4j
@Component
public class RedisDistributedLockHandler {

	private final static long LOCK_EXPIRE = 60000L; // 默认单个业务持有锁的时间60s,防止死锁

	private final static long LOCK_TRY_INTERVAL = 100L; // 默认30ms尝试一次

	private final static long LOCK_TRY_TIMEOUT = 10*1000L; // 默认尝试10s

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

	private static RedisTemplate<String, Object> redisTemplate = new RedisTemplate();

    @PostConstruct
    public void init(){
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
    }

	/**
	 * 操作redis获取全局锁
	 *
	 * @param lock  锁的名称
	 * @param timeout 获取的超时时间
	 * @param tryInterval 多少ms尝试一次
	 * @param lockExpireTime 获取成功后锁的过期时间
	 * @return true 获取成功，false获取失败
	 */
	public static boolean getLock(Lock lock, long timeout, long tryInterval,long lockExpireTime) {
		try {
			if (StringUtils.isEmpty(lock.getName())|| StringUtils.isEmpty(lock.getValue())) {
				return false;
			}
			long startTime = System.currentTimeMillis();
			while (true) {
				if (redisTemplate.opsForValue().setIfAbsent(lock.getName(),lock.getValue())) {
					redisTemplate.opsForValue().set(lock.getName(), lock.getValue(),lockExpireTime, TimeUnit.MILLISECONDS);
					log.info(Thread.currentThread().getName()+ " : get lock");
					return true;
				} else {
					log.info(Thread.currentThread().getName()+ " : ----> locking is exist!!!");
				}
				if (System.currentTimeMillis() - startTime > timeout) {
					log.info(Thread.currentThread().getName()+"try get lock timeout");
					return false;
				}
				Thread.sleep(tryInterval);
			}
		} catch (Exception e) {
			log.error("try get lock exception",e);
			return false;
		}
	}

	/**
	 * 尝试获取全局锁
	 *
	 * @param lock 锁的名称
	 * @return true 获取成功，false获取失败
	 */
	public static boolean tryLock(Lock lock) {
		return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
	}

	/**
	 * 尝试获取全局锁
	 *
	 * @param lock 锁的名称
	 * @param timeout 获取超时时间 单位ms
	 * @return true 获取成功，false获取失败
	 */
	public static boolean tryLock(Lock lock, long timeout) {
		return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
	}

	/**
	 * 尝试获取全局锁
	 *
	 * @param lock 锁的名称
	 * @param timeout 获取锁的超时时间
	 * @param tryInterval 多少毫秒尝试获取一次
	 * @return true 获取成功，false获取失败
	 */
	public static boolean tryLock(Lock lock, long timeout, long tryInterval) {
		return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
	}

	/**
	 * 尝试获取全局锁
	 *
	 * @param lock 锁的名称
	 * @param timeout 获取锁的超时时间
	 * @param tryInterval 多少毫秒尝试获取一次
	 * @param lockExpireTime 锁的过期
	 * @return true 获取成功，false获取失败
	 */
	public static boolean tryLock(Lock lock, long timeout, long tryInterval,long lockExpireTime) {
		return getLock(lock, timeout, tryInterval, lockExpireTime);
	}

	/**
	 * 释放锁
	 */
	public static void releaseLock(Lock lock) {
		if (!StringUtils.isEmpty(lock.getName())) {
			log.info(Thread.currentThread().getName() + " : del lock");
			redisTemplate.delete(lock.getName());
		}
	}

}
