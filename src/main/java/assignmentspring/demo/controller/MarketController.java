package assignmentspring.demo.controller;

import assignmentspring.demo.entity.Market;
import assignmentspring.demo.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/market")
public class MarketController {
    @Autowired
    MarketService marketService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<Object> search(@RequestParam String name){
        List<Market> marketList = marketService.search(name);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", marketList);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <Object> list(){
        List<Market> marketList = marketService.getListMarket();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", marketList);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> detail(@PathVariable long id){
        Market market = marketService.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", HttpStatus.OK.value());
        hashMap.put("message", "Success");
        hashMap.put("data", market);
        if(market == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Market obj){
        try{
            Market createMarket = marketService.createNew(obj);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("status", HttpStatus.OK.value());
            hashMap.put("message", "Success");
            hashMap.put("data", createMarket);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
