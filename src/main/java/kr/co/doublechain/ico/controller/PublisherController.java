package kr.co.doublechain.ico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.doublechain.ico.service.WalletService;
import kr.co.doublechain.ico.service.vo.WalletVO;

/**
 * 
 * Request API
 * 
 * 주소생성을 요청하는 api 정
 * 
 */
@RestController
@RequestMapping(value = "api")
public class PublisherController {
	
	@Autowired
	WalletService walletService; 
    
	@PostMapping(value="/addresses")
    public ResponseEntity<Object> post(@RequestBody WalletVO walletVO) {

		HttpStatus status = HttpStatus.OK;
		
		try {
			walletService.createWallet(walletVO);
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		
        return new ResponseEntity<>(status);
    }
	
}
