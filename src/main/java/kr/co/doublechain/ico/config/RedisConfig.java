package kr.co.doublechain.ico.config;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


/**
 * 
 * RedisConfig
 * 
 * created by basquiat
 *
 */
@Configuration
public class RedisConfig {
	
	@Value("${publish.channel}")
	private String pubChannel;
	
	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Value("${spring.redis.password}")
	private String redisPassword;
	
//	@Autowired
//	private WalletService walletService;
	
	@SuppressWarnings("deprecation")
	/**
	 * redis connection factory
	 * @return JedisConnectionFactory
	 */
	@Bean
    public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisHost);
		jedisConnectionFactory.setPort(redisPort);
		jedisConnectionFactory.setPassword(redisPassword);
		jedisConnectionFactory.setUsePool(true);		
		return jedisConnectionFactory;
    }

	/**
	 * Redis Template Setup
	 * @return RedisTemplate<String, Object>
	 */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
    
    /**
     * publish 채널 등록
     * @return ChannelTopic
     */
    @Bean
    public ChannelTopic topic() {
    	return new ChannelTopic(pubChannel);
    }
    
    /**
     * redis pub setup
     * @return MessageListenerAdapter
     */   
    @Bean
    public MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate(), topic());
    }
 
}
