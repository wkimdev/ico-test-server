package kr.co.doublechain.ico.service;

import javax.annotation.Resource;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import kr.co.doublechain.ico.redis.RedisMessagePublisher;
import kr.co.doublechain.ico.service.vo.WalletVO;
import kr.co.doublechain.ico.util.CommonUtil;

/**
 * 
 * 주소 생성 및 tx 조회 수집/저장
 * 
 * created by basquiat
 *
 */
@Service("walletService")
public class WalletService {
	
	private static final Logger LOG = LoggerFactory.getLogger(WalletService.class);
	
	@Autowired
	private RedisMessagePublisher redisMessagePublisher;
	
	@Resource(name = "redisTemplate") 
	private ListOperations<String, String> listQueueOperations;
	
	/**	
	 * 주소 생성
	 * @param walletVO
	 * @throws Exception
	 */
	public void createWallet(WalletVO walletVO) throws Exception {
		LOG.info(CommonUtil.convertJsonStringFromObject(walletVO));
		
		// 이게 redis queue key 
		listQueueOperations.rightPush("dc:create:address", CommonUtil.convertJsonStringFromObject(walletVO));
		// 사실 필요없는 메세지 
		redisMessagePublisher.publish(CommonUtil.convertJsonStringFromObject(walletVO));
	}
	
}
