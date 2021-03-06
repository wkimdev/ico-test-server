package kr.co.doublechain.ico.service.vo;


import java.math.BigDecimal;


import lombok.Getter;
import lombok.Setter;

/**
 * 
 * WalletVO
 * 
 * created by basquiat
 *
 */
@Getter
@Setter
public class WalletVO {

	/**
	 * 요청자 아이디
	 */
	private Integer userId;
	
	/**
	 * 코인 아이디
	 */
	private Integer coinId;
	
	/**
	 * 코인 코드
	 */
	private String coinCode;
	
	/**
	 * 코인 주소
	 */
	private String address;
	
	/**
	 * 발란스
	 */
	private BigDecimal balance;
	
	/**
	 * destination Tag
	 */
	private String destinationTag;
	
	/**
	 * 생성 날짜
	 */
	private String createdDttm;
	
}
