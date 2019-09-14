package assignmentspring.demo.controller;

import assignmentspring.demo.entity.Market;
import assignmentspring.demo.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/market")
public class MarketController {
    @Autowired
    MarketService marketService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<Market>> search(@RequestParam String name){
        List<Market> marketList = marketService.search(name);
        return new ResponseEntity<>(marketList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<Market>> list(){
        List<Market> marketList = marketService.getListMarket();
        return new ResponseEntity<>(marketList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Market> detail(@PathVariable long id){
        Market market = marketService.findById(id);
        if(market == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(market, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Market> store(@RequestBody Market obj){
        try{
            Market createMarket = marketService.createNew(obj);
            return new ResponseEntity<>(createMarket, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
