package assignmentspring.demo.controller;

import assignmentspring.demo.entity.Coin;
import assignmentspring.demo.entity.Market;
import assignmentspring.demo.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coin")
public class CoinController {
    @Autowired
    CoinService coinService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<Coin>> search(@RequestParam String name){
        List<Coin> coinList = coinService.search(name);
        return new ResponseEntity<>(coinList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchByMarketId")
    public ResponseEntity<List<Coin>> searchByMarketId(@RequestParam long marketId){
        List<Coin> coinList = coinService.searchByMarketId(marketId);
        return new ResponseEntity<>(coinList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<Coin>> list(){
        List<Coin> coinList = coinService.getListCoin();
        return new ResponseEntity<>(coinList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Coin> detail(@PathVariable long id){
        Coin coin = coinService.findById(id);
        if(coin == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(coin, HttpStatus.OK);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Coin> store(@RequestBody Coin obj){
        try{
            Coin createCoin = coinService.createNew(obj);
            return new ResponseEntity<>(createCoin, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
