package assignmentspring.demo.controller;

import assignmentspring.demo.entity.Coin;
import assignmentspring.demo.entity.Market;
import assignmentspring.demo.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coin")
public class CoinController {
    @Autowired
    CoinService coinService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<Object> search(@RequestParam String name){
        List<Coin> coinList = coinService.search(name);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", coinList);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchByMarketId")
    public ResponseEntity<Object> searchByMarketId(@RequestParam long marketId){
        List<Coin> coinList = coinService.searchByMarketId(marketId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", coinList);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <Object> list(){
        List<Coin> coinList = coinService.getListCoin();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", coinList);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> detail(@PathVariable long id){
        Coin coin = coinService.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", coin);
        if(coin == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Coin obj){
        try{
            Coin createCoin = coinService.createNew(obj);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("status", HttpStatus.OK.value());
            hashMap.put("message", "Success");
            hashMap.put("data", createCoin);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
